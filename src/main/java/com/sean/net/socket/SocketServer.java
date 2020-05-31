package com.sean.net.socket;

import com.sean.net.IServer;
import com.sean.net.ServerListener;
import com.sean.net.socketio.SocketIo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("SocketServer")
public class SocketServer implements IServer {

    private static final Logger LOG = LoggerFactory.getLogger(SocketServer.class);

    @Autowired
    private MessageChannel msgChannel;

    @Override
    public void start(ServerListener listener, int port) {
        if (null == listener) {
            LOG.error("ServerListener is null");
            return;
        }

        msgChannel.setListener(listener);
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
            LOG.error(e.getMessage());
        } finally {
            // 1.5优雅关闭线程池组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
