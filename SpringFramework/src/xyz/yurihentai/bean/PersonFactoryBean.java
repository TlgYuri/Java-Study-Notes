package xyz.yurihentai.bean;

import org.springframework.beans.factory.FactoryBean;

public class PersonFactoryBean implements FactoryBean<Person> {

	@Override
	public Person getObject() throws Exception {
		Person per = new Person();
		per.setName("打工人");
		return per;
	}

	@Override
	public Class<?> getObjectType() {
		return Person.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void init() {
		System.out.println("II.pre:开始找打工人当韭菜了");
	}

	public void destroy() {
		System.out.println("IV:打工人过劳死了");
	}

}
