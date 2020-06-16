SQLiteDataSet
===================================
[English](README.md) | 中文

这个项目是一个简单的Android SQLite读取工具，将数据库中的数据反序列生成对象集。

使用 Java ResultSet 可以查看 [SQLDataSet](https://github.com/Yeamy/SQLDataSet/)

### 1. 直接读取
首先，创建需要解析的Bean类，

```java
public class Fruit {

    public String num;       // 列名与成员变量名相同

    @DsColumn("FruitName")
    public String name;      // 列名与成员变量名不同，声明对应列名为FruitName
    
    @DsIgnore
    public String owner;     // 声明此成员变量不读取

    ...
}

```
接着，使用DsReader快速读取

```java
SQLiteDatabase db = ...;                               // 数据库
String sql = ...;                                      // 筛选的SQL语句
Fruit apple = DsReader.read(db, sql, Fruit.class);     // 只读取一个
ArrayList<Fruit> list = DsReader.readArray(db, sql, Fruit.class);
```
注意：找不到对应列的成员变量将无法被解析

### 2. 扩展成员变量
要将多个列解析到同一成员变量内（以下称为“扩展成员变量”），只需要修改Bean类。

假设要将*Fruit*的*image*跟*color*两列存放在名为*skin*的成员变量内，可以如下操作：

```java
public class Skin {
    
    public String image;

    public int color;
}

public class Fruit {
    ...

    public Skin skin; // 注意：非基本类型成员变量，不声明DsColumn或DsIgnore，均视为扩展成员变量
}

```
### 3. 自定义类型
    
```java
public class Fruit {
    ...

    public FruitType type; // FruitType为自定义类型
}

```
    
使用`DsFactory`，添加`DsAdapter`来实现自定义类型。

```java
DsAdapter adapter = new DsAdapter() {

    /**
     * @param t
     *           基础类型的成员变量已读取，可以直接使用
     * @param field
     *           对应需要读取的成员变量，使用field.getName()区分
     * @param cursor
     *           数据库搜索结果
     * @param columnIndex
     *           成员变量在cursor中对应的列的位置
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

SQLiteDatabase db = ...;                                   // 数据库
DsFactory<Fruit> factory = new DsFactory(Fruit.class);     // 创建工厂
factory.addAdapter(FruitType.class, adapter);              // 添加自定义类型
Fruit apple = factory.read(cursor);                        // 读取单个

List<Fruit> list = new ArrayList<Fruit>();
factory.readArray(list, cursor);                           // 读取多个
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
