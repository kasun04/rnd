package org.kasun.netty.tcp.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private EchoClient clientCallback;

    public EchoClientHandler(EchoClient clientCallback) {
        this.clientCallback = clientCallback;
    }

    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Client - Channel Active");
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello Netty!",
                                        CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println("Thread ID Client Handler : " + Thread.currentThread().getId());

        System.out.println(
                "Client received: " + in.toString(CharsetUtil.UTF_8));

        clientCallback.setByteBuf(in);
        clientCallback.callbackCompleted();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }




}
