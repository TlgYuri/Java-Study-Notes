package cn.yurihentai.springboot;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MySpringBootApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void encrypt() {
        // jdk1.8  报错EncryptionOperationNotPossibleException  从这个链接下载jce8扔到JAVA_HOME/jre/lib/security下面解决问题
        // https://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
        String username = encryptor.encrypt("root");
        System.out.println(username);
        String password = encryptor.encrypt("Yuri");
        System.out.println(password);
    }

}