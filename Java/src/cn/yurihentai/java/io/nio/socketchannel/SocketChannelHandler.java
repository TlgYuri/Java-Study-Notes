package cn.yurihentai.java.io.nio.socketchannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * description
 * 参考博客：https://blog.csdn.net/ycgslh/article/details/79604074
 * @author Yuri
 * @date 2021/11/1 11:45:14
 */
public class SocketChannelHandler {
    private int bufferSize = 1024;  // 缓冲器容量
    private String localCharset = "UTF-8";  // 编码格式

    public SocketChannelHandler() {
    }

    public SocketChannelHandler(int bufferSize) {
        this(bufferSize, null);
    }

    public SocketChannelHandler(String localCharset) {
        this(-1, localCharset);
    }

    public SocketChannelHandler(int bufferSize, String localCharset) {
        if (bufferSize > 0) {
            this.bufferSize = bufferSize;
        }
        if (localCharset != null) {
            this.localCharset = localCharset;
        }
    }

    /*
    连接请求处理方法
    */
    public void handleAccept(SelectionKey selectionKey) throws IOException {
        // 通过选择器键获取服务器套接字通道，通过 accept() 方法获取套接字通道连接
        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
        // 设置套接字通道为非阻塞模式
        socketChannel.configureBlocking(false);
        // 为套接字通道注册选择器，该选择器为服务器套接字通道的选择器，即选择到该 SocketChannel 的选择器
        // 设置选择器关心请求为读操作，设置数据读取的缓冲器容量为处理器初始化时候的缓冲器容量
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
    }

    public void handleRead(SelectionKey selectionKey) throws IOException {
        // 获取套接字通道
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        // 获取缓冲器并进行重置 ,selectionKey.attachment() 为获取选择器键的附加对象
        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
        byteBuffer.clear();
        // 没有内容则关闭通道
        if (socketChannel.read(byteBuffer) == -1) {
            socketChannel.close();
        } else {
            // 将缓冲器转换为读状态
            byteBuffer.flip();
            // 将缓冲器中接收到的值按 localCharset 格式编码保存
            String receivedRequestData = Charset.forName(localCharset).newDecoder().decode(byteBuffer).toString();
            System.out.println(" 接收到客户端的请求数据： " + receivedRequestData);
            // 返回响应数据给客户端
            String responseData = " 已接收到你的请求数据，响应数据为： ( 响应数据 )";
            byteBuffer = ByteBuffer.wrap(responseData.getBytes(localCharset));
            socketChannel.write(byteBuffer);
            // 关闭通道
            socketChannel.close();
        }
    }
}