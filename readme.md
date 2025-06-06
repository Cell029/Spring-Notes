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

prototype（原型作用域）表示每次从 Spring 容器中获取 Bean 时，都会创建一个全新的对象实例,也就是说 Spring 容器不会缓存这个 Bean 的实例，每次调用 getBean()，
都会重新创建并返回一个新的实例, Spring 不管理销毁, 需要手动销毁

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

