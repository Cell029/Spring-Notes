# 一. 前置

## 1. OCP 开闭原则

在 Spring 框架中,OCP（开闭原则）是面向对象设计的一个重要原则,即对扩展开放,对修改关闭,系统应该在不修改已有代码的情况下,允许行为的扩展.
最初的 MVC 代码就违背了 OCP 开闭原则,控制层、业务层、数据访问层往往通过硬编码方式相互依赖,当某一层的代码修改时,依赖这层的代码也会受到影响,所以引入了 Spring 框架来解决这种问题,降低每一层之间代码的耦合度

****
## 2. 依赖倒置原则 DIP

定义: 高层模块不应该依赖于低层模块,二者都应该依赖于抽象.抽象不应该依赖于细节,细节应该依赖于抽象

例如以下代码,就没有使用依赖倒置原则: OrderService 依赖 UserRepository 这个实现类,
若后续需更换为 JpaUserRepository 或 MongoUserRepository,必须修改 OrderService 的代码

```java
public class OrderService {
    private UserRepository userRepository = new UserRepository(); // 直接依赖实现类

    public void processOrder() {
        userRepository.save(); // 逻辑耦合
    }
}
public class UserRepository {
    public void save() {
        System.out.println("保存用户数据");
    }
}
```

****
## 3. 控制反转 IOC

控制反转 IoC 是一种设计思想，它将对象之间的依赖关系从代码中反转出去，由容器来管理对象的创建、依赖关系的维护和生命周期控制

```java
UserService userService = new UserService(); // 自己创建对象

// 使用 Spring 后
@Autowired
private UserService userService; // 由 Spring 创建并注入
```

依赖注入 DI:

在 Spring 中，控制反转的具体实现方式就是依赖注入,Spring 会在运行时自动将所依赖的 Bean 注入到目标对象中,常见的三种注入方式:

- 构造器注入:通过构造方法注入依赖，推荐用于必须依赖的注入
- set:通过 setter 方法注入，可选依赖
- 字段注入:直接通过属性注解注入（不推荐于大项目中）

> 所以通过 Spring 框架可以解决 MVC 架构模式中的高耦合现象,让程序面向接口编程, 提高整体的扩展性

****
# 二. Spring 概述

## 1. 简介

Spring 是一个开源的、轻量级的 Java 企业级开发框架，它以“控制反转（IoC）”和“面向切面编程（AOP）”为核心，
旨在简化企业应用程序的开发，提升代码的可维护性、可扩展性与可测试性

****
## 2. Spring 的八大模块

1. Spring Core（核心容器）

这是Spring框架最基础的部分，它提供了依赖注入（DependencyInjection）特征来实现容器对Bean的管理。
核心容器的主要组件是 BeanFactory，BeanFactory 是工厂模式的一个实现，是任何 Spring 应用的核心。它使用 IoC 将应用配置和依赖从实际的应用代码中分离出来。

2. Spring AOP（面向切面编程）

提供了一个JDBC的抽象层和异常层次结构，消除了烦琐的JDBC编码和数据库厂商特有的错误代码解析，用于简化JDBC。

3. Spring AOP（面向切面编程）

Spring 在它的 AOP 模块中提供了对面向切面编程的丰富支持，Spring AOP 模块为基于 Spring 的应用程序中的对象提供了事务管理服务。
通过使用 Spring AOP，不用依赖组件，就可以将声明性事务管理集成到应用程序中，可以自定义拦截器、切点、日志等操作。

4. Spring ORM（对象关系映射）

Spring 并不试图实现它自己的 ORM 解决方案，而是为几种流行的 ORM 框架提供了集成方案，
包括 Hibernate、JDO 和 iBATIS SQL 映射，这些都遵从 Spring 的通用事务和 DAO 异常层次结构。

5. Spring Web MVC

Spring 为构建 Web 应用提供了一个功能全面的 MVC 框架。虽然 Spring 可以很容易地与其它 MVC 框架集成，
例如 Struts，但 Spring 的 MVC 框架使用 IoC 对控制逻辑和业务对象提供了完全的分离。

6. Spring Web（Web 集成）

Web 上下文模块建立在应用程序上下文模块之上，为基于 Web 的应用程序提供了上下文，提供了 Spring 和其它 Web 框架的集成，
比如 Struts、WebWork。还提供了一些面向服务支持，例如：实现文件上传的 multipart 请求。

7. Spring Context（应用上下文）

这个模块扩展了 BeanFactory，增加了对国际化（I18N）消息、事件传播、验证的支持。
另外提供了许多企业服务，例如电子邮件、JNDI 访问、EJB集成、远程以及时序调度（scheduling）服务。
也包括了对模版框架例如 Velocity 和 FreeMarker 集成的支持

8. Spring WebFlux 模块

Spring Framework 中包含的原始 Web 框架 Spring Web MVC 是专门为 Servlet API 和 Servlet 容器构建的。
反应式堆栈 Web 框架 Spring WebFlux 是在 5.0 版的后期添加的。它是完全非阻塞的，支持反应式流(Reactive Stream)背压，
并在Netty，Undertow和Servlet 3.1+容器等服务器上运行。

****
## 3. Spring 的特点

1. 轻量

- 从大小与开销两方面而言 Spring 都是轻量的。完整的 Spring 框架可以在一个大小只有 1MB 多的 JAR 文件里发布。并且 Spring 所需的处理开销也是微不足道的。
并且 Spring 是非侵入式的：Spring 应用中的对象不依赖于 Spring 的特定类。

2. 控制反转

- Spring 通过一种称作控制反转（IoC）的技术促进了松耦合。当应用了 IoC，一个对象依赖的其它对象会通过被动的方式传递进来，
而不是这个对象自己创建或者查找依赖对象。可以认为 IoC 与 JNDI 相反,不是对象从容器中查找依赖，而是容器在对象初始化时不等对象请求就主动将依赖传递给它。

3. 面向切面

- Spring 提供了面向切面编程的丰富支持，允许通过分离应用的业务逻辑与系统级服务（例如审计（auditing）和事务（transaction）管理）进行内聚性的开发。
应用对象只实现它们应该做的——完成业务逻辑——仅此而已。它们并不负责（甚至是意识）其它的系统级关注点，例如日志或事务支持。

4. 容器

- Spring 框架作为一个容器，负责管理应用对象（Bean）的配置和生命周期。在这个意义上，Spring 容器能够根据配置控制每个 Bean 的创建方式,可以基于可配置的原型（prototype）作用域，也可以配置为单例（singleton），
即整个容器中只创建一个共享实例，或者其它作用域如请求作用域、会话作用域等。此外，Spring 容器还负责管理 Bean 之间的依赖关系和相互关联，实现对象的自动装配。

5. 框架

- Spring可以将简单的组件配置、组合成为复杂的应用。在Spring中，应用对象被声明式地组合，典型地是在一个XML文件里。
Spring也提供了很多基础功能（事务管理、持久化框架集成等等），将应用逻辑的开发留给了你。

****
# 三. 入门程序

1. bean 标签的 id 属性不可以重复,因为 Spring 容器是一个 Map 结构,id 是唯一的 key

2. 当 Spring 启动时会扫描 XML 或注解配置，读取每一个 Bean 的定义信息,然后使用反射机制,根据配置的 class 名称调用该对象的无参构造器创建对象,如果没有无参构造器则会报错:

```java
Class<?> clazz = Class.forName("com.cell.spring6.first_code.bean.User");
Object obj = clazz.getDeclaredConstructor().newInstance();
```

3. 在 xml 文件中可以使用 JDK 中的类,例如: java.util.Date

4. 可以使用带类型参数的 getBean() 方法对某个类进行向下转型:

```java
User user = applicationContext.getBean("userBean", User.class);
```

****
# 四. Spring 对 IoC 的实现

## 1. 依赖注入

### 1.1 set 注入

set 注入是基于 set 方法实现的，底层会通过反射机制调用属性对应的 set 方法然后给属性赋值(此时对象已被实例化,所以需要无参构造器):

```xml
<bean id="userDaoBean" class="com.cell.spring6.first_code.dao.UserDaoImpl"/>

<bean id="userServiceBean" class="com.cell.spring6.first_code.service.UserService">
    <property name="userDaoImpl" ref="userDaoBean"/>
</bean>

<!--等价于-->
<bean id="userServiceBean" class="com.cell.spring6.first_code.service.UserService">
<property name="userDaoImpl">
    <ref bean="userDaoBean"/>
    ...
</property>
</bean>
```

通过 property 标签获取到属性名 userDaoImpl, 然后通过属性名推断出 set 方法名 setUserDaoImpl() ,通过反射机制调用这个 set 方法给 UserService 类中的 userDaoImpl 属性赋值,
ref 是要注入的 bean 对象的 id,通过这个标签完成 bean 的装配,需要注意的是 name 标签的内容是与 set 方法名对应的,如果 UserService 类中的 set 方法名为 setAbc(),
那么 name 属性中的内容就要变成 `name="abc"`,当然 ref 属性可以有多个,也就是对应多个 set 方法

****
### 1.2 构造注入

通过调用构造方法来给属性赋值(对象实例化过程中执行,此时可以不提供无参构造器):

```xml
<bean id="orderDaoBean" class="com.cell.spring6.first_code.dao.OrderDao"/>
<bean id="orderServiceBean" class="com.cell.spring6.first_code.service.OrderService">
    <!--第一个参数下标是0-->
    <constructor-arg index="0" ref="orderDaoBean"/>
    <!--第二个参数下标是1-->
    <constructor-arg index="1" ref="userDaoBean"/>
    
    <!--使用属性名-->
    <constructor-arg name="orderDao" ref="orderDaoBean"/>
    <constructor-arg name="userDaoImpl" ref="userDaoBean"/>

    <!--只使用 ref-->
    <constructor-arg ref="orderDaoBean"/>
    <constructor-arg ref="userDaoBean"/>
</bean>
```

使用构造注入时需要配置对应的参数,可以使用下标,也可也使用属性名,或者只使用 ref 标签(Spring 会自动进行类型推断)

****
### 1.3 set 注入专题

#### 1. 注入外部 Bean 和内部 Bean

```xml

<bean id="orderDaoBean" class="com.cell.spring6.first_code.dao.OrderDao"/>
        <!--外部 bean-->
<bean id="orderServiceBean" class="com.cell.spring6.first_code.service.OrderService">
    <property name="orderDao" ref="orderDaoBean"/>
</bean>
        <!--内部 bean-->
<bean id="orderServiceBean2" class="com.cell.spring6.first_code.service.OrderService">
    <property name="orderDao">
        <bean class="com.cell.spring6.first_code.dao.OrderDao"/>
    </property>
</bean>
```

