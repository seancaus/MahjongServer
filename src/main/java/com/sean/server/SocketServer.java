package com.sean.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

 @Component("SocketServer")
public class SocketServer implements IServer{

    @Autowired
    private MessageChannel msgChannel;

    public void start(int port){
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();                                      // 1.2创建启动类ServerBootstrap实例，用来设置客户端相关参数
            b.group(bossGroup, workerGroup)                                                 // 1.2.1设置主从线程池组
                    .channel(NioServerSocketChannel.class)                                  // 1.2.2指定用于创建客户端NIO通道的Class对象
                    .handler(new LoggingHandler(LogLevel.INFO))                             // 1.2.4设置日志handler
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childHandler(msgChannel);
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 1.5优雅关闭线程池组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
