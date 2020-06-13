package com.yeamy.sqlite.ds;

import android.database.Cursor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DsFactory<T> {
    public static boolean DEBUG = false;

    private HashMap<Class<?>, DsAdapter> map;
    private List<DsField> fields;
    private Class<T> clz;

    public DsFactory(Class<T> clz) {
        this.clz = clz;
        LinkedList<DsField> list = new LinkedList<>();
        Field[] fields = clz.getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DsIgnore.class)) {
                continue;
            }
            DsField f = DsField.get(field, this);
            if (f != null) {
                if (f.isBaseType()) {
                    list.addFirst(f);
                } else {
                    list.addLast(f);
                }
            }
        }
        this.fields = list;
    }

    public void addAdapter(Class<?> clz, DsAdapter adapter) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(clz, adapter);
    }

    public DsAdapter getAdapter(Class<?> clz) {
        if (map == null) {
            return null;
        }
        while (true) {
            if (clz == null) {
                return null;
            }
            DsAdapter adapter = map.get(clz);
            if (adapter == null) {
                clz = clz.getSuperclass();
            } else {
                return adapter;
            }
        }
    }

    List<DsField> findColumnIndex(Cursor cursor) {
        List<DsField> fields = new ArrayList<>();
        for (DsField dsField : this.fields) {
            if (dsField.findColumnIndex(cursor)) {
                fields.add(dsField);
            }
        }
        return fields;

    }

    T read(Cursor cursor, List<DsField> list)
            throws InstantiationException, IllegalAccessException {
        T t = clz.newInstance();
        for (DsField f : list) {
            f.read(cursor, t);
        }
        if (t instanceof DsObserver) {
            ((DsObserver) t).onDsFinish();
        }
        return t;
    }

    public T read(Cursor cursor) throws InstantiationException, IllegalAccessException {
        List<DsField> list = findColumnIndex(cursor);
        if (cursor.moveToNext()) {
            return read(cursor, list);
        }
        return null;
    }

    public T readExtra(Cursor cursor) throws InstantiationException, IllegalAccessException {
        List<DsField> list = findColumnIndex(cursor);
        return read(cursor, list);
    }

    public void readArray(List<T> out, Cursor cursor, int limit)
            throws InstantiationException, IllegalAccessException {
        List<DsField> list = findColumnIndex(cursor);
        while (cursor.moveToNext()) {
            if (limit-- <= 0) {
                break;
            }
            out.add(read(cursor, list));
        }
    }

    /**
     * default read top 200
     */
    public void readArray(List<T> out, Cursor cursor)
            throws InstantiationException, IllegalAccessException {
        readArray(out, cursor, 200);
    }

}
