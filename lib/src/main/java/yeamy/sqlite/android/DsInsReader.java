package yeamy.sqlite.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * instance type of DsReader
 */
public class DsInsReader {
    private final Map<Class<?>, DsFieldReader<?>> fieldMap = new HashMap<>();

    /**
     * add a field reader for current instance.
     *
     * @param type   field type
     * @param reader the field reader
     */
    public <F> DsInsReader with(Class<F> type, DsFieldReader<F> reader) {
        fieldMap.put(type, reader);
        return this;
    }

    /**
     * read the first row as the given type
     *
     * @param db the database to read
     * @param sql  the query sql statement
     * @param type given type to return
     * @return the row data as object, null if no result
     */
    public <T> T read(SQLiteDatabase db, String sql, Class<T> type)
            throws ReflectiveOperationException {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            return new InternalDsFactory<>(type).read(cursor, fieldMap);
        }
    }

    /**
     * read rows as the given type in a list
     *
     * @param db the database to read
     * @param sql  the query sql statement
     * @param type given type to return
     * @return rows data as object in a list, empty if no result
     */
    public <T> ArrayList<T> readArray(SQLiteDatabase db, String sql, Class<T> type)
            throws ReflectiveOperationException {
        ArrayList<T> out = new ArrayList<>();
        readArray(db, sql, type, out);
        return out;
    }

    /**
     * read rows as the given type into the given list
     *
     * @param db the database to read
     * @param sql  the query sql statement
     * @param type given type to return
     * @param out  list to accept data
     */
    public <T> void readArray(SQLiteDatabase db, String sql, Class<T> type, Collection<T> out)
            throws ReflectiveOperationException {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            new InternalDsFactory<>(type).readArray(cursor, fieldMap, out);
        }
    }

    /**
     * read rows as the given type into the given list
     *
     * @param db  the database to read
     * @param sql   the query sql statement
     * @param type  given type to return
     * @param limit limit how many rows to read
     * @return rows data as object in a list, empty if no result
     */
    public <T> ArrayList<T> readArray(SQLiteDatabase db, String sql, Class<T> type, int limit)
            throws ReflectiveOperationException {
        ArrayList<T> out = new ArrayList<>(limit);
        readArray(db, sql, type, out, limit);
        return out;
    }

    /**
     * read rows as the given type into the given list
     *
     * @param db  the database to read
     * @param sql   the query sql statement
     * @param type  given type to return
     * @param limit limit how many rows to read
     */
    public <T> void readArray(SQLiteDatabase db, String sql, Class<T> type, Collection<T> out, int limit)
            throws ReflectiveOperationException {
        try (Cursor cursor = db.rawQuery(sql, null)) {
            new InternalDsFactory<>(type).readArray(cursor, fieldMap, out, limit);
        }
    }
}
