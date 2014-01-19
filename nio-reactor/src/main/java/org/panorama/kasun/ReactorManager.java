package org.panorama.kasun;


import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

public class ReactorManager {
    private static final int SERVER_PORT = 7070;

    public void startReactor(int port) throws Exception {

        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(port));
        server.configureBlocking(false);

        Reactor reactor = new Reactor();
        reactor.registerChannel(SelectionKey.OP_ACCEPT, server);

        reactor.registerEventHandler(
                SelectionKey.OP_ACCEPT, new AcceptEventHandler(
                reactor.getDemultiplexer()));

        reactor.registerEventHandler(
                SelectionKey.OP_READ, new ReadEventHandler(
                reactor.getDemultiplexer()));

        reactor.registerEventHandler(
                SelectionKey.OP_WRITE, new WriteEventHandler());

        reactor.run(); // Run the dispatcher loop

    }

    public static void main(String[] args) {
        System.out.println("Server Started at port : " + SERVER_PORT);
        try {
            new ReactorManager().startReactor(SERVER_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
