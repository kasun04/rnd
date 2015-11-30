package org.kasun.netty.http.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpResponseDecoder;

public class HTTPClientInitilizer extends ChannelInitializer<SocketChannel> {



    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();

        // Enable HTTPS if necessary.
        p.addLast(new HttpResponseDecoder());

        // Remove the following line if you don't want automatic content decompression.
        //p.addLast(new HttpContentDecompressor());

        // Uncomment the following line if you don't want to handle HttpContents.
        //p.addLast(new HttpObjectAggregator(1048576));

        p.addLast(new HTTPClientHandler());
    }
}
