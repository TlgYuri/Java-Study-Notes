package xyz.yurihentai.java.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * description
 * 本笔记学习自：http://ifeve.com/java-network/
 *
 * @author Yuri
 * @date 2021/11/5 11:45:14
 */
public class Net {

    public static void main(String args[]) throws Exception {
        Net.testSocket();
        Net.testDatagramSocket();
        Net.testUrl();
    }

    /**
     * TCP连接 Socket
     */
    public static void testSocket() {
        new Thread(() -> {
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
        }, "server").start();

        new Thread(() -> {
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
        }, "client").start();

    }

    /**
     * UDP DatagramSocket
     */
    public static void testDatagramSocket() {
        // ======= 发送 =======
        new Thread(() -> {
            try {
                DatagramSocket datagramSocket = new DatagramSocket();
                // 单个UDP数据包可发送的数据最大长度为65508  具体参考计算机网络原理
//                byte[] buffer = new byte[65508];
//                InetAddress address = InetAddress.getByName("yurihentai.xyz");
//                InetAddress address = InetAddress.getByAddress("127.0.0.1".getBytes());
                byte[] buffer = "Hello World!".getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 80);
                datagramSocket.send(packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "send").start();

        // ======= 接收 =======
        new Thread(() -> {
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
        }, "receive").start();
    }

    public static void testUrl() throws Exception {
        URL url = new URL("http://yurihentai.xyz");
        URLConnection urlConnection = url.openConnection();
        InputStream input = urlConnection.getInputStream();
        int data = input.read();
        while(data != -1){
            System.out.print((char) data);
            data = input.read();
        }
        input.close();
    }

}