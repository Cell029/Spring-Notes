package org.myspringframwork.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext implements ApplicationContext{

    private static final Logger logger = LoggerFactory.getLogger(ClassPathXmlApplicationContext.class);

    private Map<String, Object> singletonObjects = new HashMap<String, Object>();

    // 通过构造方法解析 Spring 的配置文件, 然后初始化所有的 Bean 对象
    public ClassPathXmlApplicationContext(String configLocation) {
        try {
            // 创建 dom4j 解析 XML 文件的核心对象
            SAXReader reader = new SAXReader();
            // 获取一个输入流, 指向配置文件
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(configLocation);
            // 读文件
            Document document = reader.read(in);
            // 获取所有的 Bean 标签
            List<Node> nodes = document.selectNodes("//bean");
            // 遍历 Bean 标签
            nodes.forEach(node -> {
                // System.out.println(node);
                try {
                    // 向下转型, 使用 Element 接口中更丰富的方法
                    Element beanElt = (Element) node;
                    // 获取 id 属性
                    String id = beanElt.attributeValue("id");
                    // 获取 class 属性
                    String className = beanElt.attributeValue("class");
                    logger.info("beanId=" + id + ", className=" + className);
                    // 通过反射机制创建对象, 并将其放大 Map 集合中, 进行提前曝光
                    Class<?> aClass = Class.forName(className);
                    // 获取无参数构造方法
                    Constructor<?> defaultCon = aClass.getDeclaredConstructor();
                    // 创建对象
                    Object bean = defaultCon.newInstance();
                    // 将 Bean 曝光
                    singletonObjects.put(id, bean);
                    logger.info(singletonObjects.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // 再次遍历所有的 Bean 标签, 给 Bean 对象的属性赋值
            nodes.forEach(node -> {
                try {
                    Element beanElt = (Element) node;
                    // 获取 id
                    String id = beanElt.attributeValue("id");
                    // 获取 class
                    String className = beanElt.attributeValue("class");
                    // 获取 Class
                    Class<?> aClass = Class.forName(className);
                    // 获取 Bean 标签下的所有 property 标签
                    List<Element> propertys = beanElt.elements("property");
                    propertys.forEach(property -> {
                        try {
                            // 获取属性名
                            String propertyName = property.attributeValue("name");
                            logger.info("属性名=" + propertyName);
                            // 获取属性类型
                            Field field = aClass.getDeclaredField(propertyName);
                            // 获取 set 方法名
                            String setMethodName = "set" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                            // 获取 set 方法
                            Method setMethod = aClass.getDeclaredMethod(setMethodName, field.getType());
                            // 获取具体的值(value 和 ref 标签)
                            String value = property.attributeValue("value");
                            String ref = property.attributeValue("ref");
                            if (value != null) { // 简单类型
                                // 因为 XML 文件中的 value 的值都是 String 类型的, 所以需要根据具体的属性类型进行转型
                                // 调用 set 方法
                                setMethod.invoke(singletonObjects.get(id), convertValue(value, field.getType()));
                            }
                            if (ref != null) { // 非简单类型
                                // ref = 另一个 Bean 的 id
                                setMethod.invoke(singletonObjects.get(id), singletonObjects.get(ref));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanName) {
        return singletonObjects.get(beanName);
    }

    private Object convertValue(String value, Class<?> targetType) {
        if (targetType == Byte.class || targetType == byte.class) return Byte.valueOf(value);
        if (targetType == Short.class || targetType == short.class) return Short.valueOf(value);
        if (targetType == Integer.class || targetType == int.class) return Integer.valueOf(value);
        if (targetType == Long.class || targetType == long.class) return Long.valueOf(value);
        if (targetType == Float.class || targetType == float.class) return Float.valueOf(value);
        if (targetType == Double.class || targetType == double.class) return Double.valueOf(value);
        if (targetType == Boolean.class || targetType == boolean.class) return Boolean.valueOf(value);
        if (targetType == Character.class || targetType == char.class) return value.charAt(0);
        if (targetType == String.class) return value;
        return null;
    }

}
