package cn.yurihentai.bean;

import org.springframework.stereotype.Component;

@Component
public class Teacher {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"name='" + name + '\'' +
				'}';
	}
}