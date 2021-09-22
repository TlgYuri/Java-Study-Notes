package xyz.yurihentai.service;

import xyz.yurihentai.entity.User;

public interface IBookService {

	boolean buyBook(int uid, int id);

	User getUser(int uid) ;

}
