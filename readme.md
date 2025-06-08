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
## 2. 动态代理

> 动态代理是指在运行时动态创建代理类，并通过这个代理类来实现对目标对象方法的增强，而不需要手写代理类代码,与静态代理相比，动态代理不需要为每个目标类编写单独的代理类，大大提高了代码复用性和灵活性

在使用静态代理时需要手动创建代理类来为每个接口实现代理功能, 但在动态代理中可以动态的生成代理类,直接在客户端调用 JDK 的动态代理技术就行: [动态代理](./Demo3-annotation/src/main/java/com/cell/proxy/dynamic)

```java
OrderService proxyObj = (OrderService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new TimeInvocationHandler(target));
```

通过这种方法,不管有多少个接口需要实现,只需要在 invoke 方法中添加增强代码即可,这样每次调用实现类的方法时都会自动加上这些代码

****


