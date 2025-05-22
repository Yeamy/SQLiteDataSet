SQLiteDataSet
===================================
[![](https://img.shields.io/badge/platform-Android-red)](https://developer.android.google.cn/reference/android/database/sqlite/SQLiteDatabase) [![](https://img.shields.io/github/license/Yeamy/SQLiteDataSet?color=green)](https://github.com/Yeamy/SQLiteDataSet/blob/master/LICENSE) [![](https://img.shields.io/maven-central/v/io.github.yeamy/sqlitedataset)](https://mvnrepository.com/artifact/io.github.yeamy/sqlitedataset)

[English](README.md) | 中文

这个项目是一个简单的`Android` SQLite读取工具，将数据库中的数据反序列生成对象集。

使用 Java Cursor 可以查看 [SQLDataSet](https://github.com/Yeamy/SQLiteDataSet/)

```groovy
implementation 'io.github.yeamy:sqlitedataset:1.2'
```

### 1. Bean类声明
```java
public class Fruit {

    @DsColumn("Name")
    public String name;      // 声明此参数对应列名为Name

    public String fullName;  // 列名可以是 "fullName" 或者 "full_name"

    @DsIgnore
    public String count;     // 声明此参数不读取
    
    public FruitType type;   // 不添加此声明，取参数名做列名，此参数为自定义类型（见下文 DsAdapter）

    public Skin skin;        // 没有声明DsColumn的参数当做扩展参数处理
}
```

### 2. DsReader
一般情况使用DsReader工具类快速读取已足矣

```java
SQLiteDatabase db = ...;                                 // 数据源
String sql = "SELECT ...";                            // 筛选的SQL语句
Fruit apple = DsReader.read(stmt, sql, Fruit.class);
ArrayList<Fruit> list = DsReader.readArray(db, sql, Fruit.class);
```

### 3. DsFactory\<T> 和 DsAdapter
使用自定义工厂类生产对象，并注册DsAdapter来扩展自定义类型。

```java
SQLiteDatabase db = ...;                           // 数据来源

DsFieldReader<Skin> skinReader = new DsFieldReader<>() {

    /**
     * @param cursor 数据库搜索结果
     * @param columnIndex 对应参数在rs中对应的位置
     * @return 对应需要读取的参数
     */
    Skin read(Cursor cursor, int columnIndex) throws ReflectiveOperationException {
        return new Skin(cursor.getString(columnIndex));
    }
};

// 全局
DsReader.register(Skin.class, skinReader);      // 添加自定义类型

Fruit f = reader.read(db, sql, Fruit.class);    // 读取

// 实例
DsInsReader reader = DsReader.with(Skin.class, skinReader)
        .with(Color.class, colorReader);

Fruit f = reader.read(db, sql, Fruit.class);    // 读取

```


### 4. DsObserver
如果导入DsObserver接口，解析结束后会调用onDsFinish()方法，可以在此方法修改数据。

```java
public class Vegetables implements DsObserver {

    @DsColumn("Name")
    public String name;
    ...
    @Override
    public void onDsFinish(){}
}

```

### 5. 扩展对象
来自Cursor的同一行数据可以被解析到同一实例内。

数据表:

|UserName|Province|CityName|...|
|:-:|:-:|:-:|:-:|
|Nike|Guangdong|Shantou|...|
|...|

通常会采用如下数据类：

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

为了将province和city封装到同个参数内，可以使用如下方式：

```java
public class User {
    @DsColumn("UserName")
    public String name;
    ...
    // 注意：参数不能声明DsColumn，参数名不能与列名重复，
    // 否则只能使用DsAdapter来解析
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
