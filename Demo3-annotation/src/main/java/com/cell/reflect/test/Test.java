package com.cell.reflect.test;

import com.cell.reflect.annotation.Component;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.net.URLDecoder.decode;

/*扫描目录下的所有 .class 文件, 然后判断是否有使用注解*/
public class Test {
    public static void main(String[] args) {
        // 存放Bean的Map集合。key存储beanId。value存储Bean。
        Map<String, Object> beanMap = new HashMap<>();
        // 扫描这个包下的文件
        String packageName = "com.cell.reflect.bean";
        // JVM 的资源路径格式是 com/cell/bean，所以把点替换成斜杠
        String packagePath = packageName.replaceAll("\\.", "/");
        // 通过类加载器找到这个路径下的资源目录
        URL url = ClassLoader.getSystemClassLoader().getResource(packagePath);
        // 获取绝对路径, 使用 UTF-8 进行解码
        String path = decode(url.getPath(), StandardCharsets.UTF_8);
        // 获取绝对路径下的所有文件
        File file = new File(path);
        File[] files = file.listFiles();
        if (files != null) {
            Arrays.stream(files).forEach(f -> {
                // 通过 "." 拆分, 只取第一个元素, 提取类名
                String className = packageName + "." + f.getName().split("\\.")[0];
                try {
                    Class<?> aClass = Class.forName(className);
                    // 判断这个类是否添加了 Component 注解
                    if (aClass.isAnnotationPresent(Component.class)) {
                        // 获取注解
                        Component component = aClass.getAnnotation(Component.class);
                        // 提取注解中的 value 的值
                        String beanId = component.value();
                        // 创建对象
                        Object bean = aClass.newInstance();
                        beanMap.put(beanId, bean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(beanMap);
    }
}
