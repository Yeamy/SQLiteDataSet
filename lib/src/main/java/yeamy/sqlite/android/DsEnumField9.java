package yeamy.sqlite.android;

import android.database.Cursor;

import androidx.annotation.RequiresApi;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

@RequiresApi(33)
class DsEnumField9 extends DsField {
    private final MethodHandle handle;

    public static DsField create(Field field, Cursor cursor) throws ReflectiveOperationException {
        int columnIndex = findColumnIndex(field, cursor);
        return columnIndex == -1 ? null : new DsEnumField9(field, columnIndex);
    }

    DsEnumField9(Field field, int columnIndex) throws ReflectiveOperationException {
        super(field, columnIndex);
        Class<?> type = field.getType();
        this.handle = MethodHandles.lookup().findStatic(type, "valueOf", MethodType.methodType(type, String.class));
    }

    @Override
    int sortInt() {
        return 0;
    }

    @Override
    public void read(Cursor cursor, Object t) throws ReflectiveOperationException {
        String data = cursor.getString(columnIndex);
        try {
            field.set(t, handle.invoke(data));
        } catch (Throwable ignored) {
        }
    }
}