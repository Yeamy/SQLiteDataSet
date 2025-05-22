SQLiteDataSet
===================================
[![](https://img.shields.io/badge/platform-Android-red)](https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase) [![](https://img.shields.io/github/license/Yeamy/SQLiteDataSet?color=green)](https://github.com/Yeamy/SQLiteDataSet/blob/master/LICENSE) [![](https://img.shields.io/maven-central/v/io.github.yeamy/sqlitedataset)](https://mvnrepository.com/artifact/io.github.yeamy/sqlitedataset)

English | [中文](README-CN.md)

This project is a simple tools to deserialize SQLite data to Java Bean on **Android**.

For java Cursor also see [SQLDataSet](https://github.com/Yeamy/SQLiteDataSet/)

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
SQLiteDatabase db = ...;                            // the source
String sql = "SELECT ...";                          // the sql
Fruit apple = DsReader.read(db, sql, Fruit.class);  // read one
ArrayList<Fruit> list = r DsReader.readArray(stmt, sql, Fruit.class);
```

### 3. DsFieldReader
In order to deserialize custom field type, you may define a `DsFieldReader`.

```java
SQLiteDatabase db = ...;                           // the data source

DsFieldReader<Skin> skinReader = new DsFieldReader<>() {

    /**
     * @param cursor query result dataSet
     * @param columnIndex column index in result dataet
     * @return field instance
     */
    Skin read(Cursor cursor, int columnIndex) throws ReflectiveOperationException {
        return new Skin(cursor.getString(columnIndex));
    }
};

// gobal
DsReader.register(Skin.class, skinReader);      // add custom type

Fruit f = reader.read(db, sql, Fruit.class);    // read

// instance
DsInsReader reader = DsReader.with(Skin.class, skinReader)
                             .with(Color.class, colorReader);

Fruit f = reader.read(db, sql, Fruit.class);    // read

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
Data come from same row of Cursor can deserialize into an extra field.

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
