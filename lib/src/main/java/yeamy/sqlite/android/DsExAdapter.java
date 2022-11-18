package yeamy.sqlite.android;

import android.database.Cursor;

import java.lang.reflect.Field;
import java.util.List;

class DsExAdapter implements DsAdapter {
    private final DsFactory<?> factory;
    private transient List<DsField> list;

    DsExAdapter(Class<?> clz) {
        factory = new DsFactory<>(clz);
    }

    @Override
    public Object read(Field field, Cursor cursor, int columnIndex)
            throws InstantiationException, IllegalAccessException {
        return factory.read(cursor, list);
    }

    boolean findColumnIndex(Cursor cursor) {
        list = factory.findColumnIndex(cursor);
        return list.size() > 0;
    }
}