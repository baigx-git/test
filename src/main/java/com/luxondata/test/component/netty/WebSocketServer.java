package com.luxondata.test.component.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author bgx
 * @date 2021/3/1 17:18
 */

@Component
@Slf4j
public class WebSocketServer {

    /**
     * 主线程组 负责接收请求
     */
    private EventLoopGroup mainGroup;
    /**
     * 从线程组  负责处理请求   这里的主从线程组就是典型的多路复用思想
     */
    private EventLoopGroup subGroup;
    /**
     * 启动器
     */
    private ServerBootstrap server;
    /**
     * 某个操作完成时（无论是否成功）future将得到通知。
     */
    private ChannelFuture future;

    /**
     * 单例WbSocketServer
     */
    private static class SingletonWsServer {
        static final WebSocketServer instance = new WebSocketServer();
    }

    public static WebSocketServer getInstance() {
        return SingletonWsServer.instance;
    }


    public WebSocketServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WbSocketServerInitialize());//自定义的初始化类，注册管道内的处理器
    }

    public void start(int port) {
        this.future = server.bind(port);
    }
}
