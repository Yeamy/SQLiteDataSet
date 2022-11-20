package yeamy.sqlite.android;

import android.database.Cursor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import yeamy.sql.DsIgnore;
import yeamy.sql.DsObserver;

/**
 * The factory to generate java bean,
 * add custom types' adapter to create fields value.
 * You can cache the factory to avoid too much reflection.
 */
public class DsFactory<T> {

    private HashMap<Class<?>, DsAdapter> map;
    private final List<DsField> fields;
    private final Class<T> type;

    /**
     * @param type type to generate
     */
    public DsFactory(Class<T> type) {
        this.type = type;
        LinkedList<DsField> list = new LinkedList<>();
        Field[] fields = type.getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DsIgnore.class)) {
                continue;
            }
            DsField f = DsField.get(field, this);
            if (f != null) {
                if (f.isBaseType()) {
                    list.addFirst(f);
                } else {
                    list.addLast(f);
                }
            }
        }
        this.fields = list;
    }

    /**
     * add a field type adapter
     * @param fieldType type of field
     * @param adapter adapter to read field
     */
    public void addAdapter(Class<?> fieldType, DsAdapter adapter) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(fieldType, adapter);
    }

    /**
     * get a field type adapter
     * @param fieldType type of field
     * @return  adapter to read field
     */
    public DsAdapter getAdapter(Class<?> fieldType) {
        if (map == null) {
            return null;
        }
        while (true) {
            if (fieldType == null) {
                return null;
            }
            DsAdapter adapter = map.get(fieldType);
            if (adapter == null) {
                fieldType = fieldType.getSuperclass();
            } else {
                return adapter;
            }
        }
    }

    List<DsField> findColumnIndex(Cursor cursor) {
        List<DsField> fields = new ArrayList<>();
        for (DsField dsField : this.fields) {
            if (dsField.findColumnIndex(cursor)) {
                fields.add(dsField);
            }
        }
        return fields;

    }

    T read(Cursor cursor, List<DsField> list)
            throws InstantiationException, IllegalAccessException {
        T t = type.newInstance();
        for (DsField f : list) {
            f.read(cursor, t);
        }
        if (t instanceof DsObserver) {
            ((DsObserver) t).onDsFinish();
        }
        return t;
    }

    public T read(Cursor cursor) throws InstantiationException, IllegalAccessException {
        List<DsField> list = findColumnIndex(cursor);
        if (cursor.moveToNext()) {
            return read(cursor, list);
        }
        return null;
    }

    public void readArray(Collection<T> out, Cursor cursor, int limit)
            throws InstantiationException, IllegalAccessException {
        List<DsField> list = findColumnIndex(cursor);
        while (cursor.moveToNext()) {
            if (limit-- <= 0) {
                break;
            }
            out.add(read(cursor, list));
        }
    }

    public void readArray(List<T> out, Cursor cursor)
            throws InstantiationException, IllegalAccessException {
        List<DsField> list = findColumnIndex(cursor);
        while (cursor.moveToNext()) {
            out.add(read(cursor, list));
        }
    }

}
