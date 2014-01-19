package org.panorama.kasun;


import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class WriteEventHandler implements EventHandler {
    public void handleEvent(SelectionKey handle) throws Exception {
        System.out.println("===== Write Event Handler =====");

        SocketChannel socketChannel =
                (SocketChannel) handle.channel();
        //ByteBuffer bb = ByteBuffer.wrap("Hello Client!\n".getBytes());
        ByteBuffer inputBuffer = (ByteBuffer) handle.attachment();
        socketChannel.write(inputBuffer);
        socketChannel.close();
    }
}
