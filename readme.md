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
- setter:通过 setter 方法注入，可选依赖
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








