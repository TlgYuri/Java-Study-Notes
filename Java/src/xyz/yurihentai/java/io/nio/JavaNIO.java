package xyz.yurihentai.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * description
 * 本笔记学习自：http://ifeve.com/java-nio-all/
 *
 * @author Yuri
 * @date 2021/10/27 11:45:14
 */
public class JavaNIO {
//    NIO始于jdk1.4，
//    1、Channels and Buffers（通道和缓冲区）：
//      标准的IO基于字节流和字符流进行操作的，而NIO是基于通道（Channel）和缓冲区（Buffer）进行操作，数据总是从通道读取到缓冲区中，或者从缓冲区写入到通道中。
//    2、Non-blocking IO（非阻塞IO）：
//      Java NIO可以让你非阻塞的使用IO，例如：当线程从通道读取数据到缓冲区时，线程还是可以进行其他事情。当数据被写入到缓冲区时，线程可以继续处理它。从缓冲区写入通道也类似。
//    3、Selectors（选择器）：
//      Java NIO引入了选择器的概念，选择器用于监听多个通道的事件（比如：连接打开，数据到达）。因此，单个的线程可以监听多个数据通道。

    @Test
    /** FileChannel */
    public void testChannel() throws Exception {
        RandomAccessFile file = new RandomAccessFile("E:\\testInput.txt", "rw");
        FileChannel fileChannel = file.getChannel();    // 获取文件通道
        // 数据会优先缓存在内存中以提高效率，此方法强制将数据写入磁盘，防止数据写入channel后没有及时写入文件中
        fileChannel.force(true);

        long fileSize = fileChannel.size(); // 获取channel关联的文件的大小

        FileChannel truncate = fileChannel.truncate(3); // 将源文件裁剪到只剩 指定数量 个数据，会修改源文件
        long truncateSize = truncate.size();
        fileSize = fileChannel.size();

        ByteBuffer buffer = ByteBuffer.allocate(64);    // 申请分配缓冲区空间

        int bytesRead = fileChannel.read(buffer);   // NIO的读写通过channel和buffer
        while (bytesRead != -1) {
            System.out.println("读取到的数据长度：" + bytesRead);
            /* buffer读写模式(capacity、limit、position)：
                 写模式：capacity=buffer容量
                        limit=capacity，表示可写入数据量
                        position为当前指针位置，每写入一个数据自动后移一位
                 读模式：capacity=buffer容量
                        limit=已经写入的数据容量，相当于写模式最后的position
                        position=当前尧读取的数据位置
            */
            buffer.put((byte) 65);   // 向缓冲区写入数据
            buffer.flip();  //从写模式切换到读模式
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());    // 获取缓冲区的数据
            }

