/*
* Package com.rust.netty1.server 
* FileName: DemoServer
* Author:   Rust
* Date:     2018/6/26 22:19
* Description: 
* History: 
*===============================================================================================
*   author：          time：                             version：           desc：
*   Rust                 2018/6/26  22:19             1.0                  
*===============================================================================================
*/
package com.rust.netty.heartbeat.node.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * FileName:    DemoServer
 * Author:      Rust
 * Date:        2018/6/26
 * Description: 服务器启动逻辑
 */
public class DemoServer {
    public static void main(String[] args) throws Exception {
        int port = 8000;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                //采用默认值
            }
        }
        new DemoServer().bind(port);
    }

    public void bind(int port) throws Exception {
        //配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ServerChannelInitializer());

            //绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();

            //等待服务器监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
