package cn.yurihentai.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {

	private Object target;

	public ProxyUtil(Object entity) {
		this.target = entity;
	}

	public Object getProxy() {
		ClassLoader loader = this.getClass().getClassLoader();
		Class[] interfaces = target.getClass().getInterfaces();

		return Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {
			MyLogger ml = new MyLogger();

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) {
				try {
					ml.doBefore(method, args);
					System.out.println("----------------\nmethod is running\n----------------");
					Object obj = method.invoke(ProxyUtil.this.target, args);
					ml.doReturn(method, obj);
					return obj;
				} catch (Exception e) {
					ml.doThrowing();
					e.printStackTrace();
				} finally {
					ml.doAfter();
				}
				return null;
			}

		});
	}

}