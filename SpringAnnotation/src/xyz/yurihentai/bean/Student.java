package xyz.yurihentai.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Student {
	private String name;
	@Autowired
	private Map<String, Teacher> teachers;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Map<String, Teacher> teachers) {
		this.teachers = teachers;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", teachers=" + teachers +
				'}';
	}
}