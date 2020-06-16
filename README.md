SQLiteDataSet
===================================
English | [中文](README-CN.md)

This project is a simple tools to deserialize SQLite data to Java Bean.

For java ResultSet also see [SQLDataSet](https://github.com/Yeamy/SQLDataSet/)

### 1. Use DsReader
First, create a Java Bean

```java
public class Fruit {

    public String num;       // the column is the same with the field name

    @DsColumn("FruitName")
    public String name;      // the column in database is "FruitName"
    
    @DsIgnore
    public String owner;     //  ignore this field

    ...
}
```

Second, deserialize via `DsReader`.

```java
SQLiteDatabase db = ...;                               // the database
String sql = ...;                                      // the query sql
Fruit apple = DsReader.read(db, sql, Fruit.class);     // read one
ArrayList<Fruit> list = DsReader.readArray(db, sql, Fruit.class);
```
Ok, that's done, easy and fast;

### 2. Extra Type
To deserialize multiple columns in the same row of cursor into one field.

For example, to package *image* and *color* into the same field called *skin*, do like this:

```java
public class Skin {
    
    public String image;
    
    public int color;
}

public class Fruit {
    ...

    public Skin skin; // NOTICE：no annotation DsColumn, and there cannot be a column with the same name as the field
}

```

### 3. Custom Type
    
```java
public class Fruit {
    ...

    public FruitType type; // FruitType is the custom type
}

```
Using `DsFactory` and `DsAdapter` to deserialize custom type field.

```java

DsAdapter adapter = new DsAdapter() {

    /**
     * @param t
     *           any other base type field has been deserialized
     * @param field
     *           using field.getName() to distinguish same type.
     * @param cursor
     *           database query result,
     * @param columnIndex
     *           the index of the target column in Cursor.
     */
    @Override
    public void read(Object t, Field field, Cursor cursor, int columnIndex)
                throws InstantiationException, IllegalAccessException {
        FruitType type = ...;
        // field.set(t, type);
        Fruit f = (Fruit) t;
        f.type = type;
    }
};

SQLiteDatabase db = ...;                                   // the database
DsFactory<Fruit> factory = new DsFactory(Fruit.class);     // build a factory
factory.addAdapter(Type.class, adapter);                   // add custom type
Fruit apple = factory.read(cursor);                        // read one

List<Fruit> list = new ArrayList<Fruit>();
factory.readArray(list, cursor);                           // read array with custom list
```

### 4. DsObserver
If you want to do anything when the Bean has been deserialize, implements `DsObserver.class`, and do it in `onDsFinish()`.

```java
public class Vegetables implements DsObserver {

    @DsColumn("Name")
    public String name;
    ...

    @Override
    public void onDsFinish(){}
}

```

