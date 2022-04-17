package cn.yurihentai.service;

import cn.yurihentai.entity.User;

public interface IBookService {

	boolean buyBook(int uid, int id);

	User getUser(int uid) ;

}
