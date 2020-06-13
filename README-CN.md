SQLiteDataSet
===================================
[English](README.md) | 中文

这个项目是一个简单的数据库读取工具，将SQLite中的数据反序列生成对象集。

使用 Java ResultSet 可以查看 [SQLDataSet](https://github.com/Yeamy/SQLDataSet/)

### 1. Annotation
使用声明来描述Bean类，

```java
public class Fruit {

    @DsColumn("Name")
    public String name;      // 声明此参数对应列名为Name
    
    @DsIgnore
    public String count;     // 声明此参数不读取
    
    public FruitType type;   // 不添加此声明，使用参数名做为列名，
                             // 此参数为自定义类型（见下文 DsAdapter）

    public Skin skin;        // 没有声明DsColumn的参数当做扩展参数处理
}

```

### 2. DsReader
一般情况使用DsReader工具类快速读取

```java
SQLiteDatabase db = getReadableDatabase();             // 数据库
String sql = "SELECT ...";                             // 筛选的SQL语句
Fruit apple = DsReader.read(db, sql, Fruit.class);     // 只读取一个
ArrayList<Fruit> list = DsReader.readArray(db, sql, Fruit.class);
```

### 3. DsFactory\<T> 和 DsAdapter
使用自定义工厂类生产对象，添加DsAdapter来扩展自定义类型。

```java
SQLiteDatabase db = getReadableDatabase();             // 数据库
DsFactory<Fruit> factory = new DsFactory(Fruit.class); // 实例化工厂

DsAdapter adapter = new DsAdapter() {

    /**
     * @param t
     *           基础类型的成员变量已读取，可以直接使用
     * @param field
     *           对应需要读取的参数，使用field.getName()区分
     * @param cursor
     *           数据库搜索结果
     * @param columnIndex
     *           对应参数在rs中对应的位置
     */
    @Override
    public void read(Object t, Field field, Cursor cursor, int columnIndex)
                throws InstantiationException, IllegalAccessException {
        FruitType type = new FruitType(....);
        field.set(t, type);
    }
};

factory.addAdapter(Type.class, adapter);               // 添加自定义类型
Fruit apple = factory.read(cursor);                        // 读取单个
factory.readArray(list, cursor);                           // 读取多个

List<Fruit> list = new ArrayList<Fruit>();
factory.readArray(list, cursor);                           // 自定义list
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
来自ResultSet的同一行数据可以被解析到同一实例内。

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
