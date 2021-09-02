package xyz.yurihentai;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.yurihentai.controller.BookController;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("JdbcTemplate.xml");
		BookController bc = ac.getBean("bookController", BookController.class);
//		bc.getUser();
		bc.buyBook();
	}
}
