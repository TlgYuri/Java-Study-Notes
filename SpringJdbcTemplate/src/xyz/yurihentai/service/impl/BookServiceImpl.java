package xyz.yurihentai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.yurihentai.dao.IBookDao;
import xyz.yurihentai.entity.User;
import xyz.yurihentai.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private IBookDao dao;

	/*
		propagation	事务的传播
		REQUIRED	运行在一个事务里，没有事务就新启一个
		REQUIRED_NEW	方法必须运行在独立的事务里（如：被别的事务方法调用的情况
		SUPPORTS	提供有事务就走事务，不然直接走
		NOT_SUPPORTED	不运行在事务里
		MANDATROY	必须运行在事务里，否则抛异常
		NEVER	不能走事务，否则抛异常
		NESTED	如果有事务，就在事务里嵌套一个事务
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	public boolean buyBook(int uid, int id) throws RuntimeException {
		boolean result = false;
		try{
			Double price = dao.getPrice(id);
			dao.updateStock(id);
			dao.updateBalance(uid,price);
			result = true;
		} catch(RuntimeException e) {
			throw e;
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public User getUser(int uid) {
		return dao.getUser(uid);
	}

}