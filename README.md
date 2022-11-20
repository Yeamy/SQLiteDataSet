SQLiteDataSet
===================================
English | [中文](README-CN.md)

This project is a simple tools to deserialize SQLite data to Java Bean on **Android**.

For java ResultSet also see [SQLDataSet](https://github.com/Yeamy/SQLDataSet/)

```groovy
    implementation 'io.github.yeamy:sqlitedataset:1.2'
```

### 1. Annotation
```java
public class Fruit {

    @DsColumn("Name")
    public String name;      // the column in database is "Name"

    public String fullName;  // the column in database can be "fullName" or "full_name"

    @DsIgnore
    public String count;     // ignore this field

    public FruitType type;   // the name of the column is the same as the field
    // regist as custom type (see DsAdapter)

    public Skin skin;        // this field no DsColumn treat as extra type
}
```

### 2. DsReader
Generally, using `DsReader` is an easy and fast way.

```java
Statement stmt = ...;                                 // the source
String sql = "SELECT ...";                            // the sql
Fruit apple = DsReader.read(stmt, sql, Fruit.class);  // read one
ArrayList<Fruit> list = r DsReader.eadArray(stmt, sql, Fruit.class);
```

### 3. DsFactory\<T> & DsAdapter
In order to deserialize custom field type, you may define a `DsFactory` and register a type with `DsAdapter`.

```java
java.sql.ResultSet rs = ...;                           // the data source

DsFactory<Fruit> factory = new DsFactory(Fruit.class); // build a factory

DsAdapter adapter = new DsAdapter() {

    /**
     * @param t
     *           any other base type field has been deserialized
     * @param field
     *           using field.getName() to distinguish same type.
     * @param rs
     *           jdbc select result,
     * @param columnIndex
     *           the index of the target column in ResultSet.
     */
    @Override
    public void read(Object t, Field field, ResultSet rs, int columnIndex) throws SQLException, InstantiationException, IllegalAccessException {
        FruitType type = new FruitType(....);
        field.set(t, type);
    }
};

factory.addAdapter(Type.class, adapter);               // add custom type

Fruit apple = factory.read(rs);                        // read one

factory.readArray(list, rs);                           // read array

List<Fruit> list = new ArrayList<Fruit>();
factory.readArray(list, rs);                           // read array with custom list
```

### 4. DsObserver
If you want to do anything after the Bean read, you can implement `DsObserver.class`, and do it in `onDsFinish()`.

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
Data come from same row of ResultSet can deserialize into an extra field.

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

    // NOTICE：must without annotion DsColumn, field name cannot as same sa column,
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
