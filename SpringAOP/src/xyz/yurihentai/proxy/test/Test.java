package xyz.yurihentai.proxy.test;

import xyz.yurihentai.proxy.entity.MathImpl;
import xyz.yurihentai.proxy.ientity.IMath;
import xyz.yurihentai.proxy.ProxyUtil;

public class Test {
	public static void main(String[] args) {
		ProxyUtil pu = new ProxyUtil(new MathImpl());
		IMath math = (IMath)pu.getProxy();
		System.out.println("result：" + math.multiply(6,9));
		System.out.println("<<<<<<<<<<>>>>>>>>>>");
		System.out.println("result：" + math.divide(1,0));
	}
}
