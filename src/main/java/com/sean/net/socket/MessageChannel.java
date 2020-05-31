package com.sean.net.socket;

import com.sean.net.ServerListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.springframework.stereotype.Component;

@Component
public class MessageChannel extends ChannelInitializer<SocketChannel> {

    private ServerListener listener;

    public void setListener(ServerListener listener){
        this.listener = listener;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();

        p.addLast(new PackDecoder());
        p.addLast(new DataParser(listener));
    }
}
