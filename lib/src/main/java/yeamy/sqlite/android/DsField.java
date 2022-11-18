package yeamy.sqlite.android;

import android.database.Cursor;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import yeamy.sql.DsColumn;
import yeamy.sql.DsType;

class DsField {
    private final Field field;
    private final DsType dsType;
    private final DsAdapter adapter;
    transient int columnIndex;

    static DsField get(Field field, DsFactory<?> factory) {
        DsType dsType = DsType.getDsType(field);
        if (dsType == DsType.Extra) {// extra type
            Class<?> type = field.getType();
            DsAdapter adapter = factory.getAdapter(type);
            if (adapter == null) {// not defined
                if (field.isAnnotationPresent(DsColumn.class)) {
                    return null;
                } else {
                    adapter = new DsExAdapter(type);
                    factory.addAdapter(type, adapter);
                    return new DsField(field, dsType, adapter);
                }
            } else {
                return new DsField(field, dsType, adapter);
            }
        } else {// base type
            return new DsField(field, dsType, null);
        }
    }

    private DsField(Field field, DsType dsType, DsAdapter adapter) {
        this.field = field;
        this.dsType = dsType;
        this.adapter = adapter;
    }

    boolean isBaseType() {
        return adapter == null;
    }

    boolean findColumnIndex(Cursor cursor) {
        if (adapter != null && adapter instanceof DsExAdapter) {
            DsExAdapter exAdapter = (DsExAdapter) adapter;
            return exAdapter.findColumnIndex(cursor);
        }
        DsColumn column = field.getAnnotation(DsColumn.class);
        String label = (column == null) ? null : column.value();
        String columnLabel;
        if (label != null && label.length() > 0) {
            columnLabel = label;
        } else {
            columnLabel = field.getName();
        }
        try {
            columnIndex = cursor.getColumnIndex(columnLabel);
            return true;
        } catch (Exception e) {
            if (DsFactory.DEBUG) {
                e.printStackTrace();
            }
            return false;
        }
    }

    void read(Cursor cursor, Object t) throws InstantiationException, IllegalAccessException {
        if (adapter != null) {
            adapter.read(t, field, cursor, columnIndex);
            return;
        }
        switch (dsType) {
            case _boolean:
            case Boolean:
                field.setBoolean(t, getBoolean(cursor, columnIndex));
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
                field.set(t, getBigDecimal(cursor, columnIndex));
                break;
            case String:
                field.set(t, cursor.getString(columnIndex));
                break;
            case Blob:
                field.set(t, cursor.getBlob(columnIndex));
                break;
            default:
                break;
        }
    }

    public static boolean getBoolean(Cursor cursor, int columnIndex) {
        return cursor.getInt(columnIndex) != 0;
    }

    public static BigDecimal getBigDecimal(Cursor cursor, int columnIndex) {
        return new BigDecimal(cursor.getString(columnIndex));
    }
}