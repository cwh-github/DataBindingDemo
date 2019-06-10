package com.chainspower.mytab;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Description:
 * Date：2019/2/18-17:31
 * Author: cwh
 */
public class Server {

    public static void main(String[] args) {
//        try {
//            startServer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            UDPServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startServer() throws IOException {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(34568));
        serverSocketChannel.configureBlocking(false);
        while (true){
            SocketChannel socketChannel=serverSocketChannel.accept();
            if(socketChannel!=null){
                ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                int len=0;
                while ((len=socketChannel.read(byteBuffer))!=-1){
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String string = new String(bytes, "utf-8");
                    System.out.print(string);
                    byteBuffer.clear();
                }
            }
        }
    }

    public static void UDPServer() throws IOException {
        DatagramChannel datagramChannel=DatagramChannel.open();
        datagramChannel.socket().bind(new InetSocketAddress(34563));
        while (true){
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            datagramChannel.receive(byteBuffer);
            byteBuffer.flip();
            System.out.println("Block On This");
            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);
            String string = new String(bytes, "utf-8");
            System.out.print(string);
            byteBuffer.clear();
        }
    }


}
