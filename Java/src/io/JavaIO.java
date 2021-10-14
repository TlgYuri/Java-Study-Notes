package io;

import org.junit.Test;

import java.io.*;

/**
 * Java基础 IO相关
 *
 * @author Yuri
 * @date 2021/10/13 11:45:14
 */
public class JavaIO {

    @Test
    public void testFile() {
        try {
            File input = new File("E:\\testInput.txt");
            File output = new File("E:\\testOutput.txt");
            if (!input.getParentFile().exists()) {
                input.getParentFile().mkdirs();
            }
            if (!output.getParentFile().exists()) {
                output.getParentFile().mkdirs();
            }
            if (!input.exists()) {
                input.createNewFile();
            }
            if (!output.exists()) {
                output.createNewFile();
            }
            System.out.println(input.isDirectory());
            System.out.println(input.isFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    /** 文件字符流 操作的是字符数据 */
    public void testFileReaderWriter() {
        Reader reader = null;
        Writer writer = null;
        try {
            reader = new FileReader("E:\\testInput.txt");
            writer = new FileWriter(new File("E:\\testOutput.txt"));

            // 读取一个字节 1-255 读取不到返回-1
            int read = reader.read();
            System.out.println(read);
            if (read != -1) {
                System.out.println((char) read);
                writer.write(read);
            }

            // 读取指定长度的字节数据  返回获取到的数据长度  返回-1表示文件到底
            char[] chars = new char[1024];
            int length;
            while ((length = reader.read(chars)) != -1) {
                System.out.println(new String(chars));
                writer.write(chars, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally { //关闭资源
            try {
                if (reader != null) {
                    writer.flush();  // 清空缓冲区，将数据保存到文件中
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    /** 文件字节流  操作的是字节数据 */
    public void testFileStream() {
        // jdk1.7引入的try-resources语法糖 在try的()中定义资源  编译后将自动增加finally块 执行资源的close()方法 (此处的资源必须实现AutoCloseable)
        // try() {} catch() {}
        try (
                // 参数可以是 文件路径 或 File类对象
                InputStream input = new FileInputStream("E:\\testInput.txt");
                // 创建输出流  指定是否追加，默认false，代表覆盖原有内容
                OutputStream output = new FileOutputStream(new File("E:\\testOutput.txt"), true);
        ) {
            // 读取一个字节 1-255 读取不到返回-1
            int read = input.read();
            System.out.println(read);
            if (read != -1) {
                System.out.println((char) read);
                output.write(read);
            }
            // 读取指定长度的字节数据  返回获取到的数据长度  返回-1表示文件到底
            byte[] bytes = new byte[1024];
            int length;
            while ((length = input.read(bytes)) != -1) {
                System.out.println(new String(bytes));
                output.write(bytes, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /** 管道流 用于线程间简单的数据交换 */
    public void testPipedStream() {
        try (final PipedInputStream input = new PipedInputStream();
             final PipedOutputStream output = new PipedOutputStream(input);) {
//            input.connect(output);  // 构造方法传递的PipedOutputStream对象用于调用input的connect方法，最终调用的是output.connect方法连接this
//            output.connect(input);  // 作用与 通过input的构造传递output 或 input.connect(ouotput)一致
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + ", start to write");
                    output.write("Hello PipedStream!".getBytes());
                    output.close(); // 写操作完成后必须关闭资源  否则会报错 java.io.IOException: Write end dead
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "outputPipe").start();

            new Thread(() -> {
                byte[] bytes = new byte[1024];
                try {
                    while (input.read(bytes) != -1) {
                        System.out.println(Thread.currentThread().getName() + ", " + new String(bytes));
                    }
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "inputPipe").start();

            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}