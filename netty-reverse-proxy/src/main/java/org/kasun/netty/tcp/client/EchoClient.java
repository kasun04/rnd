package org.kasun.netty.tcp.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class EchoClient {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "6060"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    private ByteBuf byteBuf;


    public static void main(String[] args) throws Exception {

        new EchoClient().sendRequest();


    }

    public void sendRequest() throws Exception {
        final String initialData = "Initial data ";

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            System.out.println("Thread ID - Client : " + Thread.currentThread().getId());
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new EchoClientHandler(EchoClient.this));
                        }
                    });
            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();

        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }

    public void callbackCompleted() {
        /* Response is received..*/
        System.out.println(
                "Client callback completed : " + byteBuf.toString(CharsetUtil.UTF_8));

        /*Continue with the rest of the business logic */

    }

    public ByteBuf getByteBuf() {
        return byteBuf;
    }

    public void setByteBuf(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }
}