            long position = fileChannel.position(); // 获取当前的position
            System.out.println(position);
//            fileChannel.position(position - 2);    //  更改当前的position

//            buffer.rewind();  // 将buffer的position重置为0，可以重读buffer中的数据
            buffer.clear(); // 清空整个缓冲区空间,只是重置limit等参数，并未清除数据  只清除position之前的数据，如果此前更改了position，则可能导致数据清空不完全
//            buffer.compact()    // 将未读数据移到开头，设置position到最后一个数据后，此时可以开始向后追加写入数据
            buffer.mark();  // 标记当前的position
            buffer.reset(); // 恢复到上次mark()设置的position
            bytesRead = fileChannel.read(buffer);
        }

        file.close();
    }

    @Test
    /** ServerSocketChannel SocketChannel */
    public void testSocketChannel() throws Exception {
        // 创建channel，建立连接
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("127.0.0.1", 8080));
        // 设置为非阻塞模式
        // 此时调用connect()，该方法可能在连接建立之前就返回了。为了确定连接是否建立，可以调用finishConnect()方法
        // while(!finishConnect) { }  在连接建立成功前等待、或做其他操作
        channel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(64);
        // 读取数据
        int readCount = channel.read(buffer);
        System.out.println("长度:" + readCount);

        buffer.flip();
        while (buffer.hasRemaining()) {
            // 写数据
            channel.write(buffer);
        }

        // 关闭通道
        channel.close();
    }

    @Test
    /** ServerSocketChannel */
    public void testServerSocketChannel() throws Exception {
        // 创建ServerSocketChannel，监听端口
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(8080));
        // 设置为非阻塞模式，accpet()方法在没收到新的连接的情况下直接返回null
        serverChannel.configureBlocking(false);
        while (true) {
            SocketChannel channel = serverChannel.accept();
            if (channel != null) {
                // do
                channel.close();
                break;
            }
        }
        // 关闭通道
        serverChannel.close();
    }

    @Test
    /** ServerSocketChannel 和 SocketChannel 简单联调使用 */
    public void testServerSocketAndSocketChannel() throws Exception {
        // 创建一个线程执行ServerSocketChannel 监听端口并执行读写操作
        new Thread(() -> {
            try {
                ServerSocketChannel server = ServerSocketChannel.open();
                server.bind(new InetSocketAddress(8080));

                SocketChannel channel = server.accept();
                ByteBuffer buffer = ByteBuffer.allocate(64);
                buffer.put((byte) 65);
                buffer.put((byte) 66);
                buffer.put((byte) 67);
                buffer.flip();
                channel.write(buffer);
                buffer.clear();
                channel.read(buffer);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get());
                }
                channel.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "Server").start();

        Thread.sleep(1000);

        // 本线程内使用SocketChannel连接server 执行读写操作
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("127.0.0.1", 8080));
        ByteBuffer buffer = ByteBuffer.allocate(64);
        channel.read(buffer);
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println((char) buffer.get());
        }
        buffer.clear();
        buffer.put((byte) 68);
        buffer.put((byte) 69);
        buffer.put((byte) 70);
        buffer.flip();
        channel.write(buffer);
        channel.close();
    }

    @Test
    /** Scatter/Gather 分散、聚集 */
    public void testScatterAndGather() throws Exception {
        RandomAccessFile file = new RandomAccessFile("E:\\testInput.txt", "rw");
        FileChannel channel = file.getChannel();    // 获取文件通道

        ByteBuffer header = ByteBuffer.allocate(64);
        ByteBuffer body = ByteBuffer.allocate(128);
        ByteBuffer[] bufferArray = {header, body};
        channel.read(bufferArray);  // 此种方式当第一个buffer被写满后才会写入第二个buffer，因此对数据的格式大小要求非常严格

        channel.write(bufferArray); // buffer中有多少写入多少

        file.close();
    }

    @Test
    /** 通道间的数据传输 */
    public void testChannelTransfer() throws Exception {
        RandomAccessFile input = new RandomAccessFile("E:\\testInput.txt", "rw");
        FileChannel inputChannel = input.getChannel();
        RandomAccessFile output = new RandomAccessFile("E:\\testOnput.txt", "rw");
        FileChannel outputChannel = output.getChannel();
        long position = 0;
        long count = inputChannel.size();
        // SocketChannel只会传输此刻准备好的数据，可能存在写入数据不足count的情况 ↓
        outputChannel.transferFrom(inputChannel, position, count);  // 从output的position开始写入input的数据，最多写入count个  input数据少于count或output的capacity小于count等情况则以实际为准
        inputChannel.transferTo(position, count, outputChannel);

        input.close();
        output.close();
    }

    @Test
    /** Selector 管理多个channel */
    public void testSelector() throws Exception {
        // 1、创建ssc
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(8080));
        // 要使用selector必须为非阻塞模式
        server.configureBlocking(false);

        // 2、创建selector
        Selector selector = Selector.open();
        // 3、将channel注册到Selector上
        // register()方法将channel注册到指定的selector上
        // register()方法第二个参数指定要监听什么事件  共四种：read、write、accept、connect
        // 通道触发事件指该事件已经就绪。channel成功连接到另一个服务器称为“连接就绪”。一个ssc准备好接收新进入的连接称为“接收就绪”。一个有数据可读的通道可以说是“读就绪”。等待写数据的通道可以说是“写就绪”
        // 监听多种事件可以用“|”连接：int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
        SelectionKey selectionKey = server.register(selector, SelectionKey.OP_READ);
        /* SelectionKey包含的内容
               interestSet：通过与操作判断Set中是否包含了某种事件
                            int interestSet = selectionKey.interestOps();
                            boolean isInterestedInAccept  = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT；
                            boolean isInterestedInConnect = interestSet & SelectionKey.OP_CONNECT;
                            boolean isInterestedInRead    = interestSet & SelectionKey.OP_READ;
                            boolean isInterestedInWrite   = interestSet & SelectionKey.OP_WRITE;

               ready集合：通道已经准备就绪的操作的集合，通过interestSet类似手段判断是否就绪，或使用方法
                            int readySet = selectionKey.readyOps();
                            selectionKey.isAcceptable();
                            selectionKey.isConnectable();
                            selectionKey.isReadable();
                            selectionKey.isWritable();

               Channel：从SelectionKey访问Channel
                            Channel  channel  = selectionKey.channel();

               Selector：从SelectionKey访问Selector
                            Selector keySelector = selectionKey.selector();

               附加对象：
                         1、通过selectKey附加
                             附加：selectionKey.attach(theObject);
                             获取：Object attachedObj = selectionKey.attachment();
                         2、通过register()方法，在注册时附加
                             SelectionKey key = channel.register(selector, SelectionKey.OP_READ, theObject);
        */
        // 4、通过Selector选择通道，返回值为此方法执行后就绪的通道个数
        selector.select();  // 阻塞执行，直到至少有一个通道在你注册的事件上就绪了。
