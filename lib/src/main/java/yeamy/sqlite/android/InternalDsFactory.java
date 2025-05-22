package yeamy.sqlite.android;

import android.database.Cursor;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The factory to generate java bean,
 * add custom types' adapter to create fields value.
 * You can cache the factory to avoid too much reflection.
 */
class InternalDsFactory<T> {
    protected final Class<T> type;
    private Constructor<T> constructor;
    private static final Object unsafe;
    private static final Method allocateInstance;
    private static final boolean java9;

    static {
        java9 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU;
        Object theUnsafe = null;
        Method method = null;
        try {
            Class<?> unsafe = Class.forName("sun.misc.Unsafe");
            Field f = unsafe.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            theUnsafe = f.get(null);
            method = unsafe.getDeclaredMethod("allocateInstance", Class.class);
        } catch (Exception ignored) {
        }
        unsafe = theUnsafe;
        allocateInstance = method;
    }

    /**
     * @param type type to generate
     */
    InternalDsFactory(Class<T> type) {
        this.type = type;
        if (unsafe != null) {
            try {
                this.constructor = type.getDeclaredConstructor();
            } catch (Exception ignored2) {
            }
        }
    }

    private DsField createDsField(Field field, Cursor cursor, Map<Class<?>, DsFieldReader<?>> fieldMap) throws ReflectiveOperationException {
        if (field.isAnnotationPresent(DsIgnore.class)) {
            return null;
        }
        DsType dsType = DsType.getDsType(field);
        if (dsType == DsType.Extra) {// extra type
            if (fieldMap != null) {
                DsFieldReader<?> reader = fieldMap.get(field.getType());
                if (reader != null) {
                    return DsReaderField.create(field, cursor, reader);
                }
            } else if (field.isAnnotationPresent(DsColumn.class)) {// cannot read
                return null;
            }
            return DsExtendField.create(field, cursor, fieldMap);
        } else if (dsType == DsType.Enum) {
            return DsEnumField.create(field, cursor);
        } else {// base type
            return DsBaseField.create(field, dsType, cursor);
        }
    }

    @RequiresApi(33)
    private DsField createDsFieldNewApi(Field field, Cursor cursor, Map<Class<?>, DsFieldReader<?>> fieldMap) throws ReflectiveOperationException {
        if (field.isAnnotationPresent(DsIgnore.class)) {
            return null;
        }
        DsType dsType = DsType.getDsType(field);
        if (dsType == DsType.Extra) {// extra type
            if (fieldMap != null) {
                DsFieldReader<?> reader = fieldMap.get(field.getType());
                if (reader != null) {
                    return DsReaderField9.create(type, field, cursor, reader);
                }
            } else if (field.isAnnotationPresent(DsColumn.class)) {// cannot read
                return null;
            }
            return DsExtendField9.create(type, field, cursor, fieldMap);
        } else if (dsType == DsType.Enum) {
            return DsEnumField9.create(field, cursor);
        } else {// base type
            return DsBaseField9.create(type, field, dsType, cursor);
        }
    }

    List<DsField> createDsFields(Cursor cursor, Map<Class<?>, DsFieldReader<?>> fieldMap,
                                 boolean multiple) throws ReflectiveOperationException {
        ArrayList<DsField> list = new ArrayList<>();
        Field[] fields = type.getFields();
        for (Field field : fields) {
            DsField f = multiple && (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    ? createDsFieldNewApi(field, cursor, fieldMap)
                    : createDsField(field, cursor, fieldMap);
            if (f != null) {
                f.setAccessible();
                list.add(f);
            }
        }
        Collections.sort(list, (o1, o2) -> o1.sortInt() - o2.sortInt());
        return list;
    }

    @SuppressWarnings({"unchecked"})
    private T createInstance() throws ReflectiveOperationException {
        if (unsafe != null) {
            return (T) allocateInstance.invoke(unsafe, type);
        } else if (constructor != null) {
            return constructor.newInstance();
        } else {
            return type.newInstance();
        }
    }




    T read(Cursor cursor, List<DsField> list) throws ReflectiveOperationException {
        T t = createInstance();
        for (DsField f : list) {
            f.read(cursor, t);
        }
        if (t instanceof DsObserver) {
            ((DsObserver) t).onDsFinish();
        }
        return t;
    }

    T read(Cursor cursor, Map<Class<?>, DsFieldReader<?>> fieldMap) throws ReflectiveOperationException {
        List<DsField> list = createDsFields(cursor, fieldMap, false);
        if (cursor.moveToNext()) {
            return read(cursor, list);
        }
        return null;
    }

//    public T read(Cursor cursor) throws ReflectiveOperationException {
//        List<DsField> list = findColumnIndex(cursor);
//        if (cursor.moveToNext()) {
//            return read(cursor, list);
//        }
//        return null;
//    }
//
//    public void readArray(Collection<T> out, Cursor cursor, int limit)
//            throws ReflectiveOperationException {
//        List<DsField> list = findColumnIndex(cursor);
//        while (cursor.moveToNext()) {
//            if (limit-- <= 0) {
//                break;
//            }
//            out.add(read(cursor, list));
//        }
//    }
//
//    public void readArray(List<T> out, Cursor cursor)
//            throws ReflectiveOperationException {
//        List<DsField> list = findColumnIndex(cursor);
//        while (cursor.moveToNext()) {
//            out.add(read(cursor, list));
//        }
//    }

    void readArray(Cursor cursor, Map<Class<?>, DsFieldReader<?>> fieldMap, Collection<T> out, int limit) throws ReflectiveOperationException {
        List<DsField> list = createDsFields(cursor, fieldMap, true);
        while (cursor.moveToNext()) {
            if (limit-- <= 0) {
                break;
            }
            out.add(read(cursor, list));
        }
    }

    void readArray(Cursor cursor, Map<Class<?>, DsFieldReader<?>> fieldMap, Collection<T> out) throws ReflectiveOperationException {
        List<DsField> list = createDsFields(cursor, fieldMap, true);
        while (cursor.moveToNext()) {
            out.add(read(cursor, list));
        }
    }

}
