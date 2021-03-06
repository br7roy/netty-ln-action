/*
* Package com.rust.netty1.client 
* FileName: ClientChannelInitializer
* Author:   Rust
* Date:     2018/6/26 22:20
* Description: 
* History: 
*===============================================================================================
*   author：          time：                             version：           desc：
*   Rust                 2018/6/26  22:20             1.0                  
*===============================================================================================
*/
package com.rust.netty.heartbeat.node.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * FileName:    ClientChannelInitializer
 * Author:      Rust
 * Date:        2018/6/26
 * Description: 客户端Channel通道初始化设置
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //字符串解码和编码
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        //客户端的逻辑
        pipeline.addLast("handler", new DemoClientHandler());
    }
}