package cn.yurihentai.handler;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
		System.out.println("II.pre:开始前置处理,准备初始化");
		System.out.println(s + "," + o);
		return o;
	}

	@Override
	public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
		System.out.println("II.after:初始化完成了，开始后置处理");
		System.out.println(s + '\n');
		return o;
	}
}
