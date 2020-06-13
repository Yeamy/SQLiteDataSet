package com.yeamy.sqlite.ds;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yeamy.utils.array.BooleanArray;
import com.yeamy.utils.array.DoubleArray;
import com.yeamy.utils.array.FloatArray;
import com.yeamy.utils.array.IntArray;
import com.yeamy.utils.array.LongArray;
import com.yeamy.utils.array.ShortArray;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DsReader {

    public static boolean getBoolean(SQLiteDatabase db, String sql, boolean fallback) {
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            return DsField.getBoolean(cursor, 0);
        }
        cursor.close();
        return fallback;
    }

    public static boolean[] getBooleanArray(SQLiteDatabase db, String sql) {
        BooleanArray list = new BooleanArray();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            list.add(DsField.getBoolean(cursor, 0));
        }
        cursor.close();
        return list.getArray();
    }

    public static short getShort(SQLiteDatabase db, String sql, byte fallback) {
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            return cursor.getShort(0);
        }
        cursor.close();
        return fallback;
    }

    public static short[] getShortArray(SQLiteDatabase db, String sql) {
        ShortArray list = new ShortArray();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            list.add(cursor.getShort(0));
        }
        cursor.close();
        return list.getArray();
    }

    public static int getInt(SQLiteDatabase db, String sql, int fallback) {
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            return cursor.getInt(0);
        }
        cursor.close();
        return fallback;
    }

    public static int[] getIntArray(SQLiteDatabase db, String sql) {
        IntArray list = new IntArray();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            list.add(cursor.getInt(0));
        }
        cursor.close();
        return list.getArray();
    }

    public static long getLong(SQLiteDatabase db, String sql, long fallback) {
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            return cursor.getLong(0);
        }
        cursor.close();
        return fallback;
    }

    public static long[] getLongArray(SQLiteDatabase db, String sql) {
        LongArray list = new LongArray();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            list.add(cursor.getLong(0));
        }
        cursor.close();
        return list.getArray();
    }

    public static float getFloat(SQLiteDatabase db, String sql, float fallback) {
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            return cursor.getFloat(0);
        }
        cursor.close();
        return fallback;
    }

    public static float[] getFloatArray(SQLiteDatabase db, String sql) {
        FloatArray list = new FloatArray();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            list.add(cursor.getFloat(0));
        }
        cursor.close();
        return list.getArray();
    }

    public static double getDouble(SQLiteDatabase db, String sql, double fallback) {
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            return cursor.getDouble(0);
        }
        cursor.close();
        return fallback;
    }

    public static double[] getDoubleArray(SQLiteDatabase db, String sql) {
        DoubleArray list = new DoubleArray();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            list.add(cursor.getDouble(0));
        }
        cursor.close();
        return list.getArray();
    }

    public static BigDecimal getBigDecimal(SQLiteDatabase db, String sql, BigDecimal fallback) {
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            return DsField.getBigDecimal(cursor, 0);
        }
        cursor.close();
        return fallback;
    }

    public static ArrayList<BigDecimal> getBigDecimalArray(SQLiteDatabase db, String sql) {
        ArrayList<BigDecimal> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            list.add(DsField.getBigDecimal(cursor, 0));
        }
        cursor.close();
        return list;
    }

    public static String getString(SQLiteDatabase db, String sql, String fallback) {
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            return cursor.getString(0);
        }
        cursor.close();
        return fallback;
    }

    public static ArrayList<String> getStringArray(SQLiteDatabase db, String sql) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            list.add(cursor.getString(0));
        }
        cursor.close();
        return list;
    }

    public static <T> T read(SQLiteDatabase db, String sql, Class<T> clz)
            throws InstantiationException, IllegalAccessException {
        return read(db, sql, new DsFactory<>(clz), null);
    }

    public static <T> T read(SQLiteDatabase db, String sql, DsFactory<T> factory, T fallback)
            throws InstantiationException, IllegalAccessException {
        Cursor cursor = db.rawQuery(sql, null);
        T t = factory.read(cursor);
        cursor.close();
        return (t != null) ? t : fallback;
    }

    public static <T> ArrayList<T> readArray(SQLiteDatabase db, String sql, Class<T> clz)
            throws InstantiationException, IllegalAccessException {
        ArrayList<T> out = new ArrayList<>();
        readArray(db, sql, new DsFactory<>(clz), out);
        return out;
    }

    public static <T> void readArray(SQLiteDatabase db, String sql, Class<T> clz, List<T> out)
            throws InstantiationException, IllegalAccessException {
        readArray(db, sql, new DsFactory<>(clz), out);
    }

    public static <T> void readArray(SQLiteDatabase db, String sql, DsFactory<T> factory, List<T> out)
            throws InstantiationException, IllegalAccessException {
        Cursor cursor = db.rawQuery(sql, null);
        factory.readArray(out, cursor);
        cursor.close();
    }

    public static <T> ArrayList<T> readArray(SQLiteDatabase db, String sql, Class<T> clz, int limit)
            throws InstantiationException, IllegalAccessException {
        ArrayList<T> out = new ArrayList<>();
        readArray(db, sql, new DsFactory<>(clz), limit, out);
        return out;
    }

    public static <T> void readArray(SQLiteDatabase db, String sql, DsFactory<T> factory, int limit, List<T> out)
            throws InstantiationException, IllegalAccessException {
        Cursor cursor = db.rawQuery(sql, null);
        factory.readArray(out, cursor, limit);
        cursor.close();
    }

}
