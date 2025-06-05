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
