package xyz.yurihentai.io;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

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
            buffer.put((byte)65);   // 向缓冲区写入数据
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
    /** SocketChannel */
    public void testSocketChannel() throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));

        ByteBuffer allocate = ByteBuffer.allocate(64);
        int readCount = socketChannel.read(allocate);

        System.out.println(allocate.get());

        socketChannel.close();
    }

    @Test
    /** Scatter/Gather 分散、聚集 */
    public void testScatterAndGather() throws Exception {
        RandomAccessFile file = new RandomAccessFile("E:\\testInput.txt", "rw");
        FileChannel channel = file.getChannel();    // 获取文件通道

        ByteBuffer header = ByteBuffer.allocate(64);
        ByteBuffer body = ByteBuffer.allocate(128);
        ByteBuffer[] bufferArray = { header, body };
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
        // SocketChannel可能存在写入数据不完全的情况 ↓
        outputChannel.transferFrom(inputChannel, position, count);  // 从output的position开始写入input的数据，最多写入count个  input数据少于count或output的capacity小于count等情况则以实际为准
        inputChannel.transferTo(position, count, outputChannel);

        input.close();
        output.close();
    }

    @Test
    /** Selector 管理多个channel */
    public void testSelector() throws Exception {
        //TODO 创建channel
//        Selector selector = Selector.open();
//        channel.configureBlocking(false);
//        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

    }


}