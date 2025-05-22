package yeamy.sqlite.android;

import android.database.Cursor;

import java.lang.reflect.Field;

class DsReaderField extends DsField {
    private final DsFieldReader<?> reader;

    public static DsField create(Field field, Cursor cursor, DsFieldReader<?> reader) {
        int columnIndex = findColumnIndex(field, cursor);
        return columnIndex == -1 ? null : new DsReaderField(field, columnIndex, reader);
    }

    private DsReaderField(Field field, int columnIndex, DsFieldReader<?> reader) {
        super(field, columnIndex);
        this.reader = reader;
    }

    @Override
    public int sortInt() {
        return 1;
    }

    @Override
    public void read(Cursor cursor, Object t) throws ReflectiveOperationException {
        field.set(t, reader.read(cursor, columnIndex));
    }
}
