package com.yeamy.sqlite.ds;

import android.database.Cursor;

import java.lang.reflect.Field;
import java.util.List;

class DsExAdapter implements DsAdapter {
    private DsFactory<?> factory;
    private transient List<DsField> list;

    DsExAdapter(Class<?> clz) {
        factory = new DsFactory<>(clz);
    }

    @Override
    public void read(Object t, Field field, Cursor cursor, int columnIndex)
            throws InstantiationException, IllegalAccessException {
        field.set(t, factory.read(cursor, list));
    }

    boolean findColumnIndex(Cursor cursor) {
        list = factory.findColumnIndex(cursor);
        return list.size() > 0;
    }
}