****
#### 2. 注入简单类型

如果给简单类型赋值，使用 value 属性或 value 标签,而不是 ref,

```xml
<bean id="userBean" class="com.cell.spring6.first_code.bean.User">
    <property name="username" value="张三"/>
    <property name="password" value="123"/>
    <property name="age" value="20"/>
</bean>
```

简单类型包括:

```java
public class BeanUtils{
    //.......
	public static boolean isSimpleProperty(Class<?> type) {
		Assert.notNull(type, "'type' must not be null");
		return isSimpleValueType(type) || (type.isArray() && isSimpleValueType(type.getComponentType()));
	}
    
	public static boolean isSimpleValueType(Class<?> type) {
		return (Void.class != type && void.class != type &&
				(ClassUtils.isPrimitiveOrWrapper(type) ||
				Enum.class.isAssignableFrom(type) ||
				CharSequence.class.isAssignableFrom(type) ||
				Number.class.isAssignableFrom(type) ||
				Date.class.isAssignableFrom(type) ||
				Temporal.class.isAssignableFrom(type) ||
				URI.class == type ||
				URL.class == type ||
				Locale.class == type ||
				Class.class == type));
	}
    //........
}
```

- **基本数据类型**
- **基本数据类型对应的包装类**
- **String或其他的CharSequence子类**
- **Number子类**
- **Date子类**
- **Enum子类**
- **URI**
- **URL**
- **Temporal子类**
- **Locale**
- **Class**
- **另外还包括以上简单值类型对应的数组类型。**

需要注意的是: Java.util.Date 虽然被归并为简单类型(如果把 Date 当做简单类型的话，日期字符串格式不能随便写。格式必须符合 Date 的 toString() 方法格式),但实际使用中还是用 ref 的形式比较合适:

```xml
<bean id="birthDate" class="java.util.Date">
    <constructor-arg value="2024/01/01"/>
</bean>
<bean id="user" class="com.cell.spring6.first_code.bean.User">
    <property name="birthDate" ref="birthDate"/>
</bean>
```

简单类型的注入主要是用来给数据源的属性值赋值的:

```xml
<bean id="myDataSourceBean" class="com.cell.spring6.first_code.jdbc.MyDataSource">
    <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/spring-notes"/>
    <property name="username" value="root"/>
    <property name="password" value="123"/>
</bean>
```

****
#### 3. 级联属性赋值

原始写法:

```xml
<bean id="clazzBean" class="com.cell.spring6.first_code.bean.Clazz">
    <property name="name" value="高三一班"/>
</bean>

<bean id="studentBean" class="com.cell.spring6.first_code.bean.Student">
    <property name="name" value="张三"/>
    <property name="clazz" ref="clazzBean"/>
</bean>
```

级联写法:使用这种方法需要在 clazz 中添加 get 方法,并且配置的顺序不能颠倒

```xml
<bean id="clazzBean" class="com.cell.spring6.first_code.bean.Clazz"/>

<bean id="studentBean" class="com.cell.spring6.first_code.bean.Student">
    <property name="name" value="张三"/>
    <property name="clazz" ref="clazzBean"/>
    <property name="clazz.name" value="高三一班"/>
</bean>
```

****
#### 4. 注入数组

如果数组中放的是简单类型,就使用 value;如果放的是非简单类型就使用 ref:

```xml
<bean id="person" class="...">
    <property name="favariteFoods">
        <array>
            <value>鸡排</value>
            <value>汉堡</value>
            <value>鹅肝</value>
        </array>
    </property>
</bean>
```

```xml

<bean id="goods1" class="...">
    <property name="name" value="西瓜"/>
</bean>

<bean id="goods2" class="...">
    <property name="name" value="苹果"/>
</bean>

<bean id="order" class="...">
    <property name="goods">
        <array>
            <!--这里使用ref标签即可-->
            <ref bean="goods1"/>
            <ref bean="goods2"/>
        </array>
    </property>
</bean>
```

****
#### 5. 注入 List 和 Set 集合

注入 List:

```xml
<bean id="peopleBean" class="...">
    <property name="names">
        <list>
            <value>铁锤</value>
            <value>张三</value>
            <value>张三</value>
            <value>张三</value>
            <value>狼</value>
        </list>
    </property>
</bean>
```

注入 Set:

```xml

<bean id="peopleBean" class="...">
    <property name="phones">
        <set>
            <!--非简单类型可以使用ref，简单类型使用value-->
            <value>110</value>
            <value>110</value>
            <value>120</value>
            <value>120</value>
            <value>119</value>
            <value>119</value>
        </set>
    </property>
</bean>
```

****
#### 6. 注入 Map 和 Properties 集合

```xml
<bean id="peopleBean" class="...">
    <property name="addrs">
        <map>
            <!--如果key不是简单类型，使用 key-ref 属性-->
            <!--如果value不是简单类型，使用 value-ref 属性-->
            <entry key="1" value="北京大兴区"/>
            <entry key="2" value="上海浦东区"/>
            <entry key="3" value="深圳宝安区"/>
        </map>
    </property>
</bean>
```

```xml
<bean id="configService" class="...">
    <property name="settings">
        <props>
            <prop key="encoding">UTF-8</prop>
            <prop key="timeout">3000</prop>
            <prop key="url">http://baidu.com</prop>
        </props>
    </property>
</bean>
```

****
#### 7. 注入 null 和空字符串

注入 null:

```xml

<bean id="vipBean" class="...">
    <property name="email">
        <null/>
    </property>
</bean>

<!--或者什么都不写-->
<bean id="vipBean" class="..."/>
```

注入空字符串:

```xml

<bean id="userBean" class="...">
    <!--空串的第一种方式-->
    <!--<property name="name" value=""/>-->
    <!--空串的第二种方式-->
    <property name="name">
        <value/>
    </property>
</bean>
```
#### 8. 注入特殊符号

XML中有5个特殊字符，分别是：<、>、'、"、&,这些会被当做 XML 语法的一部分进行解析，如果这些特殊符号直接出现在注入的字符串当中则会报错

第一种:使用转义字符代替特殊符号,分号不能省略

| 字符  | 转义形式   |
| --- | ------ |
| `<` | `&lt;` | 
| `>` | `&gt;` |
| `&` | `&amp;` |
| `"` | `&quot;` |
| `'` | `&apos;` |

第二种:使用 `<![CDATA[]]>` 注入

因为 value 属性只能接收普通字符串，XML 不会解析属性值中的 <![CDATA[]]>，所以它会当成普通文本解析

```xml
<bean id="configBean" class="...">
    <property name="result">
        <!--这种方式只能只能使用 value 标签,不能使用 value 属性赋值-->
        <value><![CDATA[ 2 < 3 ]]></value>
    </property>
</bean>
        <!-- 这种方式是错误的 -->
        <property name="result" value="<![CDATA[ 2 < 3 ]]>"/>
```

****
### 1.4 p 命名空间注入

p 命名空间注入是 Spring 提供的一种更简洁的配置方式，用来代替传统的 `<property>` 标签注入属性值,但本质上还是 set 注入,使用 p 命名空间的前提是必须声明 p 命名空间,即在头部加入:
`xmlns:p="http://www.springframework.org/schema/p"`

简单类型:

```xml
<bean id="user" class="com.cell.spring6.first_code.bean.User" p:username="Tom" p:age="20"/>
```

非简单类型:

```xml
<bean id="address" class="..." p:city="Beijing"/>

<bean id="user" class="com.cell.spring6.first_code.bean.User" p:username="Tom" p:address-ref="address"/>
```

****
### 1.5 c 命名空间注入

c 命名空间注入是 Spring 提供的一种更简洁的方式，用来进行构造方法注入（Constructor Injection），它是 `<constructor-arg>` 标签的简化写法,使用的前提是在头部添加:
`xmlns:c="http://www.springframework.org/schema/c"`

简单类型:

```xml
<bean id="user" class="com.cell.spring6.first_code.bean.User" c:username="Tom" c:age="20"/>
```

非简单类型:

```xml
<bean id="address" class="..." c:city="Beijing"/>

<bean id="user" class="com.cell.spring6.first_code.bean.User" c:username="Tom" c:address-ref="address"/>
```

****
### 1.6 util 命名空间注入

Spring 的 util 命名空间是为了更方便地在 XML 配置中定义常用的集合类型对象,它是对原始 `<list>`、`<set>` 等集合配置的封装,使用前提是在头部添加:
`xmlns:util="http://www.springframework.org/schema/util"` 和 `xsi:schemaLocation="http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd"`

```xml
<util:list id="bookList">
    <value>Java</value>
    <value>Spring</value>
    <value>MySQL</value>
</util:list>

<bean id="library" class="...">
    <property name="books" ref="bookList"/>
</bean>
```

定义 Properties bean:

```xml
<util:properties id="configProperties">
    <prop key="url">jdbc:mysql://localhost:3306/spring-notes</prop>
    <prop key="username">root</prop>
    <prop key="password">123</prop>
</util:properties>
```

```xml
<!--通过 set 方法给 config 属性赋值-->
<bean id="myDataSource" class="com.cell.spring6.first_code.jdbc.DataSourceConfig">
    <property name="config" ref="configProperties"/>
</bean>
```

****
### 1.7 基于 XML 的自动装配

1. 根据名称自动装配

userDao 是 userService 的属性,并且 set 方法的名称是与之对应的,才能这样使用

```xml
<bean id="userDao" class="..."/>
<bean id="userService" class="..." autowire="byName"/>
```

2. 根据类型自动装配

只要 userService 里有这些对应的类型的属性,就可以使用

```xml
<bean class="com.cell.spring6.first_code.bean.User"/>
<bean class="com.cell.spring6.first_code.bean.Student"/>

<bean id="userService" class="..." autowire="byType"/>
```

****
### 1.8 Spring 引入外部属性配置文件

```xml
<!--使用 property-placeholder 的 location 属性来指定配置文件的路径-->
<context:property-placeholder location="jdbc.properties"/>

<bean id="dataSource" class="com.cell.spring6.first_code.jdbc.MyDataSource">
    <property name="driver" value="${jdbc.driver}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>
```

****
# 五. Bean 的作用域

## 1. singleton

singleton 是 Spring 中的默认作用域,它的含义是：每个 Spring IoC 容器中，某个 Bean 定义对应一个且仅有一个 Bean 实例，
并且这个实例会被缓存在 Spring 容器中，在 new ClassPathXmlApplicationContext 时就会实例化对象, 无论调用多少次 getBean()，返回的都是同一个对象,
当容器关闭时会自动销毁

