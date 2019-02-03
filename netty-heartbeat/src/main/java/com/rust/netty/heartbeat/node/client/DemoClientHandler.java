/*
* Package com.rust.netty1.client 
* FileName: DemoClientHandler
* Author:   Rust
* Date:     2018/6/26 22:22
* Description: 
* History: 
*===============================================================================================
*   author：          time：                             version：           desc：
*   Rust                 2018/6/26  22:22             1.0                  
*===============================================================================================
*/
package com.rust.netty.heartbeat.node.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * FileName:    DemoClientHandler
 * Author:      Rust
 * Date:        2018/6/26
 * Description:
 */
public class DemoClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("Server say : " + o.toString());
    }
}
