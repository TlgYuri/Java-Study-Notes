package cn.yurihentai.proxy.test;

import cn.yurihentai.bean.Student;
import cn.yurihentai.bean.Teacher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Test {

	public static void main(String[] args) {
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("resource/AnnotationBeans.xml");
		Student stu = (Student)ac.getBean("student");
		Teacher teacher = (Teacher)ac.getBean("teacher");
		System.out.println(stu);
		System.out.println(teacher);
	}

}