//        selector.select(1000);  // 阻塞执行，直到超过timeout毫秒，或至少有一个通道在你注册的事件上就绪了。
//        selector.selectNow(); // 立即返回，如果没有就绪的通道则返回0
        // 5、获取已就绪的通道的key
        Set selectedKeys = selector.selectedKeys();
        Iterator keyIterator = selectedKeys.iterator();
        while (keyIterator.hasNext()) {
            SelectionKey key = (SelectionKey) keyIterator.next();
            if (key.isAcceptable()) {
                // a connection was accepted by a ServerSocketChannel
            } else if (key.isConnectable()) {
                // a connection was established with a remote server.
            } else if (key.isReadable()) {
                // a channel is ready for reading
            } else if (key.isWritable()) {
                // a channel is ready for writing
            }
            keyIterator.remove();
        }
        // 6、关闭Selector及所有相关SelectionKey实例，不会关闭通道本身
        selector.close();
    }

    @Test
    /** DatagramChannel */
    public void testDatagramChannel() throws Exception {
        // 打开通道 绑定端口
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(8080));

        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.clear();
        // 接收数据  超出buffer容量的数据将被废弃
        channel.receive(buffer);

        buffer.clear();
        buffer.put("Hello World!".getBytes());
        buffer.flip();

        // 发送数据  返回发送的字节数
        // 基于udp，无法确认对方是否收到
        int bytesSent = channel.send(buffer, new InetSocketAddress("127.0.0.1", 8080));
        buffer.clear();

        // UDP是无连接，因此connect()方法并不会真的建立一个连接，只是锁住channel，只从特定地址收发数据
        channel.connect(new InetSocketAddress("127.0.0.1", 8080));
        // 这样类似于传统通道的读写操作，但在数据传送方面没有任何保证
        channel.read(buffer);
        buffer.flip();
        channel.write(buffer);

        channel.close();
    }

    @Test
    /** Pipe 管道 */
    public void testPipe() throws Exception {
        // 管道是2个线程之间的单向数据连接
        // ThreadA → (Pipe)sinkChannel → (Pipe)sourceChannel → ThreadB
        Pipe pipe = Pipe.open();
        new Thread(() -> {
            Pipe.SinkChannel sink = pipe.sink();
            ByteBuffer buffer = ByteBuffer.allocate(64);
            buffer.clear();
            buffer.put("Hello World!".getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                try {
                    int write = sink.write(buffer);
                    System.out.println(Thread.currentThread().getName() + "传输的字节长度为：" + write);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "ThreadA").start();
        new Thread(() -> {
            Pipe.SourceChannel sourceChannel = pipe.source();
            ByteBuffer buffer = ByteBuffer.allocate(64);
            try {
                int bytesRead = sourceChannel.read(buffer);
                System.out.println(Thread.currentThread().getName() + "读取到字节长度为：" + bytesRead);
                buffer.flip();
                while(buffer.hasRemaining()) {
                    System.out.print((char)buffer.get());
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "ThreadB").start();
    }

    //TODO Path、Files、AsynchronousFileChannel

}