package yeamy.sqlite.android;

import android.database.Cursor;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;

public interface DsAdapter {

    /**
     * @param field the reflection field
     * @param cursor the query result
     * @param columnIndex the zero-based column index for the given column name,
     *                    or -1 if the column name does not exist.
     * @return the field value
     */
    @Nullable
    Object read(Field field, Cursor cursor, int columnIndex)
            throws InstantiationException, IllegalAccessException;

}