```xml
<!--如果不显式写 scope 属性，它默认就是 singleton-->
<bean id="springBean" class="com.cell.spring6.first_code.bean.SpringBean" scope="singleton"/>
```

工作原理:

当 Spring 容器启动并初始化时开始扫描配置中的所有 Bean ,对 `scope = "singleton"` 的 Bean 提前进行实例化(预实例化),
然后把这些 Bean 实例放入一个单例缓存池（Map）中，键是 Bean 的名称，值是对象实例,以后每次调用 `getBean("beanName")`，都直接从这个 Map 中返回实例

****
## 2. prototype

prototype（原型作用域）表示每次从 Spring 容器中获取 Bean 时，都会创建一个全新的对象实例,也就是说 Spring 容器不会缓存这个 Bean 的实例，
每次调用 getBean()都会重新创建并返回一个新的实例(完成属性的注入后才能完成实例化), Spring 不管理销毁, 需要手动销毁

```xml
<bean id="springBean2" class="com.cell.spring6.first_code.bean.SpringBean" scope="prototype"/>
```

****
## 3. 其它 scope

scope属性的值不止两个，它一共包括8个选项：

- singleton：默认的，单例。
- prototype：原型。每调用一次 getBean() 方法则获取一个新的 Bean 对象, 或每次注入的时候都是新对象
- request：一个请求对应一个 Bean。**仅限于在 WEB 应用中使用**。
- session：一个会话对应一个 Bean。**仅限于在 WEB 应用中使用**。
- global session：**portlet应用中专用的**。如果在 Servlet 的 WEB 应用中使用 global session的话，和 session 一个效果。（portlet 和 servlet 都是规范。servlet 运行在 servlet容器中，例如 Tomcat。portlet 运行在 portlet 容器中。）
- application：一个应用对应一个Bean。**仅限于在 WEB 应用中使用。**
- websocket：一个 websocket 生命周期对应一个 Bean。**仅限于在 WEB 应用中使用。**
- 自定义scope：很少使用

****
## 4. 自定义 scope

spring 内置了线程范围的类：org.springframework.context.support.SimpleThreadScope，可以直接用

```xml
<!--自定义一个 scope, 例如: 一个线程对应一个 Bean-->
<bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
    <property name="scopes">
        <map>
            <entry key="myThread">
                <bean class="org.springframework.context.support.SimpleThreadScope"/>
            </entry>
        </map>
    </property>
</bean>
<!--使用自定义的 scope-->
<bean id="sb" class="com.cell.spring6.first_code.bean.SpringBean" scope="myThread"/>
```

****
# 六. GoF 之工厂模式

GoF 23 种设计模式可分为三大类：

- **创建型**（5个）：解决对象创建问题。
    - 单例模式
    - 工厂方法模式
    - 抽象工厂模式
    - 建造者模式
    - 原型模式
- **结构型**（7个）：一些类或对象组合在一起的经典结构。
    - 代理模式
    - 装饰模式
    - 适配器模式
    - 组合模式
    - 享元模式
    - 外观模式
    - 桥接模式
- **行为型**（11个）：解决类或对象之间的交互问题。
    - 策略模式
    - 模板方法模式
    - 责任链模式
    - 观察者模式
    - 迭代子模式
    - 命令模式
    - 备忘录模式
    - 状态模式
    - 访问者模式
    - 中介者模式
    - 解释器模式

****
## 1. 工厂模式的三种形态

- 第一种：简单工厂模式（Simple Factory）：不属于 23 种设计模式之一,简单工厂模式又叫做静态工厂方法模式,是工厂方法模式的一种特殊实现
- 第二种：工厂方法模式（Factory Method）：是 23 种设计模式之一。
- 第三种：抽象工厂模式（Abstract Factory）：是 23 种设计模式之一。

****
## 2. 简单工厂模式

```text
              +----------------+
              |   工厂类       |
              |  SimpleFactory |
              +--------+-------+
                       |
         +-------------+-------------+
         |                           |
+--------v--------+        +--------v--------+
|   ProductA      |        |   ProductB      |
+-----------------+        +-----------------+
```

1. 定义产品接口和实现类

```java
// 抽象产品角色
public interface MessageService {
    void sendMessage(String msg);
}

// 具体产品角色
public class EmailService implements MessageService {
    public void sendMessage(String msg) {
        System.out.println("发送邮件：" + msg);
    }
}

public class SmsService implements MessageService {
    public void sendMessage(String msg) {
        System.out.println("发送短信：" + msg);
    }
}
```

2. 创建简单工厂类

```java
// 通过传递参数决定创建哪个具体产品角色
public class MessageFactory {
    public static MessageService createService(String type) {
        if ("email".equalsIgnoreCase(type)) {
            return new EmailService();
        } else if ("sms".equalsIgnoreCase(type)) {
            return new SmsService();
        }
        throw new IllegalArgumentException("不支持的类型：" + type);
    }
}
```

优点:

- 客户端只需传入参数就可以获取对象，无需了解创建细节
- 对象创建逻辑集中，便于管理和维护
- 扩展容易，可以通过参数选择不同实现

缺点:

- 不符合开闭原则（新增产品需要修改工厂代码）
- 所有对象的创建逻辑都集中在一个工厂类里,这会导致工厂类的代码会变得越来越复杂,代码难以维护
- 不易支持复杂对象的依赖注入和生命周期管理（Spring 正是为了解决这些问题而出现）

****
## 3. 工厂方法模式

将对象的创建延迟到子类中进行，由子类来决定实例化哪个类，从而达到代码解耦、符合开闭原则的目的,即简单工厂中工厂类自己创建所有对象，而工厂方法把创建交给子类来做

角色组成:

- Product（抽象产品）：定义产品接口
- ConcreteProduct（具体产品）：实现具体产品类
- Factory（抽象工厂）：定义创建产品的方法
- ConcreteFactory（具体工厂）：实现工厂接口，创建具体产品

```lua
           +----------------------+
           |   Product            |<------------------+
           +----------------------+                   |
           |  <<interface>>       |                   |
           +----------------------+                   |
                     ▲                                |
     +---------------+---------------+                |
     |                               |                |
+----v-----+                   +-----v----+     +------v------+
|ProductA  |                   |ProductB  |     |Product...   |
+----------+                   +----------+     +-------------+

           +------------------------+
           |    Creator             |<------------------+
           +------------------------+                   |
           | + factoryMethod():Product|                 |
           +------------------------+                   |
                     ▲                                 |
     +---------------+----------------+                |
     |                                |                |
+----v----------------+     +---------v----------+   +--v----------------+
| ConcreteCreatorA    |     | ConcreteCreatorB   |   | ConcreteCreator...|
+---------------------+     +--------------------+   +-------------------+
| + factoryMethod()   |     | + factoryMethod()  |   | + factoryMethod() |
+---------------------+     +--------------------+   +-------------------+

说明：
- `Product`：抽象产品，定义产品的接口
- `ProductA/ProductB`：具体产品，实现 Product 接口
- `Creator`：抽象工厂类，定义工厂方法 `factoryMethod()`，返回 `Product`
- `ConcreteCreatorA/B/...`：具体工厂，实现 `factoryMethod()`，返回对应的具体产品
```

1. 定义接口和实现类

在进行功能扩展的时候，不需要修改之前的源代码, 直接通过新建一个类完成扩展, 显然工厂方法模式符合 OCP 原则

```java
// 抽象产品
public interface MessageService {
    void send(String msg);
}
// 具体产品
public class EmailService implements MessageService {
    public void send(String msg) {
        System.out.println("发送邮件：" + msg);
    }
}
```

2. 编写一个工厂类

```java
// 具体工厂
public class MessageFactory {
  // 静态工厂方法
  public static MessageService createEmailService() {
    return new EmailService();
  }

  // 实例工厂方法
  public MessageService createEmailServiceByInstance() {
    return new EmailService();
  }
}
```

3. XML 配置使用工厂方法

```xml
<!--使用静态方法-->
<bean id="emailService" class="com.example.MessageFactory" factory-method="createEmailService"/>

<!--使用实例方法-->
<!-- 先创建工厂对象 -->
<bean id="msgFactory" class="com.example.MessageFactory"/>

<!-- 再通过工厂方法创建 Bean -->
<bean id="emailService" factory-bean="msgFactory" factory-method="createEmailServiceByInstance"/>
```

4. 获取并使用 Bean

```java
// 调用工厂静态方法
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("factory.xml");
MessageService messageService = applicationContext.getBean("emailService", MessageService.class);
messageService.send("Hello Factory Method!");

// 调用工厂实例方法
MessageService messageService = applicationContext.getBean("emailServiceInstance", MessageService.class);
messageService.send("Hello Factory Method!");
```

优点:

- 一个调用者想创建一个对象，只要知道其名称就可以了
- 扩展性高，如果想增加一个产品，只要扩展一个工厂类就可以
- 屏蔽产品的具体实现，调用者只关心产品的接口

缺点:

- 每次增加一个产品时，都需要增加一个具体类和对象实现工厂，使得系统中类的个数成倍增加，在一定程度上增加了系统的复杂度，同时也增加了系统具体类的依赖

****
## 4. 抽象工厂模式

抽象工厂模式是一种创建型设计模式，用于创建一系列相关或依赖的对象，而无需指定它们的具体类。它提供一个接口，用于创建多个“产品族”的对象，而不是单一对象

```lua
         +----------------------+
         |  AbstractFactory     |<-------------------+
         +----------------------+                    |
         | + createProductA()   |                    |
         | + createProductB()   |                    |
         +----------+-----------+                    |
                    |                                |
        +-----------+-----------+        +-----------+-----------+
        |       ConcreteFactory1 |       |       ConcreteFactory2 |
        +------------------------+       +------------------------+
        | + createProductA()     |       | + createProductA()     |
        | + createProductB()     |       | + createProductB()     |
        +-----------+------------+       +-----------+------------+
                    |                                |
   -----------------+----------------  --------------+----------------
   |                                |  |                               |
+--v----------------+       +-------v---------+         +--------------v------+
| AbstractProductA  |       | AbstractProductB|         | AbstractProductB    |
+-------------------+       +-----------------+         +---------------------+
|                   |       |                 |         |                     |
+-------------------+       +-----------------+         +---------------------+
   |                          |                               |
+--v----------------+     +--v-------------------+     +------v------------------+
| ProductA1         |     | ProductB1            |     | ProductB2              |
+-------------------+     +----------------------+     +------------------------+
|                   |     |                      |     |                        |
+-------------------+     +----------------------+     +------------------------+

说明：
- `AbstractFactory`：抽象工厂，定义创建抽象产品的接口。
- `ConcreteFactory1/2`：具体工厂，实现抽象工厂接口，创建具体产品。
- `AbstractProductA/B`：抽象产品，定义产品族中某个产品的接口。
- `ProductA1/ProductB1/ProductB2`：具体产品，由具体工厂创建。
```

