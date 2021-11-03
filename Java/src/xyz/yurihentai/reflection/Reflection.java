package xyz.yurihentai.reflection;

import org.junit.Test;
import xyz.yurihentai.io.bean.SerializableBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * description
 * 本笔记学习自：http://ifeve.com/java-reflection/
 *
 * @author Yuri
 * @date 2021/11/3 11:45:14
 */
public class Reflection {

    // Java反射机制可以让我们在代码运行时(Runtime)检查类和接口中的属性、方法等信息。
    // 反射还可以让我们在运行时实例化对象、调用方法、操作对象属性的值。

    @Test
    /** Class对象的获取和简单使用 */
    public void testMethod() throws Exception {
        // ======= 获取class对象的三种方式: =======
        // 1
        Class<SerializableBean> serializableBeanClass1 = SerializableBean.class;
        // 2
        Class<? extends SerializableBean> serializableBeanClass2 = new SerializableBean().getClass();
        // 3  这里的参数需要是包含包名在内的类的全限定名
        Class<?> serializableBeanClass3 = Class.forName("xyz.yurihentai.io.bean.SerializableBean");

        // ======= 获取类的访问修饰符 以一个位标识表示  可以通过Modifier类进行判断验证 =======
        int modifier = serializableBeanClass1.getModifiers();
        Modifier.isPublic(modifier);

        // ======= 获取类所在的包的信息 =======
        Package aPackage = serializableBeanClass1.getPackage();

        // ======= 获取类的父类 =======
        Class<? super SerializableBean> superclass = serializableBeanClass1.getSuperclass();

        // ======= 获取类实现的所有接口 =======
        Class<?>[] interfaces = serializableBeanClass1.getInterfaces();

        // ======= 获取类的构造器（在参数中指明构造器的参数数量和类型，可以获取到指定的构造器） =======
        // 只返回public修饰的
        Constructor<?>[] constructors = serializableBeanClass1.getConstructors();
        System.out.println(Arrays.toString(constructors));
        // 返回所有构造
        Constructor<?>[] declaredConstructors = serializableBeanClass1.getDeclaredConstructors();
        System.out.println("======= =======\n" + Arrays.toString(declaredConstructors).replaceAll("\\),", ")\n") + "\n======= =======");

        // ======= 反射获取实例对象 =======
        SerializableBean serializableBean = serializableBeanClass1.getDeclaredConstructor().newInstance();

        // ======= 获取类的方法 =======
        Method[] methods = serializableBeanClass1.getMethods();

        // ======= 获取类的属性 =======
        Field[] fields = serializableBeanClass1.getFields();

        // ======= 获取类上标注的注解 =======
        Annotation[] annotations = serializableBeanClass1.getAnnotations();
    }

}