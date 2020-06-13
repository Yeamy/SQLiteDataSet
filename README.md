SQLiteDataSet
===================================
English | [中文](README-CN.md)

This project is a simple tools to deserialize SQLite data to Java Bean.

For java ResultSet also see [SQLDataSet](https://github.com/Yeamy/SQLDataSet/)

### 1. Annotation
```java
public class Fruit {

    @DsColumn("Name")
    public String name;      // the column in database is "Name"
    
    @DsIgnore
    public String count;     // ignore this field
    
    public FruitType type;   // the name of the column is the same as the field
                             // regist as custom type (see DsAdapter)

    public Skin skin;        // this field no DsColumn treat as extra type
}
```

### 2. DsReader
Generally, using `DsReader` is a easy and fast way.

```java
SQLiteDatabase db = getReadableDatabase();             // the database
String sql = "SELECT ...";                             // the sql
Fruit apple = DsReader.read(db, sql, Fruit.class);     // read one
ArrayList<Fruit> list = DsReader.readArray(db, sql, Fruit.class);
```

### 3. DsFactory\<T> & DsAdapter
The `DsFactory` support base type in sql, such as int, long, String and so on. 

Using `DsAdapter` to deserialize custom type field.

```java
SQLiteDatabase db = getReadableDatabase();              // the database
DsFactory<Fruit> factory = new DsFactory(Fruit.class);  // build a factory

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
        FruitType type = new FruitType(....);
        field.set(t, type);
    }
};

factory.addAdapter(Type.class, adapter);                // add custom type
Fruit apple = factory.read(cursor);                     // read one
factory.readArray(list, cursor);                        // read array

List<Fruit> list = new ArrayList<Fruit>();
factory.readArray(list, cursor);                        // read array with custom list
```

### 4. DsObserver
If you want to do anything when the Bean has been readed, you can implements `DsObserver.class`, and do it in `onDsFinish()`.

```java
public class Vegetables implements DsObserver {

    @DsColumn("Name")
    public String name;
    ...

    @Override
    public void onDsFinish(){}
}

```

### 5. Extra Field
Data come from same row of Cursor can deserialize into a extra field.

source table:

|UserName|Province|CityName|...|
|:-:|:-:|:-:|:-:|
|Nike|Guangdong|Shantou|...|
|...|

Usually, deserialize like this:

```java
public class User {

    @DsColumn("UserName")
    public String name;

    @DsColumn("Province")
    public String province;

    @DsColumn("CityName")
    public String city;
    ...
}

```

to package `province` and `city` into same field `location`, see below:

```java
public class User {

    @DsColumn("UserName")
    public String name;
    ...

    // NOTICE：must without annotation DsColumn, field name cannot as same sa column,
    // otherwise using DsAdapter instead
    public City location;
}

public class City {

    @DsColumn("Province")
    public String province;

    @DsColumn("CityName")
    public String city;
    ...
}

```
