package yeamy.sqlite.android;

import android.database.Cursor;

import java.lang.reflect.Field;
import java.math.BigDecimal;

class DsBaseField extends DsField {
    private final DsType dsType;

    public static DsField create(Field field, DsType dsType, Cursor cursor) {
        int columnIndex = findColumnIndex(field, cursor);
        return columnIndex == -1 ? null : new DsBaseField(field, columnIndex, dsType);
    }

    DsBaseField(Field field, int columnIndex, DsType dsType) {
        super(field, columnIndex);
        this.dsType = dsType;
    }

    @Override
    int sortInt() {
        return 0;
    }

    @Override
    public void read(Cursor cursor, Object t) throws ReflectiveOperationException {
        switch (dsType) {
            case _boolean:
            case Boolean:
                field.setBoolean(t, cursor.getInt(columnIndex) != 0);
                break;
            case _short:
            case Short:
                field.setShort(t, cursor.getShort(columnIndex));
                break;
            case _int:
            case Integer:
                field.setInt(t, cursor.getInt(columnIndex));
                break;
            case _long:
            case Long:
                field.setLong(t, cursor.getLong(columnIndex));
                break;
            case _float:
            case Float:
                field.setFloat(t, cursor.getFloat(columnIndex));
                break;
            case _double:
            case Double:
                field.setDouble(t, cursor.getDouble(columnIndex));
                break;
            case BigDecimal:
                field.set(t, new BigDecimal(cursor.getString(columnIndex)));
                break;
            case String:
                field.set(t, cursor.getString(columnIndex));
                break;
            case Blob:
                field.set(t, cursor.getBlob(columnIndex));
                break;
            default:
        }
    }
}