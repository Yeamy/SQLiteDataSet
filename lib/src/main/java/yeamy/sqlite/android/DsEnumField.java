package yeamy.sqlite.android;

import android.database.Cursor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class DsEnumField extends DsField {
    private final Class<?> type;
    private final Method method;

    public static DsField create(Field field, Cursor cursor) {
        int columnIndex = findColumnIndex(field, cursor);
        return columnIndex == -1 ? null : new DsEnumField(field, columnIndex);
    }

    DsEnumField(Field field, int columnIndex) {
        super(field, columnIndex);
        this.type = field.getType();
        Method method;
        try {
            method = type.getMethod("valueOf", String.class);
        } catch (Exception e) {
            method = null;
        }
        this.method = method;
    }

    @Override
    int sortInt() {
        return 0;
    }

    @Override
    public void read(Cursor cursor, Object t) throws ReflectiveOperationException {
        String data = cursor.getString(columnIndex);
        Object obj = method.invoke(type, data);
        field.set(t, obj);
    }
}