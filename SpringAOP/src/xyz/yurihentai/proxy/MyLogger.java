package xyz.yurihentai.proxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MyLogger {
	public void doBefore(Method method, Object[] args) {
		System.out.println("[before] method name = " + method.getName() + ", args:" + Arrays.toString(args));
	}
	public void doReturn(Method method, Object result) {
		System.out.println("[return] method name = " + method.getName() + ", result:" + result);
	}
	public void doThrowing() {
		System.out.println("throwing exception...");
	}
	public void doAfter() {
		System.out.println("[finally] method done");
	}
}
