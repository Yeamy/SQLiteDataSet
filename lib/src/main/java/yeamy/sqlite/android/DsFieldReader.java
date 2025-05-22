package yeamy.sqlite.android;

import android.database.Cursor;

public interface DsFieldReader<T> {

    /**
     * @param cursor jdbc query resultSet
     * @param columnIndex column index in resultSet
     * @return field instance
     */
    T read(Cursor cursor, int columnIndex) throws ReflectiveOperationException;

}
