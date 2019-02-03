/*
* Package com.rust.netty1 
* FileName: DemoServerHandler
* Author:   Rust
* Date:     2018/6/26 22:16
* Description: 
* History: 
*===============================================================================================
*   author：          time：                             version：           desc：
*   Rust                 2018/6/26  22:16             1.0                  
*===============================================================================================
*/
package com.rust.netty1.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * FileName:    DemoServerHandler
 * Author:      Rust
 * Date:        2018/6/26
 * Description:     服务器业务逻辑
 */
public class DemoServerHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("Client say : " + o.toString());
        //返回客户端消息 - 我已经接收到了你的消息
        channelHandlerContext.writeAndFlush("Received your message : " + o.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RemoteAddress : " + ctx.channel().remoteAddress() + " active !");
        ctx.writeAndFlush("连接成功！");
        super.channelActive(ctx);
    }
}
