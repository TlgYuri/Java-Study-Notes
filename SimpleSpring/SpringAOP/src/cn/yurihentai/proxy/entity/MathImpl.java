package cn.yurihentai.proxy.entity;

import cn.yurihentai.proxy.ientity.IMath;

public class MathImpl implements IMath {

	@Override
	public int add(int x, int y) {
		return x + y;
	}

	@Override
	public int sub(int x, int y) {
		return x - y;
	}

	@Override
	public int multiply(int x, int y) {
		return x * y;
	}

	@Override
	public int divide(int x, int y) {
		return x / y;
	}

}