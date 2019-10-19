# Mybatis复习
## 一.MBG生成器中的pojoExample的使用

#### 1.简介
example类使用一构造筛选辅助条件的类，理论上使用Example可以构造任何场景的筛选
#### 2.成员变量
- 升序还是降序？
参数格式：字段+空格+asc(desc)
protected String orderByClause;
- 去除重复？
     //true是选择不重复记录
     protected boolean distinct;
- 自定义查询条件？
     //Criteria的集合，集合中对象是由or连接
     protected List<Criteria> oredCriteria;
     //内部类Criteria包含一个Cretiron的集合，
     每一个Criteria对象内包含的Cretiron之间是由AND连接的
     public static class Criteria extends GeneratedCriteria {
         protected Criteria() {
             super(); 
         }
     }
