/*
* Package com.rust.netty1 
* FileName: ServerChannelInitializer
* Author:   Rust
* Date:     2018/6/26 22:15
* Description: 
* History: 
*===============================================================================================
*   author：          time：                             version：           desc：
*   Rust                 2018/6/26  22:15             1.0                  
*===============================================================================================
*/
package com.rust.netty.heartbeat.node.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * FileName:    ServerChannelInitializer
 * Author:      Rust
 * Date:        2018/6/26
 * Description: 服务器Channel通道初始化设置
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //字符串解码和编码
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        //服务器的逻辑
        pipeline.addLast("handler", new DemoServerHandler());
    }
}