角色:

- 抽象工厂角色
- 具体工厂角色
- 抽象产品角色
- 具体产品角色

1. 定义抽象产品接口

```java
public interface PayService {
    void pay();
}

public interface RefundService {
    void refund();
}
```

2. 定义产品实现类（支付宝系列）

```java
public class AlipayPayService implements PayService {
    public void pay() {
        System.out.println("使用支付宝支付");
    }
}

public class AlipayRefundService implements RefundService {
    public void refund() {
        System.out.println("使用支付宝退款");
    }
}
```

3. 定义产品实现类（微信系列）

```java
public class WeChatPayService implements PayService {
    public void pay() {
        System.out.println("使用微信支付");
    }
}

public class WeChatRefundService implements RefundService {
    public void refund() {
        System.out.println("使用微信退款");
    }
}
```

4. 定义抽象工厂接口

```java
public interface PaymentFactory {
    PayService createPayService();
    RefundService createRefundService();
}
```

5. 实现具体工厂类

```java
public class AlipayFactory implements PaymentFactory {
    public PayService createPayService() {
        return new AlipayPayService();
    }

    public RefundService createRefundService() {
        return new AlipayRefundService();
    }
}

public class WeChatFactory implements PaymentFactory {
    public PayService createPayService() {
        return new WeChatPayService();
    }

    public RefundService createRefundService() {
        return new WeChatRefundService();
    }
}
```

6. Spring 配置（factory.xml）

通过 Bean 标签创建 AlipayFactory 和 WechatFactory 工厂对象, 然后调用工厂对象中的方法创建 XxxPayService 和 XxxRefundService 对象, 
当调用 pay() 与 refund() 方法时, 就等于调用了 XxxPayService 和 XxxRefundService 对象的支付与退款方法, 
所以尽管使用的是 PayService 接口，但底层其实是调用了 WeChatPayService 类的方法，这就是 Java 的多态 + 工厂模式联合发挥的效果

```xml
<!--使用支付宝-->
<bean id="paymentFactoryAli" class="com.cell.spring6.factory.abstract_factory.AlipayFactory"/>
<bean id="payServiceAli" factory-bean="paymentFactoryAli" factory-method="createPayService"/>
<bean id="refundServiceAli" factory-bean="paymentFactoryAli" factory-method="createRefundService"/>

<!--使用微信-->
<bean id="paymentFactory" class="com.example.factory.WeChatFactory" />
<bean id="payService" factory-bean="paymentFactory" factory-method="createPayService" />
<bean id="refundService" factory-bean="paymentFactory" factory-method="createRefundService" />
```

```java
ApplicationContext context = new ClassPathXmlApplicationContext("factory.xml");
PayService payServiceAli = context.getBean("payServiceAli", PayService.class);
RefundService refundServiceAli = context.getBean("refundServiceAli", RefundService.class);
payServiceAli.pay();
refundServiceAli.refund();
```

等价于:

```java
PayService payServiceAli = new AlipayPayService();
RefundService refundServiceAli = new AlipayRefunService();
payServiceAli.pay();
refundServiceAli.refund();
```

优点:

- 可以保证同一系列的产品对象一起工作，比如一个 UI 界面风格统一
- 使用者不关心对象如何创建，只依赖工厂接口
- 新增一个产品系列时，只需新增具体工厂和一套产品实现，不影响现有代码
- 通过添加类扩展功能，不需修改现有代码, 符合 OCP 原则
- Spring 就是通过抽象工厂 + 配置注入，实现了 IOC 的可插拔机制

缺点:

- 每增加一个产品族就需要增加多个类和接口（工厂接口、产品接口、多个实现类）
- 如果要新增一个产品种类，就要改所有的工厂接口和实现类（违反接口隔离）
- 结构较为复杂, 不适合小型系统
- 如果接口发生改变，所有工厂和实现类都要动，非常麻烦
- 如果滥用，会让代码变得难以理解，维护成本上升

****
## 四. 三种工厂模式的对比

| 模式   | 产品创建控制          | 是否违背开闭原则 | 类结构复杂度 | 使用场景                 |
| ---- | --------------- | -------- | ------ | -------------------- |
| 简单工厂 | 一个工厂类+传参         违背         | 低      | 小系统、产品种类较少           |
| 工厂方法 | 每个产品对应一个工厂类     | 遵守       | 中      | 产品种类经常扩展             |
| 抽象工厂 | 一个工厂创建多个产品（产品族） | 对“产品种类”扩展不友好 | 高      | 需统一生产一组产品的系统，如跨平台 UI |

简单工厂模式是通过传递参数决定创建哪个对象，结构简单但违反开闭原则；
工厂方法模式为每个产品提供独立工厂，遵循开闭原则，但类数量较多，扩展略繁琐；
抽象工厂模式适用于一系列产品的创建，一个工厂接口对应多个产品类型，实现了“产品族”的整体生成，但不适合频繁新增“产品种类”，否则每个工厂类都要改。

****
# 七. Bean 的获取方式

通过 Spring 来获取 Bean 对象就不能再使用 new 的方式, 这样会让 Bean 对象失去被 Spring 管理的资格, 导致属性的注入出现问题

## 1. 通过构造方法获取

默认情况下，会调用Bean的无参数构造方法, 在解析 XML 文件时则会把所有的 Bean 全部实例化:

```xml
<bean id="userBean" class="com.cell.spring6.first_code.bean.User"/>
```

****
## 2. 静态工厂方法实例化

调用某个静态工厂类的静态方法返回 Bean, 尽量用接口作为返回类型

```java
public class StaticFactory {
    public static UserService createUserService() {
        return new UserService();
    }
}
```

```xml
<bean id="userService" class="com...StaticFactory" factory-method="createUserService"/>
```

```java
ApplicationContext context = new ClassPathXmlApplicationContext("...xml");
UserService userService = context.getBean("...", UserService.class);
```

相当于执行:

```java
UserService userService = StaticFactory.createUserService();
```

****
## 3. 实例工厂方法实例化(factory-bean)

```java
public class InstanceFactory {
    public UserService createUserService() {
        return new UserService();
    }
}
```

```xml
<!-- 先注册工厂实例 -->
<bean id="instanceFactory" class="com...InstanceFactory"/>

<!-- 再通过实例方法创建 Bean -->
<bean id="userService" factory-bean="instanceFactory" factory-method="createUserService"/>
```

```java
ApplicationContext context = new ClassPathXmlApplicationContext("...xml");
UserService userService = context.getBean("userService", UserService.class);
```

相当于执行:

```java
InstanceFactory factory = new InstanceFactory();
UserService userService = factory.createUserService();
```

****
## 4. 实现 FactoryBean 接口实例化

```java
public class UserServiceFactoryBean implements FactoryBean<UserService> {

    @Override
    public UserService getObject() throws Exception {
        return new UserService();  // 可包含复杂逻辑或第三方对象创建
    }

    @Override
    public Class<?> getObjectType() {
        return UserService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
```

```xml
<bean id="userService" class="com...UserServiceFactoryBean"/>
```

```java
ApplicationContext context = new ClassPathXmlApplicationContext("...xml");
// 获取的是 getObject() 返回的 UserService 实例
UserService userService = context.getBean("userService", UserService.class);
// 如果想获取工厂 Bean 本身，要加 & 前缀
UserServiceFactoryBean factory = (UserServiceFactoryBean) context.getBean("&userService");
```

****
## 5. BeanFactory 和 FactoryBean 的区别

BeanFactory 被翻译为 “Bean工厂”, 是 Spring IoC 容器的顶级对象, 负责创建 Bean 对象

FactoryBean 是一个 Bean, 能够辅助 Spring 实例化其它 Bean 对象的一个 Bean

****
# 八. Bean 的生命周期

Bean 生命周期就是 Spring 如何“创建、管理、使用并最终销毁”一个 Bean 的全过程, 当知道 Bean 的生命周期后, 就能知道在哪个时间点上调用了哪些代码, 也方便后续在特殊的时间节点上编写代码

## 1. Bean 的生命周期之 5 步

[User.java](./Demo1-First_Spring/src/main/java/com/cell/spring6/bean_lifecycle/User.java)

```xml
<!--init-method属性指定初始化方法; destroy-method属性指定销毁方法-->
<bean id="userBean" class="com.cell.spring6.bean_lifecycle.User" init-method="initBean" destroy-method="destroyBean">
    <property name="name" value="zhangsan"/>
</bean>
```

- 第一步：实例化Bean
- 第二步：Bean属性赋值
- 第三步：初始化Bean
- 第四步：使用Bean
- 第五步：销毁Bean

需要注意的是:

- 第一：只有正常关闭 spring 容器，bean 的销毁方法才会被调用
- 第二：ClassPathXmlApplicationContext 类才有 close() 方法
- 第三：配置文件中的 init-method 指定初始化方法; destroy-method 指定销毁方法

****
## 2. Bean 的生命周期之 7 步

额外的两步就是在初始化 Bean 的前后加入 "Bean 后处理器", 手动编写一个类实现 BeanPostProcessor 类，并且重写 before 和 after 方法, 然后在 xml 文件中配置“Bean 后处理器”:

[实现类:LogBeanPostProcessor.java](./Demo1-First_Spring/src/main/java/com/cell/spring6/bean_lifecycle/LogBeanPostProcessor.java)

```xml
<!--配置Bean后处理器, 这个后处理器将作用于当前配置文件中所有的 bean, 即所有 bean 的声明周期都变为 7 步-->
<bean class="com.cell.spring6.bean_lifecycle.LogBeanPostProcessor"/>
```

****
## 3. Bean 的生命周期之 10 步

- 第一步:实例化
- 第二步:属性注入
- 第三步:检查 Bean 是否实现了 Aware 接口,并设置相关依赖
- 第四步:初始化前处理(bean 后处理器的 before 方法)
- 第五步:检查 Bean 是否实现了 InitializingBean 接口,并调用接口方法
- 第六步:初始化
- 第七步:初始化后处理(bean 后处理器的 after 方法)
- 第八步:使用
- 第九步:检查 Bean 是否实现了 DisposableBean 接口,并调用接口方法
- 第十步:销毁

