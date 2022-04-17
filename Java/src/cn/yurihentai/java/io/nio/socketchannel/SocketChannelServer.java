package cn.yurihentai.java.io.nio.socketchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * description
 * 参考博客：https://blog.csdn.net/ycgslh/article/details/79604074
 * @author Yuri
 * @date 2021/11/1 11:45:14
 */
public class SocketChannelServer {

    public static void main(String[] args) {
        try {
            // 创建 ServerSocketChannel 通道，绑定监听端口为 8080
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
            // 设置为非阻塞模式，否则无法使用Selector
            serverSocketChannel.configureBlocking(false);
            // 注册选择器 , 设置选择器选择的操作类型
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // 创建处理器
            SocketChannelHandler handler = new SocketChannelHandler(1204);
            while (true) {
                // 等待请求，每次等待阻塞 3s ，超过时间则向下执行，若传入 0 或不传值，则在接收到请求前一直阻塞
                if (selector.select(3000) == 0) {
                    System.out.println(" 等待请求超时 ......");
                    continue;
                }
                System.out.println("----- 处理请求 -----");
                // 获取待处理的选择键集合
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey selectionKey = keyIterator.next();
                    try {
                        // 如果是连接请求，调用处理器的连接处理方法
                        if (selectionKey.isAcceptable()) {
                            handler.handleAccept(selectionKey);
                        }
                        // 如果是读请求，调用对应的读方法
                        if (selectionKey.isReadable()) {
                            handler.handleRead(selectionKey);
                        }
                    } catch (IOException e) {
                        keyIterator.remove();
                        continue;
                    }
                }
                // 处理完毕从待处理集合移除该选择键
                keyIterator.remove();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}