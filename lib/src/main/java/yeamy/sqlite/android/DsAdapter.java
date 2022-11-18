package yeamy.sqlite.android;

import android.database.Cursor;

import java.lang.reflect.Field;

public interface DsAdapter {

    Object read(Field field, Cursor cursor, int columnIndex)
            throws InstantiationException, IllegalAccessException;

}