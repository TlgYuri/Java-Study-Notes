package cn.yurihentai.dao;

import cn.yurihentai.entity.User;

public interface IBookDao {

	Double getPrice(int id) ;

	int updateStock(int id);

	int updateBalance(int uid, Double price);

	User getUser(int uid);

}