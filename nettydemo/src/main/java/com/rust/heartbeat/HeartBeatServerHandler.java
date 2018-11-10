/**
 * 壹钱包
 * Copyright (c) 2013-2018 壹钱包版权所有.
 */
package com.rust.heartbeat;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * FileName: HeartBeatServerHandler
 * Author:   Rust
 * Date:     2018/6/26
 * Description:http://www.importnew.com/17647.html
 * History:
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter  {
    //public static final Logger logger = LoggerFactory.getLogger(HeartBeatServerHandler.class);

    // Return a unreleasable view on the given ByteBuf
    // which will just ignore release and retain calls.
    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled
            .unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat",
                    CharsetUtil.UTF_8));  // 1

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        ByteBuf bf = (ByteBuf) msg;
        if (!bf.hasArray()) {
            int len = bf.readableBytes();
            byte[] bs = new byte[len];
            bf.getBytes(0, bs);
        }
        ReferenceCountUtil.release(msg);
        System.out.println("msg:" + bf);
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {

        if (evt instanceof IdleStateEvent) {  // 2
            IdleStateEvent event = (IdleStateEvent) evt;
            String type = "";
            if (event.state() == IdleState.READER_IDLE) {
                type = "read idle";
            } else if (event.state() == IdleState.WRITER_IDLE) {
                type = "write idle";
            } else if (event.state() == IdleState.ALL_IDLE) {
                type = "all idle";
            }

            ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(
                    ChannelFutureListener.CLOSE_ON_FAILURE);  // 3

            System.out.println( ctx.channel().remoteAddress()+"超时类型：" + type);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