Aware 相关的接口包括：BeanNameAware、BeanClassLoaderAware、BeanFactoryAware

- 当 Bean 实现了 BeanNameAware，在 Bean 内部知道自己在配置文件或注解中的名字
- 当 Bean 实现了 BeanClassLoaderAware，Bean 会被注入 ApplicationContext, 它是 BeanFactory 的子接口，功能更强大
- 当 Bean 实现了 BeanFactoryAware，Bean 会被注入 BeanFactory 容器

过程:

```text
1.实例化Bean
2.Bean属性赋值
3.bean名字：userBean
3.Bean工厂：org.springframework.beans.factory.support.DefaultListableBeanFactory@2b91004a: defining beans [userBean,com.cell.spring6.bean_lifecycle.LogBeanPostProcessor#0]; root of factory hierarchy
4.Bean后处理器的before方法执行，即将开始初始化
5.afterPropertiesSet执行
6.初始化Bean
7.Bean后处理器的after方法执行，已完成初始化
8.使用Bean
9.DisposableBean destroy
10.销毁Bean
```

****
## 4. Bean 的作用域不同，管理方式不同

- 对于 singleton 作用域的 Bean，Spring 能够精确地知道该 Bean 何时被创建，何时初始化完成，以及何时被销毁；
- 而对于 prototype 作用域的 Bean，Spring 只负责创建，当容器创建了 Bean 的实例后，Bean 的实例就交给客户端代码管理，Spring 容器将不再跟踪其生命周期, 即不会执行上面的最后两步

****
## 5. 手动实例化的对象纳入 Spring 容器管理

通过这种方式获得的 Bean 并不会和上面一样经历完整的 Bean 生命周期的十个步骤

```java
 // 自己new的对象
User user = new User();
System.out.println(user);

// 创建 默认可列表BeanFactory 对象
DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
// 注册Bean
factory.registerSingleton("userBean", user);
// 从spring容器中获取bean
User userBean = factory.getBean("userBean", User.class);
```

- 手动 new 了对象，跳过了实例化、属性注入、Aware 注入等所有生命周期步骤
- registerSingleton() 仅仅是把这个现成对象放入了容器的单例池中（singletonObjects）
- Spring 不会再帮忙执行 Aware 接口、初始化方法、BeanPostProcessor、销毁回调等

****
# 九. Bean 的循环依赖问题

循环依赖是指两个或多个 Bean 在创建过程中相互依赖，形成“你依赖我，我又依赖你”的闭环结构,例如:

```java
// 这两个 Bean 都需要互相注入, 此时就构成了循环依赖
public class Husband {
    private String name;
    private Wife wife;
}

public class Wife {
  private String name;
  private Husband husband;
}
```

****
## 1. singleton 下的 set 注入产生的循环依赖

```xml
<bean id="husbandBean" class="com.cell.spring6.circular_dependency.Husband" scope="singleton">
    <property name="name" value="张三"/>
    <property name="wife" ref="wifeBean"/>
</bean>
<bean id="wifeBean" class="com.cell.spring6.circular_dependency.Wife" scope="singleton">
    <property name="name" value="小花"/>
    <property name="husband" ref="husbandBean"/>
</bean>
```

通过测试得知: 在 singleton + set 注入的情况下，循环依赖是没有问题的, Spring 可以解决这个问题, 结果可以正常打印.

主要原因是在这种模式下 Spring 对 Bean 的管理主要分为两个阶段:

- 第一个阶段:在 Spring 容器加载时就会实例化 Bean, 只要有一个 Bean 被实例化了就马上进行"曝光"(不用等属性进行赋值, 但只有在 singleton 时才会曝光)
- 第二个阶段:Bean"曝光"后才会进行属性的赋值(set 注入)

****
## 2. prototype 下的 set 注入产生的循环依赖

将 scope 改为 prototype(只有两个都是 prototype 时才会报错):

```text
Error creating bean with name 'husbandBean' defined in class path resource [circular_dependency/cd.xml]: 
Cannot resolve reference to bean 'wifeBean' while setting bean property 'wife'
Error creating bean with name 'husbandBean': Requested bean is currently in creation: Is there an unresolvable circular reference?
```

因为此时是调用 getBean() 方法后才会进行 Bean 的实例化, 也就是说每一次的实例化都是一个新的 Bean, 而 Husband 和 Wife 互相依赖对方的实例化作为自己的字段, 
所以它们会进入一个死循环, 也就是当 Husband 需要 Wife 完成实例化后才能完成实例化, 而 Wife 又需要 Husband 完成实例化后才能完成实例化

****
## 3. singleton 下的构造注入产生的循环依赖

这个与上面同理, 使用构造器注入时需要先完成属性的注入才能完成 Bean 的实例化, 也就是说也会进入死循环

****
## 4. Spring 解决循环依赖的机理

> 只有在 set 注入 + singleton 模式下才能解决循环依赖问题, 即 Bean 的实例化和属性赋值是分开执行的, 不会因为某个动作没完成而陷入死循环

在 Spring 底层中有三个缓存(本质上是 Map 集合):

- `private final Map<String, Object> singletonObjects`: 一级缓存, 这个缓存中的 Bean 对象的属性已经完成赋值, 是一个完整的单例 Bean 对象
- `private final Map<String, Object> earlySingletonObjects`: 二级缓存, 早期的单例 Bean 对象, 此时的 Bean 对象还没有完成属性的赋值
- `private final Map<String, ObjectFactory<?>> singletonFactories`: 三级缓存, 存储了大量单例工厂对象, 每一个单例 Bean 对象都对应一个单例工厂对象(即创建单例对象时对应的工厂对象)

所以使用这种方法后, 创建的 Bean 对象会提前曝光, 也就是放入缓存中, 当需要获取该 Bean 对象时就会去这三个缓存中找, 从一级到三级, 到三级缓存后就通过 BeanFactory 获取 Bean 然后放入二级缓存中,
通过这种缓存机制让实例化和属性赋值分开执行, 互不干扰, 从而解决循环依赖问题

****
# 十. 手写 Spring 框架

第一步: 编写 [ApplicationContext.java](./Demo2-My_Spring/src/main/java/org/myspringframwork/core/ApplicationContext.java) 接口, ApplicationContext 接口中提供一个 getBean() 方法，通过该方法可以获取 Bean 对象

第二步: 编写 [ClassPathXmlApplicationContext.java](./Demo2-My_Spring/src/main/java/org/myspringframwork/core/ClassPathXmlApplicationContext.java) 实现类,
该类从类路径当中加载 myspring.xml 配置文件

第三步: 确定采用 Map 集合存储 Bean, 在 ClassPathXmlApplicationContext 类中添加 Map<String,Object> 属性, 并且在 ClassPathXmlApplicationContext 类中添加构造方法，
该构造方法的参数接收 myspring.xml 文件, 同时实现 getBean() 方法

第四步: 解析配置文件实例化所有 Bean, 在 ClassPathXmlApplicationContext 的构造方法中解析配置文件，获取所有 bean 的类名，通过反射机制调用无参数构造方法创建 Bean。
并将 Bean 对象存放到 Map 集合中

第五步: 给 Bean 的属性赋值, 通过反射机制调用 set 方法

****
# 十一. Spring IoC 注解式开发

## 1. 利用反射获取注解

[获取注解](./Demo3-annotation/src/main/java/com/cell/test/Test.java)

****
## 2. 声明 Bean 的注解

负责声明Bean的注解，常见的包括四个, 其余的都是 Component 的别名, 都能都一样, 主要是用来提高可读性：

- @Component
- @Controller: 用在 Controller 层
- @Service: 用在 Service 层
- @Repository: 用在 Dao 层

****
## 3. Spring 注解的使用

- 第一步：加入aop的依赖

添加 spring-context 依赖之后，会关联加入 aop 的依赖

- 第二步:在配置文件中添加 context 命名空间

```xml
xmlns:context="http://www.springframework.org/schema/context"

http://www.springframework.org/schema/context
https://www.springframework.org/schema/context/spring-context.xsd
```

- 第三步：在配置文件中指定要扫描的包

```xml
<context:component-scan base-package="com.cell.annotation.bean"/>
```

- 第四步：在 Bean 类上使用注解

当 @Component 里面没有定义属性名的话就会将类名的首字母小写作为 Bean 的名称

多包扫描:

- 在配置文件中指定多个包，用逗号隔开

```xml
<context:component-scan base-package="com.cell.annotation.bean,com.cell.annotation.bean2"/>
```

- 指定共同的父包

```xml
<context:component-scan base-package="com.cell.annotation"/>
```

****
## 4. 选择性实例化 Bean

默认情况下，使用 @Component、@Service、@Repository、@Controller 注解的类，只要在 ComponentScan 扫描路径内，Spring 就会把它们全部实例化为 Bean. 

- 第一种:可以通过修改 use-default-filters 来选择不再使用 Spring 默认的规则, 由自己手动选择需要实例化的 Bean

```xml
<context:component-scan base-package="com.cell.annotation.bean" use-default-filters="false">
    <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```

use-default-filters="true" 表示：使用 spring 默认的规则，只要有 Component、Controller、Service、Repository 中的任意一个注解标注，则进行实例化
use-default-filters="false" 表示：不再 spring 默认实例化规则，即使有 Component、Controller、Service、Repository 这些注解标注，也不再实例化
`<context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>` 表示只有 Controller 进行实例化

- 第二种:可以将 use-default-filters 设置为 true（不写就是true），并且采用 exclude-filter 方式排出哪些注解标注的 Bean 不参与实例化

```xml
<context:component-scan base-package="com.cell.annotation.bean">
  <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Repository"/>
  <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Service"/>
  <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```

****
## 5. 负责注入的注解

给 Bean 属性赋值需要用到这些注解：

- @Value
- @Autowired
- @Qualifier
- @Resource

### 5.1 @Value

当属性的类型是简单类型时，可以使用 @Value 注解进行注入, 它可以将外部配置的值注入到字段、方法、构造器参数中, 当没有提供 setter 方法时也可也完成注入

****
### 5.2 @Autowired 与 @Qualifier

@Autowired 和 @Qualifier 主要用于实现自动装配 Bean, 它们常一起配合使用，用于控制 Spring 容器如何选择和注入所需的 Bean

@Autowired:

单独使用 @Autowired 注解时，默认是根据类型进行装配。Spring 会从容器中查找与字段类型匹配的 Bean, 无需提供 setter 方法。
如果找不到匹配的 Bean 或者有多个 Bean，则会抛出异常, 若该类没有被 Spring 管理（即没有被注册为 Bean），
那么该类型的 Bean 根本不存在，注入失败并会抛出异常。只有在设置 @Autowired(required = false) 或使用 Optional<> 作为引用类型时，
Spring 才会在找不到 Bean 的情况下不报错

