package xyz.yurihentai.springaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("resource/aop.xml");
		IMath math = ac.getBean("mathImpl", IMath.class);
		System.out.println(math.getClass().getName());
		math.add(1,1);
//		math.divide(1,0);
	}

}