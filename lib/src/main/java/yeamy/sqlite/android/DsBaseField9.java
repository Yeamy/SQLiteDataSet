package yeamy.sqlite.android;

import android.database.Cursor;

import androidx.annotation.RequiresApi;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Calendar;

@RequiresApi(33)
class DsBaseField9 extends DsField {
    private static final Calendar calendar = Calendar.getInstance();
    private final DsType dsType;
    private final VarHandle vh;

    public static DsField create(Class<?> type, Field field, DsType dsType, Cursor cursor) throws ReflectiveOperationException {
        int columnIndex = findColumnIndex(field, cursor);
        return columnIndex == -1 ? null : new DsBaseField9(type, field, columnIndex, dsType);
    }

    DsBaseField9(Class<?> type, Field field, int columnIndex, DsType dsType) throws ReflectiveOperationException {
        super(field, columnIndex);
        this.dsType = dsType;
        this.vh = MethodHandles.lookup().findVarHandle(field.getType(), field.getName(), type);
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
                vh.set(cursor.getInt(columnIndex) != 0);
                break;
            case _short:
            case Short:
                vh.set(cursor.getShort(columnIndex));
                break;
            case _int:
            case Integer:
                vh.set(cursor.getInt(columnIndex));
                break;
            case _long:
            case Long:
                vh.set(cursor.getLong(columnIndex));
                break;
            case _float:
            case Float:
                vh.set(cursor.getFloat(columnIndex));
                break;
            case _double:
            case Double:
                vh.set(cursor.getDouble(columnIndex));
                break;
            case BigDecimal:
                vh.set(new BigDecimal(cursor.getString(columnIndex)));
                break;
            case String:
                vh.set(cursor.getString(columnIndex));
                break;
            case Blob:
                vh.set((Object) cursor.getBlob(columnIndex));
                break;
            default:
        }
    }
}