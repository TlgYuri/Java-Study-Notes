package xyz.yurihentai.dao.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.yurihentai.dao.IBookDao;
import xyz.yurihentai.entity.User;

@Repository
public class BookDao implements IBookDao {

	@Autowired
	private JdbcTemplate jt;

	@Override
	public Double getPrice(int id) {
		Double price = jt.queryForObject("select price from book where id = ?", new Object[]{id}, Double.class);
		return price;
	}

	public Integer getStock(int id) {
		Integer stock  = jt.queryForObject("select stock from stock where id = ?",new Object[]{id}, Integer.class);
		return stock;
	}

	@Override
	public int updateStock(int id) throws RuntimeException {
		int result = -1;
		Integer stock = getStock(id);
		if(stock >= 1) {
			result = jt.update("update stock set stock = stock - 1 where id=?", new Object[]{id});
		} else {
			throw new RuntimeException("库存不足！");
		}
		return result;
	}

	@Override
	public int updateBalance(int uid, Double price) throws RuntimeException {
		int result = -1;
		Double balance = jt.queryForObject("select balance from userbalance where uid = ?",new Object[]{uid}, Double.class);
		if(balance >= price) {
			result = jt.update("update userbalance set balance = balance - ? where uid = ?", new Object[]{price,uid});
		} else {
			throw new RuntimeException("余额不足!");
		}
		return result;
	}

	@Override
	public User getUser(int uid) {
		User user = jt.queryForObject("select * from userbalance where uid = ?",new BeanPropertyRowMapper<>(User.class), new Object[] {uid});
		return user;
	}
}
