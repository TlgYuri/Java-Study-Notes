package cn.yurihentai.测试;

import cn.yurihentai.实体类.用户;
import cn.yurihentai.实体类.用户信息;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class 测试 {

    @Test
    public void 测试1() {
        String 配置文件名 = "mybatis配置.xml";
        SqlSession 会话 = null;
        try {
            InputStream 输入流 = Resources.getResourceAsStream(配置文件名);
            SqlSessionFactory sql会话工厂 = new SqlSessionFactoryBuilder().build(输入流);
            System.out.println(sql会话工厂);
            会话 = sql会话工厂.openSession();
            用户信息 用户信息1 = 会话.selectOne("用户信息映射.查找", "1145141919810");
            System.out.println(用户信息1);

            int update = 会话.update("用户信息映射.更新", 用户信息1);
            System.out.println("udpate:" + update);

            用户信息1.set编号("13579");
            int delete = 会话.delete("用户信息映射.真删", 用户信息1.get编号());
            System.out.println("delete:" + delete);
            用户信息1.set身份证号("13579");
            int insert = 会话.insert("用户信息映射.新增", 用户信息1);
            System.out.println("insert:" + insert);

            用户信息1 = null;
            用户信息1 = 会话.selectOne("用户信息映射.查找", "13579");
            System.out.println(用户信息1);

            delete = 会话.delete("用户信息映射.删除", 用户信息1.get编号());
            System.out.println("delete:" + delete);
        } catch (IOException 异常) {
            异常.printStackTrace();
        } finally {
            if (会话 != null) {
                会话.commit();
                会话.close();
            }
        }
    }

    @Test
    public void 测试association分步查询() {
        String 配置文件名 = "mybatis配置.xml";
        SqlSession 会话 = null;
        try {
            InputStream 输入流 = Resources.getResourceAsStream(配置文件名);
            SqlSessionFactory sql会话工厂 = new SqlSessionFactoryBuilder().build(输入流);
            System.out.println(sql会话工厂);
            会话 = sql会话工厂.openSession();

            用户 用户1 = 会话.selectOne("cn.yurihentai.映射.用户映射.查找", "1145141919810");
            System.out.println(用户1);
        } catch (IOException 异常) {
            异常.printStackTrace();
        } finally {
            if (会话 != null) {
                会话.commit();
                会话.close();
            }
        }
    }

    @Test
    public void 测试where() {
        String 配置文件名 = "mybatis配置.xml";
        SqlSession 会话 = null;
        try {
            InputStream 输入流 = Resources.getResourceAsStream(配置文件名);
            SqlSessionFactory sql会话工厂 = new SqlSessionFactoryBuilder().build(输入流);
            System.out.println(sql会话工厂);
            会话 = sql会话工厂.openSession();

            用户 用户1 = 会话.selectOne("cn.yurihentai.映射.用户映射.where示例", "1145141919810");
            System.out.println(用户1);
        } catch (IOException 异常) {
            异常.printStackTrace();
        } finally {
            if (会话 != null) {
                会话.commit();
                会话.close();
            }
        }
    }

    @Test
    public void 测试trim() {
        String 配置文件名 = "mybatis配置.xml";
        SqlSession 会话 = null;
        try {
            InputStream 输入流 = Resources.getResourceAsStream(配置文件名);
            SqlSessionFactory sql会话工厂 = new SqlSessionFactoryBuilder().build(输入流);
            System.out.println(sql会话工厂);
            会话 = sql会话工厂.openSession();

            用户 用户1 = 会话.selectOne("cn.yurihentai.映射.用户映射.trim示例", "1145141919810");
            System.out.println(用户1);
        } catch (IOException 异常) {
            异常.printStackTrace();
        } finally {
            if (会话 != null) {
                会话.commit();
                会话.close();
            }
        }
    }

    @Test
    public void 测试choose() {
        String 配置文件名 = "mybatis配置.xml";
        SqlSession 会话 = null;
        try {
            InputStream 输入流 = Resources.getResourceAsStream(配置文件名);
            SqlSessionFactory sql会话工厂 = new SqlSessionFactoryBuilder().build(输入流);
            System.out.println(sql会话工厂);
            会话 = sql会话工厂.openSession();

            用户 用户1 = new 用户();
            用户1.set编号("1145141919810");
            用户1 = 会话.selectOne("cn.yurihentai.映射.用户映射.choose示例", 用户1);
            System.out.println(用户1);

            用户1 = new 用户();
            用户1.set账号("114514");
            用户1 = 会话.selectOne("cn.yurihentai.映射.用户映射.choose示例", 用户1);
            System.out.println(用户1);
        } catch (IOException 异常) {
            异常.printStackTrace();
        } finally {
            if (会话 != null) {
                会话.commit();
                会话.close();
            }
        }
    }

    @Test
    public void 测试set() {
        String 配置文件名 = "mybatis配置.xml";
        SqlSession 会话 = null;
        try {
            InputStream 输入流 = Resources.getResourceAsStream(配置文件名);
            SqlSessionFactory sql会话工厂 = new SqlSessionFactoryBuilder().build(输入流);
            System.out.println(sql会话工厂);
            会话 = sql会话工厂.openSession();

            用户 用户1 = new 用户();
            用户1.set编号("1145141919810");
            用户1.set修改时间(new Date());
            Integer result = 会话.update("cn.yurihentai.映射.用户映射.set示例", 用户1);
            System.out.println(result);
        } catch (IOException 异常) {
            异常.printStackTrace();
        } finally {
            if (会话 != null) {
                会话.commit();
                会话.close();
            }
        }
    }

    @Test
    public void 测试foreach() {
        String 配置文件名 = "mybatis配置.xml";
        SqlSession 会话 = null;
        try {
            InputStream 输入流 = Resources.getResourceAsStream(配置文件名);
            SqlSessionFactory sql会话工厂 = new SqlSessionFactoryBuilder().build(输入流);
            System.out.println(sql会话工厂);
            会话 = sql会话工厂.openSession();

            List<String> list = new ArrayList<>();
            list.add("1145141919810");
            list.add("13579");
            List<用户> 查询结果集 = 会话.selectList("cn.yurihentai.映射.用户映射.foreach示例", list);
            System.out.println(查询结果集);
        } catch (IOException 异常) {
            异常.printStackTrace();
        } finally {
            if (会话 != null) {
                会话.commit();
                会话.close();
            }
        }
    }

}
