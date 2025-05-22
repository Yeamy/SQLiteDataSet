package yeamy.sqlite.android;

import android.database.Cursor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

abstract class DsField {
    final Field field;
    final int columnIndex;

    public DsField(Field field, int columnIndex) {
        this.field = field;
        this.columnIndex = columnIndex;
    }

    abstract void read(Cursor cursor, Object t) throws ReflectiveOperationException;

    abstract int sortInt();

    void setAccessible() {
        int flag = field.getModifiers();
        if (!Modifier.isPublic(flag) || Modifier.isFinal(flag)) {
            field.setAccessible(true);
        }
    }

    static int findColumnIndex(Field field, Cursor cursor) {
        DsColumn ann = field.getAnnotation(DsColumn.class);
        if (ann != null && ann.value() != null && !ann.value().isEmpty()) {
            return cursor.getColumnIndex(ann.value());
        }
        int i = cursor.getColumnIndex(field.getName());
        if (i == -1) {
            i = cursor.getColumnIndex(name2underline(field.getName()));
        }
        return i;
    }

    static String name2underline(String name) {
        StringBuilder sb = new StringBuilder(name);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                sb.setCharAt(i, (char) (c + 32));
                sb.insert(i, '_');
                i++;
            }
        }
        return sb.toString();
    }

    public static boolean getBoolean(Cursor cursor, int columnIndex) {
        return cursor.getInt(columnIndex) != 0;
    }

    public static BigDecimal getBigDecimal(Cursor cursor, int columnIndex) {
        return new BigDecimal(cursor.getString(columnIndex));
    }
}