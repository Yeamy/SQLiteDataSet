package yeamy.sqlite.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
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
     * Returns the value of first row first column as boolean
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
     * Returns the value of first row first column as boolean
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as boolean or null if no query result
     */
    public static Boolean getBoolean(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return DsField.getBoolean(cursor, 0);
            }
            return null;
        }
    }

    /**
     * Returns the value as boolean
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as boolean in an array, empty array if no query result
     */
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
     * Returns the value of first row first column as short
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
     * Returns the value of first row first column as short
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as short or null if no query result
     */
    public static Short getShort(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getShort(0);
            }
            return null;
        }
    }

    /**
     * Returns the value as short
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as short in an array, empty array if no query result
     */
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
     * Returns the value of first row first column as int
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
     * Returns the value as int
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as int in an array, empty array if no query result
     */
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
     * Returns the value of first row first column as long
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
     * Returns the value of first row first column as long
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as long or null if no query result
     */
    public static Long getLong(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getLong(0);
            }
            return null;
        }
    }

    /**
     * Returns the value as long
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as long in an array, empty array if no query result
     */
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
     * Returns the value of first row first column as float
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
     * Returns the value of first row first column as float
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as float or null if no query result
     */
    public static Float getFloat(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getFloat(0);
            }
            return null;
        }
    }

    /**
     * Returns the value as float
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as float in an array, empty array if no query result
     */
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
     * Returns the value of first row first column as double
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
     * Returns the value of first row first column as double
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as double or null if no query result
     */
    public static Double getDouble(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getDouble(0);
            }
            return null;
        }
    }

    /**
     * Returns the value as double
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as double in an array, empty array if no query result
     */
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
     * Returns the value of first row first column as decimal
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
     * Returns the value of first row first column as decimal
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as decimal or null if no query result
     */
    public static BigDecimal getBigDecimal(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return DsField.getBigDecimal(cursor, 0);
            }
            return null;
        }
    }

    /**
     * Returns the value as decimal
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as decimal in an array, empty array if no query result
     */
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
     * Returns the value of first row first column as string
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
     * Returns the value of first row first column as string
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return value as string or null if no query result
     */
    public static String getString(SQLiteDatabase db, String sql) {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToNext()) {
                return cursor.getString(0);
            }
            return null;
        }
    }

    /**
     * Returns the value as string
     *
     * @param db  the database to read
     * @param sql the query sql statement
     * @return values as string in an array, empty array if no query result
     */
    public static ArrayList<String> getStringArray(SQLiteDatabase db, String sql) {
        ArrayList<String> list = new ArrayList<>();
        try (Cursor cursor = db.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                list.add(cursor.getString(0));
            }
            return list;
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
    public static <T> T read(SQLiteDatabase db, String sql, Class<T> type)
            throws InstantiationException, IllegalAccessException {
        return read(db, sql, new DsFactory<>(type), null);
    }

    /**
     * read the first row as the given type
     *
     * @param db       the database to read
     * @param sql      the query sql statement
     * @param factory  factory of given type
     * @param fallback if no result
     * @return the row data as object or return fallback if no result
     */
    public static <T> T read(SQLiteDatabase db, String sql, DsFactory<T> factory, T fallback)
            throws InstantiationException, IllegalAccessException {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            T t = factory.read(cursor);
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
    public static <T> ArrayList<T> readArray(SQLiteDatabase db, String sql, Class<T> type)
            throws InstantiationException, IllegalAccessException {
        ArrayList<T> out = new ArrayList<>();
        readArray(db, sql, new DsFactory<>(type), out);
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
            throws InstantiationException, IllegalAccessException {
        readArray(db, sql, new DsFactory<>(type), out);
    }

    /**
     * read rows as the given type into the given list
     *
     * @param db      the database to read
     * @param sql     the query sql statement
     * @param factory factory of given type
     * @param out     list to accept data
     */
    public static <T> void readArray(SQLiteDatabase db, String sql, DsFactory<T> factory, List<T> out)
            throws InstantiationException, IllegalAccessException {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            factory.readArray(out, cursor);
        }
    }

    /**
     * read rows as the given type into the given list
     *
     * @param db    the database to read
     * @param sql   the query sql statement
     * @param type  given type to return
     * @param limit limit how many rows to read
     * @return rows data as object in a list, empty if no result
     */
    public static <T> ArrayList<T> readArray(SQLiteDatabase db, String sql, Class<T> type, int limit)
            throws InstantiationException, IllegalAccessException {
        ArrayList<T> out = new ArrayList<>();
        readArray(db, sql, new DsFactory<>(type), limit, out);
        return out;
    }

    /**
     * read rows as the given type into the given list
     *
     * @param db      the database to read
     * @param sql     the query sql statement
     * @param factory factory of given type
     * @param limit   limit how many rows to read
     */
    public static <T> void readArray(SQLiteDatabase db, String sql, DsFactory<T> factory, int limit, List<T> out)
            throws InstantiationException, IllegalAccessException {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            factory.readArray(out, cursor, limit);
        }
    }

}
