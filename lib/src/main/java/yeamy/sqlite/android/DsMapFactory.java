package yeamy.sqlite.android;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DsMapFactory {

    private static ArrayList<String> findColumnIndex(Cursor cursor) {
        ArrayList<String> fields = new ArrayList<>();
        for (int i = 1, c = cursor.getColumnCount(); i <= c; i++) {
            fields.add(cursor.getColumnName(i));
        }
        return fields;
    }

    private static void read(Cursor cursor, Map<String, String> map, List<String> list) {
        for (int i = 0, l = list.size(); i < l; i++) {
            String label = list.get(i);
            String value = cursor.getString(i + 1);
            map.put(label, value);
        }
    }

    private static LinkedHashMap<String, String> read(Cursor cursor, List<String> list) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        read(cursor, map, list);
        return map;
    }

    public static void read(Cursor cursor, Map<String, String> map) {
        ArrayList<String> list = findColumnIndex(cursor);
        if (cursor.moveToNext()) {
            read(cursor, map, list);
        }
    }

    public static LinkedHashMap<String, String> read(Cursor cursor) {
        ArrayList<String> list = findColumnIndex(cursor);
        if (cursor.moveToNext()) {
            return read(cursor, list);
        }
        return null;
    }

    public static ArrayList<Map<String, String>> readArray(Cursor cursor) {
        ArrayList<Map<String, String>> out = new ArrayList<>();
        ArrayList<String> list = findColumnIndex(cursor);
        while (cursor.moveToNext()) {
            out.add(read(cursor, list));
        }
        return out;
    }

    public static void readArray(Cursor cursor, Collection<Map<String, String>> col, int limit) {
        ArrayList<String> list = findColumnIndex(cursor);
        while (cursor.moveToNext()) {
            if (limit-- <= 0) {
                break;
            }
            col.add(read(cursor, list));
        }
    }

}