```java
@Autowired
private UserService userService;
```

这个注解可以使用在字段, 构造方法, 以及方法上, 但都必须有对应的参数才行, 当构造方法只有一个时, @Autowired 可以省略

```java
@Service
public class UserService {
    private UserDao userDao;
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    public void save(){
        userDao.insert();
    }
}
```

@Qualifier:

当一个接口有多个实现类，Spring 不知道注入哪个时，@Qualifier 用来指定 Bean 的名字，帮助 @Autowired 精确注入

```java
@Component("wechatService")
public class WeChatService implements MessageService {}

@Component("emailService")
public class EmailService implements MessageService {}
```

```java
@Component
public class MessageSender {

    @Autowired
    @Qualifier("emailService")
    private MessageService messageService;
}
```

****
### 5.3 @Resource 

@Resource 注解属于 JDK 扩展包，所以不在 JDK 当中，需要额外引入以下依赖(如果是JDK8的话不需要额外引入依赖, 高于JDK11或低于JDK8需要引入以下依赖), 且 Spring 6 及以上版本基于 Jakarta EE 9+，注解包从 javax.annotation 改为 jakarta.annotation:

```xml
<dependency>
  <groupId>jakarta.annotation</groupId>
  <artifactId>jakarta.annotation-api</artifactId>
  <version>2.1.1</version>
</dependency>
```

@Resource 用于按照名称优先的策略注入 Spring 容器中的 Bean (只能作用在字段与 setter 方法上), 如果没有指定名称, 就会按照当前字段的属性名查找 Bean , 如果没找到就根据类型查找

```java
@Resource(name = "userService") // 按名称查找
private UserService service;

@Resource(type = OrderService.class) // 按类型查找（如果有多个，仍然会报错）
private OrderService orderService;
```

****
## 6. 全注解式开发

所谓的全注解开发就是不再使用 spring 配置文件了, 而是写一个配置类来代替配置文件, 编写测试程序就不再 new ClassPathXmlApplicationContext()对象了

```java
@Configuration
@ComponentScan({"com.cell.annotation"})
public class AppConfig {}
```

```java
AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
AppController appController = annotationConfigApplicationContext.getBean("appController", AppController.class);
String userInfo = appController.getUserInfo();
System.out.println(userInfo);
```

****
# 十二. JdbcTemplate

JdbcTemplate 是对原生 JDBC 的封装：

- 自动管理资源（连接、语句、结果集）
- 自动转换 SQL 异常为运行时异常
- 支持参数绑定、查询、更新、批处理等操作

## 1. 环境配置

创建数据源并设置 JdbcTemplate:

[MyDataSource.java](./Demo3-annotation/src/main/java/com/cell/jdbc_template/datasource/MyDataSource.java)

```xml
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <property name="dataSource" ref="myDataSource"/>
</bean>

<bean id="myDataSource" class="com.cell.jdbc_template.datasource.MyDataSource">
    <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/spring-notes"/>
    <property name="username" value="root"/>
    <property name="password" value="123"/>
</bean>
```

****
## 2. 新增

[testInsert](./Demo3-annotation/src/test/java/com/cell/test/JdbcTemplateTest.java)

update方法有两个参数：

- 第一个参数：要执行的SQL语句（SQL语句中可能会有占位符 ? ）
- 第二个参数：可变长参数，参数的个数可以是0个，也可以是多个, 一般是SQL语句中有几个问号，则对应几个参数

****
## 3. 查询

查询一条记录:

queryForObject 方法三个参数：

- 第一个参数：sql 语句
- 第二个参数：Bean 属性值和数据库记录行的映射对象, 在构造方法中指定映射的对象类型
- 第三个参数：可变长参数，给 sql 语句的占位符问号传值

查询多条记录:

使用 query 方法

****
# 十三. GoF 之代理模式

代理模式（Proxy Pattern）就是为其他对象提供一种代理以控制对这个对象的访问, 类似于雇佣一个中介, 让中介帮忙干活, 而我不是真正的执行任务的对象,
所以当两个对象无法直接交互时; 功能需要增强时; 在程序中目标需要保护时都可以使用代理模式

代理模式中的角色：

- 代理类（代理主题）
- 目标类（真实主题）
- 代理类和目标类的公共接口（抽象主题）：客户端在使用代理类时就像在使用目标类，不被客户端所察觉，所以代理类和目标类要有共同的行为，也就是实现共同的接口

****
## 1. 静态代理

> 静态代理是指：在程序运行前, 开发者手动创建代理类，该代理类实现与目标对象相同的接口，并在代理类中控制对目标对象的访问，可以在调用目标方法前后添加额外的逻辑

在普通的 MVC 模型中, 想要在原有的代码基础上进行扩展有三种方式:

- 第一种: 硬编码, 在每一个业务接口中添加新的方法, 然后通过实现类完成实现, 这种方式违背了 OCP 原则, 并且代码通常无法得到复用
- 第二种: 编写业务的子类, 让子类继承实现类, 然后对每个方法进行重写, 这样虽然解决了 OCP 问题, 但是会导致每个类之间的耦合度很高,
- 第三章: 静态代理, 通过公共接口来调用实现类的方法, 让类与类之间的耦合变成类与属性的耦合, 降低了耦合度, 但是这种方式需要每个接口都实现一个代理,
当接口较多时容易造成类爆炸 [OrderServiceProxy.java](./Demo3-annotation/src/main/java/com/cell/proxy/static_proxy/service/OrderServiceProxy.java)

****
## 2. JDK 动态代理

> 动态代理是指在运行时动态创建代理类，并通过这个代理类来实现对目标对象方法的增强，而不需要手写代理类代码,与静态代理相比，动态代理不需要为每个目标类编写单独的代理类，大大提高了代码复用性和灵活性

在使用静态代理时需要手动创建代理类来为每个接口实现代理功能, 但在动态代理中可以动态的生成代理类,直接在客户端调用 JDK 的动态代理技术就行: [动态代理](./Demo3-annotation/src/main/java/com/cell/proxy/dynamic)

```java
OrderService proxyObj = (OrderService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new TimeInvocationHandler(target));
```

通过这种方法,不管有多少个接口需要实现,只需要在 invoke 方法中添加增强代码即可,这样每次调用实现类的方法时都会自动加上这些代码

JDK动态代理适用场景：

- 目标对象已经实现了接口 
- 需要轻量级代理解决方案
- 需要遵循Java标准API的场景

****
## 3. CGLIB 动态代理

[CGLIB](./Demo3-annotation/src/main/java/com/cell/proxy/cglib_proxy)底层采用继承的方式实现，所以子类被创建时会调用父类的构造器(使用无参构造器)，
并且被代理的目标类不能使用final修饰

使用CGLIB，需要引入它的依赖：

```xml
<dependency>
  <groupId>cglib</groupId>
  <artifactId>cglib</artifactId>
  <version>3.3.0</version>
</dependency>
```

```text
目标类（TargetClass）
        ↓
生成子类（ProxyClass extends TargetClass）
        ↓
子类方法重写 + 增强逻辑（如执行前/后拦截）
        ↓
通过代理子类对象调用方法
```

CGLIB代理适用场景：

- 目标对象没有实现任何接口 
- 需要代理普通类的方法 
- 对性能要求较高的场景 
- 需要代理非final类和方法的场景

****
# 十四. 面向切面编程 AOP

AOP 面向切面编程，它是一种编程技术，是对面向对象编程（OOP）的补充与延申，通过动态代理来实现（JDK动态代理 + CGLIB动态代理技术），
Spring在这两种动态代理中灵活切换，如果是代理接口，会默认使用JDK动态代理，如果要代理某个类，而这个类没有实现接口，就会切换使用CGLIB，
也可也强制通过一些配置让Spring只使用CGLIB

## 1. AOP 介绍

一般一个系统当中都会有一些系统服务，例如：日志、事务管理、安全等,这些系统服务被称为：**交叉业务**，
这些**交叉业务**几乎是通用的，不管是做银行账户转账，还是删除用户数据，日志、事务管理、安全等功能都是需要做的。
如果在每一个业务处理过程当中，都掺杂这些交叉业务代码进去的话，存在两方面问题：

- 第一：交叉业务代码在多个业务流程中反复出现，显然这个交叉业务代码没有得到复用，并且修改这些交叉业务代码的话又需要修改多处
- 第二：程序员无法专注核心业务代码的编写，在编写核心业务代码的同时还需要处理这些交叉业务

而使用 AOP 就能很好的解决这些问题，将与核心业务无关的代码独立的抽取出来，形成一个独立的组件，然后以横向交叉的方式应用到业务流程当中

所以AOP的优点是：

- 代码复用性增强
- 代码易维护
- 使开发者更关注业务逻辑

****
## 2. AOP 的七大术语

```java
public class UserService {
    public void do1() {
        System.out.println("do 1");
    }

    public void do2() {
        System.out.println("do 2");
    }

    public void do3() {
        System.out.println("do 3");
    }

    public void do4() {
        System.out.println("do 4");
    }

    public void do5() {
        System.out.println("do 5");
    }

    // 核心业务方法
    public void service() {
        try {
            // Joinpoint连接点
            do1(); // Pointcut 切点
            // Joinpoint连接点
            do2();// Pointcut 切点
            // Joinpoint连接点
            do3();// Pointcut 切点
            // Joinpoint连接点
            do5();// Pointcut 切点
            // Joinpoint连接点
        } catch (Excrption e) {
            // Joinpoint连接点
        } finally {
          // Joinpoint连接点
        }

    }
}
```

- 连接点(Joinpoint)
> 在程序的整个执行流程中，可以织入切面的位置，也就是方法的执行前后，异常抛出之后等位置,所以连接点描述的是位置

- 切点(Pointcut)
> 在程序执行流程中，真正织入切面的方法（一个切点对应多个连接点）

- 通知(Advice)
> 通知又叫增强，就是具体要织入的增强代码，例如：日志代码、统计时常的代码，所以通知描述的是代码，通知包括前置通知（切点前）、后置通知（切点后）、环绕通知（切点前后都有）、异常通知（catch 中）、最终通知（finally 中）

- 切面(Aspect)
> 切点 + 通知就是切面

- 织入(Weaving)
> 把通知应用到目标对象上的过程

- 代理对象(Proxy)
> 一个目标对象被织入通知后产生的新对象

