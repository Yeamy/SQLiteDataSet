package yeamy.sqlite.android;

import android.database.Cursor;

import androidx.annotation.RequiresApi;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;

@RequiresApi(33)
class DsReaderField9 extends DsField {
    private final DsFieldReader<?> reader;
    private final VarHandle vh;

    public static DsField create(Class<?> type, Field field, Cursor cursor, DsFieldReader<?> reader) throws ReflectiveOperationException {
        int columnIndex = findColumnIndex(field, cursor);
        return columnIndex == -1 ? null : new DsReaderField9(type, field, columnIndex, reader);
    }

    private DsReaderField9(Class<?> type, Field field, int columnIndex, DsFieldReader<?> reader) throws ReflectiveOperationException {
        super(field, columnIndex);
        this.reader = reader;
        this.vh = MethodHandles.lookup().findVarHandle(field.getType(), field.getName(), type);
    }

    @Override
    public int sortInt() {
        return 1;
    }

    @Override
    public void read(Cursor cursor, Object t) throws ReflectiveOperationException {
        vh.set(t, reader.read(cursor, columnIndex));
    }
}
