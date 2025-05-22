package yeamy.sqlite.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yeamy.utils.array.BooleanArray;
import yeamy.utils.array.DoubleArray;
import yeamy.utils.array.FloatArray;
import yeamy.utils.array.IntArray;
import yeamy.utils.array.LongArray;
import yeamy.utils.array.ShortArray;

public class DsReader {

    /**
     * Return the value of the first column of the first row as boolean
     *
     * @param db       the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as boolean
     */
    public static boolean getBoolean(SQLiteDatabase db, String sql, boolean fallback) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return DsField.getBoolean(cursor, 0);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as boolean
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as boolean or null if no query result
     */
    @Nullable
    public static Boolean getBoolean(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return DsField.getBoolean(cursor, 0);
            }
            return null;
        }
    }

    /**
     * Return the values of the first column as boolean
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as boolean in an array, empty array if no query result
     */
    @NonNull
    public static boolean[] getBooleanArray(SQLiteDatabase db, String sql) {
        BooleanArray list = new BooleanArray();
        try (Cursor cursor = db.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                list.add(DsField.getBoolean(cursor, 0));
            }
            return list.getArray();
        }
    }

    /**
     * Return the value of the first column of the first row as short
     *
     * @param db       the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as short
     */
    public static short getShort(SQLiteDatabase db, String sql, byte fallback) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getShort(0);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as short
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as short or null if no query result
     */
    @Nullable
    public static Short getShort(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getShort(0);
            }
            return null;
        }
    }

    /**
     * Return the values of the first column as short
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as short in an array, empty array if no query result
     */
    @NonNull
    public static short[] getShortArray(SQLiteDatabase db, String sql) {
        ShortArray list = new ShortArray();
        try (Cursor cursor = db.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                list.add(cursor.getShort(0));
            }
            return list.getArray();
        }
    }

    /**
     * Return the value of the first column of the first row as int
     *
     * @param db       the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as int
     */
    public static int getInt(SQLiteDatabase db, String sql, int fallback) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getInt(0);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as int
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as int or null if no query result
     */
    @Nullable
    public static Integer getInt(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getInt(0);
            }
            return null;
        }
    }

    /**
     * Return the values of the first column as int
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as int in an array, empty array if no query result
     */
    @NonNull
    public static int[] getIntArray(SQLiteDatabase db, String sql) {
        IntArray list = new IntArray();
        try (Cursor cursor = db.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                list.add(cursor.getInt(0));
            }
            return list.getArray();
        }
    }

    /**
     * Return the value of the first column of the first row as long
     *
     * @param db       the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as long
     */
    public static long getLong(SQLiteDatabase db, String sql, long fallback) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getLong(0);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as long
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as long or null if no query result
     */
    @Nullable
    public static Long getLong(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getLong(0);
            }
            return null;
        }
    }

    /**
     * Return the values of the first column as long
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as long in an array, empty array if no query result
     */
    @NonNull
    public static long[] getLongArray(SQLiteDatabase db, String sql) {
        LongArray list = new LongArray();
        try (Cursor cursor = db.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                list.add(cursor.getLong(0));
            }
            return list.getArray();
        }
    }

    /**
     * Return the value of the first column of the first row as float
     *
     * @param db       the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as float
     */
    public static float getFloat(SQLiteDatabase db, String sql, float fallback) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getFloat(0);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as float
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as float or null if no query result
     */
    @Nullable
    public static Float getFloat(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getFloat(0);
            }
            return null;
        }
    }

    /**
     * Return the values of the first column as float
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as float in an array, empty array if no query result
     */
    @NonNull
    public static float[] getFloatArray(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            FloatArray list = new FloatArray();
            while (cursor.moveToNext()) {
                list.add(cursor.getFloat(0));
            }
            return list.getArray();
        }
    }

    /**
     * Return the value of the first column of the first row as double
     *
     * @param db       the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as double
     */
    public static double getDouble(SQLiteDatabase db, String sql, double fallback) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getDouble(0);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as double
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as double or null if no query result
     */
    @Nullable
    public static Double getDouble(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getDouble(0);
            }
            return null;
        }
    }

    /**
     * Return the values of the first column as double
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as double in an array, empty array if no query result
     */
    @NonNull
    public static double[] getDoubleArray(SQLiteDatabase db, String sql) {
        DoubleArray list = new DoubleArray();
        try (Cursor cursor = db.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                list.add(cursor.getDouble(0));
            }
            return list.getArray();
        }
    }

    /**
     * Return the value of the first column of the first row as decimal
     *
     * @param db       the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as decimal
     */
    public static BigDecimal getBigDecimal(SQLiteDatabase db, String sql, BigDecimal fallback) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return DsField.getBigDecimal(cursor, 0);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as decimal
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as decimal or null if no query result
     */
    @Nullable
    public static BigDecimal getBigDecimal(SQLiteDatabase db, String sql) {
        return getBigDecimal(db, sql, null);
    }

    /**
     * Return the values of the first column as decimal
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as decimal in an array, empty array if no query result
     */
    @NonNull
    public static ArrayList<BigDecimal> getBigDecimalArray(SQLiteDatabase db, String sql) {
        ArrayList<BigDecimal> list = new ArrayList<>();
        try (Cursor cursor = db.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                list.add(DsField.getBigDecimal(cursor, 0));
            }
            return list;
        }
    }

    /**
     * Return the value of the first column of the first row as string
     *
     * @param db       the database to read
     * @param sql      the query sql statement
     * @param fallback when there is no data
     * @return value as string
     */
    public static String getString(SQLiteDatabase db, String sql, String fallback) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getString(0);
            }
            return fallback;
        }
    }

    /**
     * Return the value of the first column of the first row as string
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as string or null if no query result
     */
    @Nullable
    public static String getString(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getString(0);
            }
            return null;
        }
    }

    /**
     * Return the values of the first column as string
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as string in an array, empty array if no query result
     */
    @NonNull
    public static ArrayList<String> getStringArray(SQLiteDatabase db, String sql) {
        ArrayList<String> list = new ArrayList<>();
        getStringArray(db, sql, list);
        return list;
    }

    /**
     * Return the values of the first column as string int the given collection
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @param out collection to accept data
     */
    public static void getStringArray(SQLiteDatabase db, String sql, Collection<String> out) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                out.add(cursor.getString(0));
            }
        }
    }

    /**
     * read the first row as map
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return the row data in a map or null if no result
     */
    @Nullable
    public static HashMap<String, String> read(SQLiteDatabase db, String sql) {
        return read(db, sql, new HashMap<>());
    }

    /**
     * read the first row as map
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @param out given map to receive row data
     * @return the given map with row data or null if no result
     */
    @Nullable
    public static <T extends Map<String, String>> T read(SQLiteDatabase db, String sql, T out) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                String[] names = cursor.getColumnNames();
                for (int i = 0; i < names.length; i++) {
                    out.put(names[i], cursor.getString(i));
                }
                return out;
            }
        }
        return null;
    }

    /**
     * read rows as map into the given list
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return rows data in a list, empty if no result
     */
    @NonNull
    public static ArrayList<Map<String, String>> readArray(SQLiteDatabase db, String sql) {
        ArrayList<Map<String, String>> list = new ArrayList<>();
        readArray(db, sql, list);
        return list;
    }

    /**
     * read rows as map into the given collection
     *
     * @param db         the database to read
     * @param sql        the query sql statement
     * @param collection the collection to accept rows data
     */
    public static void readArray(SQLiteDatabase db, String sql, Collection<Map<String, String>> collection) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            String[] names = null;
            Map<String, String> map;
            while (cursor.moveToNext()) {
                if (names == null) names = cursor.getColumnNames();
                collection.add(map = new HashMap<>());
                for (int i = 0; i < names.length; i++) {
                    map.put(names[i], cursor.getString(i));
                }
            }
        }
    }

    /**
     * read the first row as the given type
     *
     * @param db   the database to read
     * @param sql  the query sql statement
     * @param type given type to return
     * @return the row data as object, null if no result
     */
    @Nullable
    public static <T> T read(SQLiteDatabase db, String sql, Class<T> type)
            throws ReflectiveOperationException {
        return read(db, sql, type, null);
    }

    /**
     * read the first row as the given type
     *
     * @param db       the database to read
     * @param sql      the query sql statement
     * @param type     given type to return
     * @param fallback if no result
     * @return the row data as object or return fallback if no result
     */
    @Nullable
    public static <T> T read(SQLiteDatabase db, String sql, Class<T> type, T fallback)
            throws ReflectiveOperationException {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            T t = new InternalDsFactory<>(type).read(cursor, fieldMap);
            cursor.close();
            return (t != null) ? t : fallback;
        }
    }

    /**
     * read rows as the given type in a list
     *
     * @param db   the database to read
     * @param sql  the query sql statement
     * @param type given type to return
     * @return rows data as object in a list, empty if no result
     */
    @NonNull
    public static <T> ArrayList<T> readArray(SQLiteDatabase db, String sql, Class<T> type)
            throws ReflectiveOperationException {
        ArrayList<T> out = new ArrayList<>();
        readArray(db, sql, type, out);
        return out;
    }

    /**
     * read rows as the given type into the given list
     *
     * @param db   the database to read
     * @param sql  the query sql statement
     * @param type given type to return
     * @param out  list to accept data
     */
    public static <T> void readArray(SQLiteDatabase db, String sql, Class<T> type, List<T> out)
            throws ReflectiveOperationException {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            new InternalDsFactory<>(type).readArray(cursor, fieldMap, out);
        }
    }

    /**
     * read rows as the given type into the given list
     *
     * @param db   the database to read
     * @param sql  the query sql statement
     * @param type given type to return
     * @param out  collection to accept data
     */
    public static <T> void readArray(SQLiteDatabase db, String sql, Class<T> type, Collection<T> out)
            throws ReflectiveOperationException {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            new InternalDsFactory<T>(type).readArray(cursor, fieldMap, out);
        }
    }

    //---------------------------------------------------------------------------

    private static Map<Class<?>, DsFieldReader<?>> fieldMap;

    /**
     * add a global field reader
     *
     * @param type   field type
     * @param reader the field reader
     */
    public static <F> void register(Class<F> type, DsFieldReader<F> reader) {
        if (fieldMap == null) fieldMap = new HashMap<>();
        fieldMap.put(type, reader);
    }

    //---------------------------------------------------------------------------

    /**
     * create DsInsReader with a field reader
     *
     * @param type   field type
     * @param reader the field reader
     */
    public static <F> DsInsReader with(Class<F> type, DsFieldReader<F> reader) {
        return new DsInsReader().with(type, reader);
    }

}