- 目标对象(Target)
> 被织入通知的对象

****
## 3. 切点表达式

切点表达式用来定义通知（Advice）往哪些方法上切入，切入点表达式语法格式：

```java
execution([访问控制权限修饰符] 返回值类型 [全限定类名]方法名(形式参数列表) [异常])
```

访问控制权限修饰符（可选项）：

- 没写，就是4个权限都包括
- 写public就表示只包括公开的方法

返回值类型（必填项）：

- `*` 表示返回值类型任意

全限定类名（可选项）：

- 两个点“..”代表当前包以及子包下的所有类
- 省略时表示所有的类

方法名（必填项）：

- `*`表示所有方法
- `set*` 表示所有的set方法

形式参数列表（必填项）：

- () 表示没有参数的方法
- (..) 参数类型和个数随意的方法
- (*) 只有一个参数的方法
- (*, String) 第一个参数类型随意，第二个参数是String的

异常（可选项）：

- 省略时表示任意异常类型

例如：

```java
// service 包下的所有类的以 delete 开头的所有public方法（方法参数随意）
execution(public * com.cell.spring.service.*.delete*(..))

// spring 包下的所有类（不包括子包）的所有的方法
execution(* com.cell.spring..*(..))

// 所有类的所有方法
execution(* *(..))
```

****
## 4. 使用 Spring 的 AOP

Spring对AOP的实现包括以下3种方式：

- 第一种方式：Spring框架结合AspectJ框架实现的AOP，基于注解方式
- 第二种方式：Spring框架结合AspectJ框架实现的AOP，基于XML方式
- 第三种方式：Spring框架自己实现的AOP，基于XML配置方式

使用Spring+AspectJ的AOP需要引入相关依赖以及配置命名空间：

```xml
<!-- Spring AOP 核心依赖 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>6.1.14</version>
</dependency>

<!-- AspectJ 运行时支持 -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.8</version>
</dependency>

<!-- 如果使用注解配置 AOP，还需要添加 spring-context -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.1.14</version>
</dependency>
```

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
```

实现步骤：

第一步：定义目标类以及目标方法[AOP](./Demo3-annotation/src/main/java/com/cell/aop/service)

```java
// 目标类
public class OrderService {
    // 目标方法
    public void generate(){
        System.out.println("订单已生成！");
    }
}
```

第二步：定义切面类

```java
// 切面类
@Aspect
public class MyAspect {
}
```

第三步：目标类和切面类都纳入 Spring 容器管理，也就是在对上面两个类添加 @Component 注解

第四步：在配置文件中添加组建扫描

```xml
<context:component-scan base-package="com.cell.aop.service"/>
```

第五步：在切面类中添加通知

```java
// 这就是需要增强的代码（通知）
public void advice(){
    System.out.println("我是一个通知");
}
```

第六步：在切面类的通知上添加切点表达式

```java
// 切点表达式，OrderService 类中的所有方法，注解 @Before 表示前置通知
@Before("execution(* com.cell.aop.service.OrderService.*(..))")
```

第七步：在配置文件中启用自动代理

开启自动代理之后，凡是带有@Aspect注解的bean都会生成代理对象。
proxy-target-class="true" 表示采用cglib动态代理。
proxy-target-class="false" 表示采用jdk动态代理。默认值是false。即使写成false，当没有接口的时候，也会自动选择cglib生成代理类。

```xml
<aop:aspectj-autoproxy proxy-target-class="true"/>
```

****
## 5. 通知类型的执行顺序

通知类型包括：

前置通知：@Before 目标方法执行之前的通知，通常用来权限检查、日志记录等，也可也传入一个 JoinPoint 参数（非必需）：

```text
getSignature()：获取目标方法的签名信息（如方法名、参数类型）
getArgs()：获取目标方法的参数列表
getTarget()：获取目标对象的引用
```

后置通知：@AfterReturning 目标方法执行之后的通知，通常用于返回值处理或资源释放，也可也传入 JoinPoint ，与上述一样，不过可以在切点表达式中添加 returning 接收返回值：

```java
// returning = "result"：将目标方法的返回值绑定到通知方法的 result 参数
@AfterReturning(pointcut = "execution(* com.cell.aop.service.OrderService.getOrder(..))", returning = "result")
public void logOrderResult(JoinPoint joinPoint, Object result) {
    System.out.println("方法 " + joinPoint.getSignature().getName() + " 返回: " + result);
    if (result instanceof Order) {
        Order order = (Order) result;
        System.out.println("订单ID: " + order.getId());
    }
}
```

环绕通知：@Around 目标方法之前添加通知，同时目标方法执行之后添加通知，它可以

- 在目标方法执行前后添加自定义逻辑
- 决定目标方法是否执行（甚至可以替代目标方法）
- 修改目标方法的返回值
- 捕获并处理目标方法抛出的异常

在环绕通知的方法中，必须传入一个 ProceedingJoinPoint 类型的参数，它是环绕通知特有的，它提供了一些方法：

```text
proceed()：调用目标方法，并且必须在环绕通知中调用此方法，否则目标方法不会执行，也可以调用 proceed(Object[] args) 重载方法来修改目标方法的参数
getSignature()：获取目标方法的签名信息（如方法名、参数类型）
getArgs()：获取目标方法的参数列表
```

异常通知：@AfterThrowing 发生异常之后执行的通知，它可以接收 JoinPoint 对象或者绑定异常对象，通过 throwing 属性绑定目标方法抛出的异常：

```java
// throwing = "ex"：将目标方法抛出的异常绑定到通知方法的 ex 参数
@AfterThrowing(pointcut = "execution(* com.cell.aop.service.OrderService.*(..))", throwing = "ex")
public void logException(JoinPoint joinPoint, Exception ex) {
    System.out.println("方法 " + joinPoint.getSignature().getName() + " 抛出异常: " + ex.getMessage());
}
```

最终通知：@After 会在目标方法无论以何种方式结束（正常返回或抛出异常）后被触发，不过无法手动将 @After 放在 finally 中，
只需使用注解标注方法即可，因为 Spring AOP 在底层将其实现逻辑放在了类似 finally 块的位置，
并且框架自动生成的外部 finally 块（包含 @After 通知）后执行，确保最终通知始终触发

各种通知的执行顺序：

通过执行代码可以看到它们的执行顺序，当发生异常之后，后置通知和环绕通知的结束部分不会执行，但最终通知会执行，因为它在 finally 语句块中还可以执行

```text
// 未发生异常
环绕通知开始
前置通知
订单已生成！
方法 generate 返回: null
后置通知
最终通知
环绕通知结束

// 发生异常
环绕通知开始
前置通知
订单已生成！
方法 generate 抛出异常: 模拟异常发生
异常通知
最终通知
```

****
## 6. 切面的先后顺序

业务流程当中不一定只有一个切面，可能有的切面控制事务，有的记录日志，有的进行安全控制，如果多个切面的话，顺序可以使用@Order注解来标识切面类，
为@Order注解的value指定一个整数型的数字，数字越小，优先级越高

```text
FirstAspect环绕通知开始
FirstAspect前置通知
环绕通知开始
前置通知
订单已生成！
方法 generate 返回: null
后置通知
最终通知
环绕通知结束
FirstAspect后置通知
FirstAspect最终通知
FirstAspect环绕通知结束
```

****
## 7. 切点表达式的优化

```java
@Around("execution(* com.cell.aop.service.OrderService.*(..))")
public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    System.out.println("环绕通知开始");
    // 执行目标方法。
    proceedingJoinPoint.proceed();
    System.out.println("环绕通知结束");
}
....
```

上面这种切点表达式重复写了很多次，如果要修改切点表达式就需要修改每个通知的，太麻烦了，所以可以将切点表达式单独的定义出来，在需要的位置引入即可：

```java
// 定义可复用的切点表达式
@Pointcut("execution(* com.cell.aop.service.OrderService.*(..))")
public void orderServiceMethods() {} // 方法体为空，仅作为切点的标识

// 然后其他通知注释直接引用切点即可
@Around("orderServiceMethods()")
public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
  System.out.println("环绕通知开始");
  // 执行目标方法。
  proceedingJoinPoint.proceed();
  System.out.println("环绕通知结束");
}

// 也可也使用混合的方式
@Pointcut("execution(* com.cell.aop.service.OrderService.*(..))")
public void orderService() {}

@Pointcut("execution(* com.cell.aop.service.UserService.*(..))")
public void userService() {}

// 组合切点：所有服务
@Pointcut("orderService() || userService()")
public void allServices() {}

// 应用于所有服务的通知
@Before("allServices()")
public void logServiceAccess(JoinPoint joinPoint) {
  System.out.println("访问方法：" + joinPoint.getSignature().getName());
}
```

****
## 8. 全注解式开发 AOP

就是编写一个类，在这个类上面使用大量注解来代替配置文件，如下：

```java
@Configuration
@ComponentScan("com.cell.aop.service")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfiguration {
}
```

使用：

```java
// 没使用注解开发
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop.xml");
OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
orderService.generate();

// 使用注解开发
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfiguration.class);
OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
orderService.generate();
```

****
## 9. 基于 XML 配置方式的 AOP

[AOPXml](./Demo3-annotation/src/main/java/com/cell/aopXml/service)

第一步：切面配置，引入命名空间：

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="
           http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/aop
           https://www.springframework.org/schema/aop/spring-aop.xsd">
```

切面是横切逻辑的封装，包含切入点和通知定义：

```xml
<aop:aspect id="logAspect" ref="logAspectBean">
    <!-- 切入点和通知定义 -->
</aop:aspect>
```

第二步：切入点（Pointcut）配置

切入点定义了切面逻辑作用的目标方法：

```xml
<aop:pointcut id="servicePointcut" expression="execution(* com.cell.aopXml.service.*.*(..))" />
```

例如：环绕通知

```xml
<aop:aspect id="logAspect" ref="logAspectBean">
    <aop:pointcut id="servicePointcut" expression="execution(* com.cell.aopXml.service.*.*(..))" />
    <aop:around pointcut-ref="servicePointcut" method="logAround"/>
</aop:aspect>
```

****
## 10. AOP 的事务处理

AOP 通过动态代理（如 JDK 动态代理、CGLIB 代理）在目标方法执行前后插入事务相关逻辑，[核心流程](./Demo3-annotation/src/main/java/com/cell/aop/transaction_service/TransactionAspect.java)如下：

1. 事务开启：在方法执行前创建事务，设置事务属性（隔离级别、传播行为等）
2. 业务逻辑执行：调用目标方法，执行具体业务操作。
3. 事务提交/回滚：根据执行结果决定提交或回滚事务
4. 资源释放：关闭数据库连接等资源

