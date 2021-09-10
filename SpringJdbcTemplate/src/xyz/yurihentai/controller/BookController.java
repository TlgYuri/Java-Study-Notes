package xyz.yurihentai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import xyz.yurihentai.service.IBookService;

@Controller
public class BookController {

	@Autowired
	private IBookService service;

	public void buyBook() {
		for (int i = 1; i <= 10; i++) {
			try {
				System.out.println(i + "" + service.buyBook(114514, 1));
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public void getUser() {
		System.out.println(service.getUser(114514));
	}

	public void checkOut() {
	}

}