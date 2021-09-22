package xyz.yurihentai.proxy.entity;

import xyz.yurihentai.proxy.ientity.IMath;

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