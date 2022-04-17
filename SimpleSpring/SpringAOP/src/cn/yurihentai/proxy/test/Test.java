package cn.yurihentai.proxy.test;

import cn.yurihentai.proxy.ProxyUtil;
import cn.yurihentai.proxy.entity.MathImpl;
import cn.yurihentai.proxy.ientity.IMath;

public class Test {

	public static void main(String[] args) {
		ProxyUtil pu = new ProxyUtil(new MathImpl());
		IMath math = (IMath)pu.getProxy();
		System.out.println("result：" + math.multiply(6,9));
		System.out.println("<<<<<<<<<<>>>>>>>>>>");
		System.out.println("result：" + math.divide(1,0));
	}

}