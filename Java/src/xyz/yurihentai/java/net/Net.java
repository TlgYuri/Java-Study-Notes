package xyz.yurihentai.java.net;

import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * description
 * 本笔记学习自：http://ifeve.com/java-network/
 *
 * @author Yuri
 * @date 2021/11/5 11:45:14
 */
public class Net {

    public static void main(String[] args) throws Exception {
        Net.testUrl();
    }

    @Test
    /** InetAddress */
    public void testInetAddress() throws UnknownHostException {
//        InetAddress 是 Java 对 IP 地址的封装。这个类的实例经常和 UDP DatagramSockets 和 Socket，ServerSocket 类一起使用。
        // 根据域名获取实例
        InetAddress address1 = InetAddress.getByName("yurihentai.xyz");
        // 根据IP地址获取实例
        InetAddress address2 = InetAddress.getByName("127.0.0.1");
        // 获取本机ip的实例
        InetAddress address3 = InetAddress.getLocalHost();
        // ……do it yourself……
    }

    @Test
    /** TCP连接 Socket */
    public void testSocket() {
        Thread ta = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(80)) {
                boolean isStopped = false;
                while (!isStopped) {
                    Socket clientSocket = serverSocket.accept();

                    // ======= 读取数据 =======
                    // 只有在连接关闭时才会返回-1  因此需要直到传递的数据长度，或者在数据的末尾约定特殊字符用于判断结束
                    InputStream in = clientSocket.getInputStream();
                    int data = 0;
                    while ((data = in.read()) != (int) '&') {
                        System.out.print((char) data);
                    }
                    isStopped = true;
                    System.out.println();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "server");

        Thread tb = new Thread(() -> {
            try (Socket socket = new Socket("127.0.0.1", 80);
                 OutputStream out = socket.getOutputStream()) {
                // ======= 写入数据 =======
                out.write("some data&".getBytes(StandardCharsets.UTF_8));
                // 操作系统底层的TCP/IP实现会先将数据放入一个更大的数据缓存块中，缓存块的大小与TCP/IP的数据包大小相适应。
                // 调用flush()方法只是将数据写入操作系统缓存中，并不保证数据会立即发送
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "client");
        ta.start();
        tb.start();

        try {
            ta.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    /** UDP DatagramSocket */
    public void testDatagramSocket() {
        // ======= 发送 =======
        Thread ta = new Thread(() -> {
            try {
                DatagramSocket datagramSocket = new DatagramSocket();
                // 单个UDP数据包可发送的数据最大长度为65508  具体参考计算机网络原理
//                byte[] buffer = new byte[65508];
                byte[] buffer = "Hello World!".getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 80);
                datagramSocket.send(packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "send");

        // ======= 接收 =======
        Thread tb = new Thread(() -> {
            try {
                DatagramSocket datagramSocket = new DatagramSocket(80);
                byte[] buffer = new byte[65508];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                // 阻塞执行，直到收到数据为止
                datagramSocket.receive(packet);
                buffer = packet.getData();
                System.out.println(new String(buffer, StandardCharsets.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "receive");

        ta.start();
        tb.start();

        try {
            ta.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testUrl() throws Exception {
        // 访问远程资源
        URL url = new URL("http://yurihentai.xyz");
        URLConnection urlConnection = url.openConnection();
        // 默认发送get请求
        InputStream input = urlConnection.getInputStream();
        int data = input.read();
        while (data != -1) {
            System.out.print((char) data);
            data = input.read();
        }
        input.close();

        // POST
        URL urlPost = new URL("http://yurihentai.xyz");
        URLConnection urlPostConnection = urlPost.openConnection();
        urlPostConnection.setDoOutput(true);
        OutputStream outputStream = urlPostConnection.getOutputStream();
        String postData = URLEncoder.encode("Hello World!", StandardCharsets.UTF_8.name());
        outputStream.write(postData.getBytes());
        outputStream.flush();
        outputStream.close();


        // 打开本地文件
        URL localUrl = new URL("file:/e:/testInput.txt");
        URLConnection localUrlConnection = url.openConnection();
        InputStream localInput = localUrlConnection.getInputStream();
        int localData = input.read();
        while (localData != -1) {
            System.out.print((char) localData);
            localData = input.read();
        }
        localInput.close();
    }

    @Test
    /** JarURLConnection */
    public void testJarUrlConnection() throws Exception {
        String urlString = "http://butterfly.jenkov.com/container/download/jenkov-butterfly-container-2.9.9-beta.jar";
        URL jarUrl = new URL(urlString);
        JarURLConnection connection = null;
//        connection new JarURLConnection(jarUrl);
        Manifest manifest = connection.getManifest();
        JarFile jarFile = connection.getJarFile();
    }

}