****
## 11. AOP 的安全日志

AOP 同过动态代理在目标方法执行前或后插入[日志记录](./Demo3-annotation/src/main/java/com/cell/aop/security_service/SecurityAspect.java)

****
## 12. AOP 与动态代理的关系

1、技术本质：AOP 是编程范式，动态代理是实现手段

- AOP：是一种编程范式，核心目标是将横切关注点与业务逻辑分离，解决 OOP（面向对象编程）无法纵向解决的代码复用问题
- 动态代理：是一种设计模式，核心机制是运行时创建代理对象拦截方法调用，属于实现 AOP 的具体技术手段之一

2、AOP 对动态代理的封装体现

虽然 AOP != 动态代理，但Spring AOP 对动态代理进行了高级封装，通过注解（如@Aspect、@Pointcut）或 XML 配置声明切面就不再需要手动编写代理类

****
# 十五. Spring 对事务的支持

Spring实现事务的两种方式：

- 编程式事务
1. 通过编写代码的方式来实现事务的管理
- 声明式事务
1. 基于注解方式 
2. 基于XML配置方式

## 声明式事务管理

> 声明式事务是一种基于 AOP（面向切面编程） 的事务管理方式，只需在方法上加上注解或在 XML 配置中声明，即可实现事务控制，无需自己写 commit、rollback 等逻辑

## 编程式事务管理

编程式事务管理是通过 Java 代码手动控制事务的开启、提交和回滚

## 1. 基于注解实现

- 第一步：在spring配置文件中配置事务管理器

```xml
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
  <property name="dataSource" ref="dataSource"/>
</bean>
```

- 第二步：在spring配置文件中引入tx命名空间

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
```

- 第三步：在spring配置文件中配置“事务注解驱动器”，开启注解的方式控制事务

```xml
<tx:annotation-driven transaction-manager="transactionManager"/>
```

- 第四步：在service类上或方法上添加@Transactional注解，在类上添加该注解，该类中所有的方法都有事务；在某个方法上添加该注解，表示只有这个方法使用事务

****
## 2. @Transactional 的属性

- propagation：事务的传播行为
- isolation：事务的隔离级别
- timeout：事务超时时间（单位：秒）
- readOnly：是否为只读事务
- rollbackFor / rollbackForClassName：哪些异常会触发回滚、
- noRollbackFor / noRollbackForClassName：哪些异常不会触发回滚

### 2.1 事务传播行为 propagation

> 在 Spring 中，每个被 @Transactional 注解的方法执行时都要确定应该加入当前已经存在的事务中，还是开启一个新的事务，或者干脆不在事务中执行，而传播行为就是解决这个的

Propagation 枚举定义了 7 种传播行为：

1、 REQUIRED（默认）

如果当前有事务，就加入；如果没有，就新建一个事务；当外层方法抛异常时，所有调用的方法一同回滚

```java
@Transactional(propagation = Propagation.REQUIRED)
public void saveOrder() {
    saveOrderInfo(); // 内部也为 REQUIRED，会加入同一个事务
    saveLog(); // 也会加入同一个事务
}
```

2、REQUIRES_NEW

每次都新建一个事务，原有事务挂起，且外层失败不影响该方法事务，常用于记录日志或发送通知

```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void saveLog() {
    // 不管外面有没有事务都新开一个
}
```

3、NESTED（嵌套事务）

如果当前有事务，则在其中嵌套一个子事务，子事务出错只会回滚到保存点，不影响主事务，如果当前没有事务则行为同 REQUIRED

4、SUPPORTS

如果当前有事务，就加入，所有操作受当前事务的提交/回滚控制；没有事务也能正常执行，方法以非事务方式执行（即数据库自动提交模式），不开启事务控制

5、NOT_SUPPORTED

不支持事务，如果当前有事务，挂起原事务，以非事务方式执行

6、NEVER

当前不能有事务，如果有就抛异常

7、MANDATORY

当前必须有事务，没有事务就抛异常

****
### 2.2 事务隔离级别 isolation

1、@Transactional(isolation = Isolation.READ_UNCOMMITTED)

读未提交，允许读取未提交的数据（脏读），每个事务可以看到其他事务还未提交的变更

2、 @Transactional(isolation = Isolation.READ_COMMITTED)

读已提交，只能读取已经提交的数据，可以防止脏读，但可能出现不可重复读和幻读，是 Oracle 的默认级别

3、@Transactional(isolation = Isolation.REPEATABLE_READ)

可重复读（MySQL 默认），同一事务中多次读取同一数据，结果一致，可以防止脏读和不可重复读，但仍可能出现幻读

4、@Transactional(isolation = Isolation.SERIALIZABLE)

可串行化，最高隔离级别，完全隔离所有并发问题，强制事务串行执行，不过性能最差，常用于金融、审计类场景

****
### 2.3 事务超时 @Transactional(timeout = 2)

> 一个事务从开始到提交所允许的最长执行时间，一旦超过这个时间 Spring 会自动终止事务并回滚，以避免长时间占用数据库资源导致系统性能下降，默认值为 -1，也就是没设置超时时间，
> 以秒为单位，且只对读写事务有效，对只读事务（readOnly = true）无效

当使用的是 JDBC 的 DataSourceTransactionManager 进行事务管理时（声明式事务默认使用这个），超时机制只对数据库操作（SQL 执行）生效，不对 Java 层代码（如 sleep()）起作用，
也就是说当 Thread.sleep(2) 方法放在最后一个 sql 语句后面时，不管程序睡眠多久都不会影响事务最终的提交，放在其他位置都会引起报错(事务从方法被调用时就开启)，当所有sql语句执行完毕后才计算时间是否超时

****
### 2.4 只读事务 @Transactional(readOnly = true)

将当前事务设置为只读事务，在该事务执行过程中只允许select语句执行，delete insert update均不可执行。
该特性的作用是：启动spring的优化策略，提高select语句执行效率，如果该事务中确实没有增删改操作，建议设置为只读事务

****

### 2.5 设置哪些异常回滚事务

@Transactional(rollbackFor = RuntimeException.class)：表示只有发生RuntimeException异常或该异常的子类异常才回滚

@Transactional(noRollbackFor = NullPointerException.class)：表示发生NullPointerException或该异常的子类异常不回滚，其他异常则回滚

****
## 3. 事务的全注解开发

编写一个[TransactionConfig](./Demo4-web/src/main/java/com/cell/transaction/bank/config/TransactionConfig.java)类来代替配置文件

****
## 4. 基于 XML 实现事务管理

- 第一步：配置事务管理器[transactionXML.xml](./Demo4-web/src/main/resources/transactionXML.xml)
- 第二步：配置通知
- 第三步：配置切面

注意：使用这种方式不能再在 Service 类上添加 @Transactional 注解

****
# 十六. Spring6 整合 JUnit5

- 第一步：引入依赖

```xml
<!-- JUnit5 相关 -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.2</version>
    <scope>test</scope>
</dependency>

<!-- Spring Test 模块 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>6.1.14</version>
    <scope>test</scope>
</dependency>
```

- 第二步：创建[测试类](./)

整合后，测试类就可以变成 Spring 容器管理的 Bean，支持 Spring 的各种操作，并且直接通过注解进行依赖注入可以省去获取 Bean 的步骤

****
# 十七. Spring 集成 MyBatis

这种方式让两种框架的优点得以发挥，Spring 能连接数据库，但对 SQL 操作支持不强；MyBatis 擅长写 SQL，但层与层之间的耦合度较高；而两者结合刚好优势互补

- 第一步：编写[mybatis-config.xml](./Demo4-web/src/main/resources/mybatis-config.xml)配置文件

该配置文件可以不写，因为和 Spring 整合后大部分的配置可以转移到spring配置文件中，不过如果有mybatis相关的系统级配置还是需要写的，例如：全局参数（如延迟加载、驼峰命名映射等）

- 第二步：编写[spring](./Demo4-web/src/main/resources/Spring-MyBatis.xml)配置文件

这里需要完成spring基础的配置，例如：组件扫描，配置数据源，添加事务管理等；还需要完成mybatis的配置，通过这个来构建SqlSessionFactory，获取数据库的连接对象

- 第三步：编写程序

通过 Spring 配置文件或注解机制，Spring 容器会负责创建并管理 Service 层的 Bean，通过容器获取这个 Bean 并调用其方法时，Spring 会将 DAO 层（也就是 MyBatis 的 Mapper 接口）自动注入到 Service 层中，
最终这些结果通过对象映射，返回给 Service 层，再返回给调用方（如 Controller 或 main 方法）

****
# 十八. Spring 的八大设计模式

## 简单工厂模式

Spring 容器（如 BeanFactory 或 ApplicationContext）作为一个巨大的 Bean 工厂，对所有 Bean 的创建、管理、依赖注入都负责统一处理，当调用 getBean("xxx") 方法时，
就是让 Spring 工厂通过这个字符串标识 "xxx"，创建或获取一个具体的 Bean 实例。这种根据“名称”来生产“实例”的过程，正是简单工厂模式的核心思想

## 工厂方法模式

在 Spring 配置文件中，可以使用 factory-method 属性来定义使用实例工厂方法来创建 Bean 对象，这就是工厂方法模式的直接应用，对象由“工厂实例”提供的“工厂方法”创建，而不是由容器直接构造类

## 单例模式

Spring 的 Bean 默认是 单例作用域（singleton scope），整个 Spring 容器中只有一个实例

## 代理模式

Spring 的 AOP 就是使用了动态代理实现的

## 装饰器模式

Spring 的 AOP 就使用了装饰器模式，它通过动态代理技术，在不修改原始类代码的情况下，将增强的通知逻辑“包裹”在目标方法周围，实现功能的扩展和增强

## 观察者模式

定义对象间的一对多的关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并自动更新

## 策略模式

策略模式是行为性模式，调用不同的方法，适应行为的变化，强调父类的调用子类的特性。比如我们自己写了AccountDao接口，然后这个接口下有不同的实现类：AccountDaoForMySQL，AccountDaoForOracle。
对于service来说不需要关心底层具体的实现，只需要面向AccountDao接口调用，底层可以灵活切换实现，这就是策略模式。

## 模板方法模式

模板方法模式是指在一个抽象类中定义一个操作的骨架（算法的步骤），将某些步骤的实现延迟到子类，
Spring中的JdbcTemplate类就是一个模板类，它是对 JDBC 的封装，定义了数据库操作的流程（连接获取、SQL 执行、结果处理、连接关闭等），这是模板方法







