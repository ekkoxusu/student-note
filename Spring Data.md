[TOC]

## Spring Data JPA

### 一. 注解解释

#### @Column

##### ①包含属性

name 

> 表列名 默认为属性或字段名

unique

> 判断是否唯一键 默认为false

nullable

> 是否可为空 默认为true

insertable(updatable)  

> 在保存 (修改) 对象时是否保存 (修改) 该字段 默认为true  [常见情况](https://blog.csdn.net/u011983531/article/details/48731629)

columnDefinition

> 设置创表语句  [常见情况](https://blog.csdn.net/pyd950812/article/details/77879557)

table

> 一般不用,列创建在哪个表

length

> 长度

precision

> 数值总长度

scale

> 小数精度(仅适用于10进制)

##### ②常见用法

```java
@Column(name = "user_name", length = 50, unique = true)
@Column(columnDefinition ="text",length=500,name="user_word")
```

#### @JoinColumn

##### ①包含属性

name 

> one -> many 名字在目标表
>
> many -> one  名字在当前表
>
> many -> many 名字在连接表

foreignKey

> 外键规范 默认应用约束

##### ②常见用法

```java
@JoinColumn(name = "user_id")
```

#### @OneToMany

##### ①包含属性

fetch

> 加载方式 默认懒加载

cascade

> 级联操作,默认不级联操作

mappedBy

> 映射于对应目标方哪个字段 必须

orphanRemoval

> 该字段为true时,当删除当前对象是否删除所有子对象 默认为false

##### ②常见操作

```java
@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
```

#### @ManyToOne

##### ①包含属性

optional

> 关系是否可可选 默认为 true 为false时必须查得出结果

其他属性与OneToMany基本一致

##### ②常见操作

```
@ManyToOne(fetch = FetchType.LAZY)
```

#### @Version

##### ①包含属性

无

##### ②常见操作

```java
@Version
private Long version;
```

#### @Id

##### ①包含属性

无

##### ②常见操作

```
@Id
@GeneratedValue
@Column(name = "id")
protected Long id;
```

#### @GeneratedValue

##### ①包含属性

strategy

> 主键生成策略,默认为AUTO 推荐 TABLE  [原因](https://www.cnblogs.com/lj95801/p/5003580.html)

generator

> 主键生成策略器名字 一般配合 @TableGenerator使用 默认会生成一张表

##### ②常见操作

```
@TableGenerator(name="ID_GENERATOR",
            table="t_id_generator",
            pkColumnName="PK_NAME",
            pkColumnValue="seed_t_customer_id",
            valueColumnName="PK_VALUE",
            allocationSize=20,
            initialValue=10
            )
    @GeneratedValue(strategy=GenerationType.TABLE, generator="ID_GENERATOR")
    @Id
    public Integer getId() {
        return id;
    }
```

#### @GenericGenerator

##### ①包含属性

name

> 主键生成器名字

strategy

> 生成器策略 可用hibernate自带也可自己书写 ps:enhanced-table

parameters

> 可选参数

##### ②常见操作

```java
@GenericGenerator( name="id_gen", strategy="enhanced-table", 
 5    　　parameters = {
 6        　　@Parameter( name = "table_name", value = "enhanced_gen"), 
 7        　　@Parameter( name = "value_column_name", value = "next"), 
 8        　　@Parameter( name = "segment_column_name",value = "segment_name"), 
 9        　　@Parameter( name = "segment_value", value = "emp_seq"),
10        　　@Parameter( name = "increment_size", value = "10"), 
11        　　@Parameter( name = "optimizer",value = "pooled-lo") 
12    　　}) 
```

#### @Temporal

##### ①包含属性

value

> 只用于时间类型上 可设置
>
> 第一种：@Temporal(TemporalType.DATE)——》实体类会封装成日期“yyyy-MM-dd”的 Date类型。
>
> 第二种：@Temporal(TemporalType.TIME)——》实体类会封装成时间“hh-MM-ss”的 Date类型。
>
> 第三种：@Temporal(TemporalType.TIMESTAMP)——》实体类会封装成完整的时间“yyyy-MM-dd hh:MM:ss”的 Date类型。

