package xyz.yurihentai.dao;

import xyz.yurihentai.entity.User;

public interface IBookDao {

	Double getPrice(int id) ;

	int updateStock(int id);

	int updateBalance(int uid, Double price);

	User getUser(int uid);

}