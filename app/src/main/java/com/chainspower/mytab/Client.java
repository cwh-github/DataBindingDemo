package com.chainspower.mytab;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Date：2019/2/18-17:31
 * Author: cwh
 */
public class Client {

    public static void main(String[] args) {
//        try {
//            startClient();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        try {
            UDPClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startClient() throws IOException, InterruptedException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("127.0.0.1", 34568));
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入语句(以回车结束)：");
        String text = null;
        while (scanner.hasNextLine()) {
            text = scanner.nextLine();
            if (text != null && text.length() > 0) {
                break;
            }
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.clear();
        byteBuffer.put(text.getBytes());
        byteBuffer.flip();
        while (!channel.finishConnect()) {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("正在等待连接...");
        }
        while (byteBuffer.hasRemaining()) {
            channel.write(byteBuffer);
        }
        byteBuffer.clear();
        channel.close();
    }

    public static void UDPClient() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.connect(new InetSocketAddress("127.0.0.1", 34563));
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入语句(以回车结束)：");
        String text = null;
        while (scanner.hasNextLine()) {
            text = scanner.nextLine();
            if (text != null && text.length() > 0) {
                break;
            }
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(text.getBytes());
        byteBuffer.flip();
        datagramChannel.write(byteBuffer);
        byteBuffer.clear();
    }
}
