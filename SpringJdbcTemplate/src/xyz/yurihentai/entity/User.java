package xyz.yurihentai.entity;

public class User {

	private int uid;
	private String name;
	private String balance;

	public User() {}

	public User(int uid, String name, String balance) {
		this.uid = uid;
		this.name = name;
		this.balance = balance;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User{" +
				"uid=" + uid +
				", name='" + name + '\'' +
				", balance='" + balance + '\'' +
				'}';
	}

}