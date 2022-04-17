package cn.yurihentai.java.pack.bean;

import java.io.Serializable;

/**
 * description
 *
 * @author Yuri
 * @date 2021/10/27 11:45:14
 */
public class SerializableBean implements Serializable {

    private String name;
    private String age;
    private String gender;

    public SerializableBean() {}

    public SerializableBean(String name) {
        this(name, null);
    }
    private SerializableBean(String name, String age) {
        this(name, age, null);
    }
    private SerializableBean(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "SerializableBean{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

}