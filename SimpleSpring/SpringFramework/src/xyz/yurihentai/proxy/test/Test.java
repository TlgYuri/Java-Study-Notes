package xyz.yurihentai.proxy.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.yurihentai.bean.Person;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
	public static void main(String[] args) {
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("Beans.xml");
//		test01(ac);
		test02(ac);
	}


	private static void test01(ConfigurableApplicationContext ac) {
		Person yuri = (Person) ac.getBean("yuri");
		Person per = (Person) ac.getBean("factoryBean");
		if (per != null) {
			per.show();
		}
		System.out.println("-------");
		if (yuri != null) {
			yuri.show();
		}
		ac.close();
		System.gc();
	}


	private static void test02(ConfigurableApplicationContext ac) {
		DataSource ds = (DataSource) ac.getBean("dataSource");
		System.out.println(ds);
		try {
			Connection conn = ds.getConnection();
			System.out.println(conn);
			PreparedStatement ps = conn.prepareStatement("select * from stu where sid=?");
			System.out.println(ps);
			ps.setString(1, "ITCAST_0001");
			ResultSet rs = ps.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				System.out.println(rs.getString(1) + "," +
						rs.getString(2) + "," +
						rs.getInt(3) + "," +
						rs.getString(4) + "," +
						rs.getDate(5));
			}
		} catch (SQLException e) {
			System.out.println("出错了");
			e.printStackTrace();
		}
	}
}
