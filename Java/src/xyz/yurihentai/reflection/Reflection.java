package xyz.yurihentai.reflection;

import org.junit.Test;
import xyz.yurihentai.bean.SerializableBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
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
    public void testClass() throws Exception {
        // ======= 获取class对象的三种方式: =======
        // 1
        Class<SerializableBean> serializableBeanClass1 = SerializableBean.class;
        // 2
        Class<? extends SerializableBean> serializableBeanClass2 = new SerializableBean().getClass();
        // 3  这里的参数需要是包含包名在内的类的全限定名
        Class<?> serializableBeanClass3 = Class.forName("xyz.yurihentai.bean.SerializableBean");

        // ======= 获取类的访问修饰符 以一个位标识表示  可以通过Modifier类进行判断验证 =======
        int modifier = serializableBeanClass1.getModifiers();
        Modifier.isPublic(modifier);

        // ======= 获取类所在的包的信息 =======
        Package aPackage = serializableBeanClass1.getPackage();

        // ======= 获取类的父类 =======
        Class<? super SerializableBean> superclass = serializableBeanClass1.getSuperclass();

        // ======= 获取类实现的所有接口 =======
        Class<?>[] interfaces = serializableBeanClass1.getInterfaces();

        // ======= 获取类的构造器 =======
        Constructor<?>[] constructors = serializableBeanClass1.getConstructors();   // 公有
        Constructor<?>[] declaredConstructors = serializableBeanClass1.getDeclaredConstructors();   // 全部

        // ======= 获取类的方法 =======
        Method[] methods = serializableBeanClass1.getMethods(); // 公有
        Method[] declaredMethods = serializableBeanClass1.getDeclaredMethods(); // 全部(不包含父类提供的方法)

        // ======= 获取类的属性 =======
        Field[] fields = serializableBeanClass1.getFields();    // 公有
        Field[] declaredFields = serializableBeanClass1.getDeclaredFields();    // 全部(不包含父类提供的属性)

        // ======= 获取类上标注的注解 =======
        Annotation[] annotations = serializableBeanClass1.getAnnotations(); // 获取所有注解（包括父类的注解）
        Annotation[] declaredAnnotations = serializableBeanClass1.getDeclaredAnnotations(); // 仅获取本类上的注解
    }

    @Test
    /** 通过反射获取构造方法、实例化对象 */
    public void testConstructor() throws Exception {
        Class<SerializableBean> aClass = SerializableBean.class;
        // 获取类的构造器（在参数中指明构造器的参数数量和类型，可以获取到指定的构造器）
        // 只返回public修饰的构造方法
        Constructor<?>[] constructors = aClass.getConstructors();
        System.out.println(Arrays.toString(constructors));
        // 获取所有构造方法
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        System.out.println("======= =======");
        System.out.println(Arrays.toString(declaredConstructors).replaceAll("\\),", ")\n"));

        // 反射获取实例对象
        SerializableBean serializableBean = aClass.getDeclaredConstructor().newInstance();
        System.out.println("======= =======");
        System.out.println(serializableBean);

        // 获取指定参数的构造器并实例化对象
        Constructor<SerializableBean> declaredConstructor = aClass.getDeclaredConstructor(String.class, String.class);
        declaredConstructor.setAccessible(true);    // 获取私有方法的访问权限
        SerializableBean serializableBean1 = declaredConstructor.newInstance("name", "age");// 设置参数
        System.out.println("======= =======");
        System.out.println(serializableBean1);
    }

    @Test
    /** 操作类的属性 */
    public void testFields() throws Exception {
        SerializableBean bean = new SerializableBean("init");
        Class<? extends SerializableBean> aClass = bean.getClass();
        // 通过属性名获取指定属性
        Field nameField = aClass.getDeclaredField("name");
        // 获取属性的类型
        Class<?> type = nameField.getType();
        System.out.println(type);
        // 获取私有属性的访问权限
        nameField.setAccessible(true);
        // 获取特定对象中该属性的值
        String name = nameField.get(bean).toString();   // 如果是静态属性 可以传null
        System.out.println(name);
        // 设置特定对象中该属的性值
        nameField.set(bean, "tset");
        System.out.println(bean.getName());
    }

    @Test
    /** 操作类的方法 */
    public void testMethod() throws Exception {
        SerializableBean bean = new SerializableBean();
        Class<? extends SerializableBean> aClass = bean.getClass();
        // 获取指定方法  参数为方法名、方法参数  方法参数需要确保顺序正确
        Method declaredMethod = aClass.getDeclaredMethod("add", int.class, int.class);
        // 获取方法的返回值
        Class<?> returnType = declaredMethod.getReturnType();
        System.out.println(returnType);
        // 调用特定对象的该方法
        Object invoke = declaredMethod.invoke(bean, 114, 514);
        System.out.println(invoke);
    }

}