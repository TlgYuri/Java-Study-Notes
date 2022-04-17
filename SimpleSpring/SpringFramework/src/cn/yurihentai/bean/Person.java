package cn.yurihentai.bean;

import java.util.List;
import java.util.Map;

public class Person {

	private String name;
	private List<String> hobby;
	private Map<String, Person> relation;

	public Person() {
		System.out.println("I：对象创建了");
	}

	public List<String> getHobby() {
		return hobby;
	}

	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}

	public Map<String, Person> getRelation() {
		return relation;
	}

	public void setRelation(Map<String, Person> relation) {
		this.relation = relation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void show() {
		System.out.println("Hello," + this.name);
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "III：Person{ name='" + name + "\',hobby=" + hobby + ",relation=" + relation + " }\n";
	}

	public void init() {
		System.out.println(this.name + "II：初始化了");
	}

	public void destroy() {
		System.out.println("IV:" + this.name + "销毁了");
	}

}