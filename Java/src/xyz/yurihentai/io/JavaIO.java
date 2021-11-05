package xyz.yurihentai.io;

import xyz.yurihentai.bean.SerializableBean;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * Java基础 IO相关学习记录
 * 学习文章来源：http://ifeve.com/java-io/
 * @author Yuri
 * @date 2021/10/13 11:45:14
 */
public class JavaIO {

    @Test
    /** java.io.File 文件类 */
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
            System.out.println("文件字节数：" + input.length());
//            input.delete(); //删除文件
//            input.renameTo(new File("C:\\data\\new-file.txt"));  //重命名、移动文件
//            System.out.println(input.list()); // 读取目录下的文件列表
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

            // 读取一个字节 1-65535 读取不到返回-1
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

    @Test
    /** 数组流 处理字符或字节数组 */
    public void testArrayStream() {
        // 字符流与此类似，改为CharArrayReader和CharAraryWriter，操作char数组即可
        byte[] buf = "testIn".getBytes();
        ByteArrayInputStream input = new ByteArrayInputStream(buf);
        int data;
        while ((data = input.read()) != -1) {
            System.out.println((char) data + ":" + data);
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            output.write("testOut".getBytes("UTF-8"));
            byte[] bytes = output.toByteArray();
            System.out.println(Arrays.toString(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    /** 系统流 */
    public void testSystemStream() throws Exception {
        //控制台System.in 控制台输出System.out System.error
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("======= 请输入数据(输入exit退出)： ");
            String str = scanner.nextLine();//获取下一个整行输入
            if ("exit".equals(str)) {
                System.out.println("\n======= 已退出 =======");
                break;
            }
            System.out.println(str);
        }
        scanner.close();

        //替换系统流 将内容输出到指定文件中
        System.out.println("\n======= 开始通过系统流向文件输出数据 =======");
        OutputStream output = new FileOutputStream(new File("E:\\testOutput.txt"), true);
        PrintStream printOut = new PrintStream(output);
        System.setOut(printOut);
        System.out.println("test System Stream");
        System.out.flush();
        printOut.close();
    }

    @Test
    /** RandomAccessFile 随机读写文件 */
    public void testRandomAccessFile() throws Exception {
        RandomAccessFile file = new RandomAccessFile("E:\\testInput.txt", "rw");  // r:读 w:写
        System.out.println("当前文件指针位置：" + file.getFilePointer());  //获取当前文件的指针位置，从0开始
        file.seek(1); // 移动文件指针
        System.out.println("移动指针后，文件指针位置：" + file.getFilePointer());
        int data = file.read(); //方法执行完成后自动后移指针
        if (data < 97) {
            file.write(data - 32); //方法执行完成后自动后移指针
        } else {
            file.write(data + 32);
        }
        System.out.println(data);
        System.out.println("读取到数据：" + (char) data + "，当前文件指针位置：" + file.getFilePointer());
        file.close();
    }

    @Test
    /** BufferdStream 缓冲 */
    public void testBufferdStream() throws Exception {
        /*
            为输入流提供缓冲区，能提高很多IO的速度。
            可以一次读取一大块的数据，而不需要每次从网络或者磁盘中一次读取一个字节。
            特别是在访问大量磁盘数据时，缓冲通常会让IO快上许多。
        */
        // 创建缓冲流，指定流内缓冲区的大小（默认位8 * 1024
        InputStream input = new BufferedInputStream(new FileInputStream("c:\\data\\input-file.txt"), 8 * 1024);
        input.close();
    }

    @Test
    /** DataStream 读取、输出Java基本类型 */
    public void teatDataInputStream() throws Exception {
        //输入
        DataInputStream input = new DataInputStream(new FileInputStream("binary.data"));
        int aByte = input.read();
        int anInt = input.readInt();
        float aFloat = input.readFloat();
        double aDouble = input.readDouble();
        input.close();

        //输出
        DataOutputStream output = new DataOutputStream(new FileOutputStream("binary.data"));
        output.write(45);
        //byte data output.writeInt(4545);
        //int data output.writeDouble(109.123);
        //double data  output.close();
        output.close();
    }

    @Test
    /** 序列化 ObjectStream */
    public void testObjectStream() throws Exception {
        SerializableBean obj = new SerializableBean();
        obj.setName("Yuri");
        obj.setAge("24");
        obj.setGender("男");
        // 序列化
        FileOutputStream output = new FileOutputStream("E:\\Object.data");
        ObjectOutputStream objOutput = new ObjectOutputStream(output);
        objOutput.writeObject(obj);

        FileInputStream input = new FileInputStream("E:\\Object.data");
        ObjectInputStream objInput = new ObjectInputStream(input);
        Object o = objInput.readObject();
        System.out.println(o);
    }

    @Test
    /** PushbackInputStream 可以 将读到的数据重新推回inputStream中*/
    public void testPushbackInputStream() throws Exception {
        //PushBackReader与此类似
        //指定缓冲区  决定了可写回的字节大小
        PushbackInputStream input = new PushbackInputStream(new FileInputStream("E:\\testInput.txt"), 8);
        int data = input.read();
        System.out.println((char) data);
        input.unread(data);  //将数据写回输入流
        System.out.println((char) input.read());
    }

    @Test
    /** SequenceInputStream 将多个流联通，逐个读取 */
    public void testSequenceInputStream() throws Exception {
        InputStream input1 = new FileInputStream("E:\\testInput.txt");
        InputStream input2 = new FileInputStream("E:\\testOutput.txt");
        InputStream combined = new SequenceInputStream(input1, input2); // 当input1内容读完后，从input2中读取数据
        int read = combined.read();
        System.out.println((char) read);
        combined.close();
    }

    @Test
    /** PrintStream 将数据原样文本化输出，而不是输出为字节数据 */
    public void testPrintStream() throws Exception {
        //PringWriter与此类似
        FileOutputStream output = new FileOutputStream("E:\\testOutput.txt");
        PrintStream print = new PrintStream(output);
        print.print(true);
        print.print(123);
        print.print(123.456f);
        print.printf(Locale.UK, "Text + data: %1$", 123);
        print.close();
    }

    @Test
    /** StringReader 将String转换为Reader */
    public void testStringReader() throws Exception {
        Reader reader = new StringReader("input string...");
        int data = reader.read();
        while (data != -1) {
            data = reader.read();
        }
        reader.close();

        //StringWriter
        StringWriter writer = new StringWriter();
        writer.write(65);   //写入数据
        System.out.println(writer.toString());  //获取StringWriter中的字符串数据
        StringBuffer dataBuffer = writer.getBuffer();   //获取StringWriter在内部构造字符串时所使用的StringBuffer对象
        System.out.println(dataBuffer);
    }

}