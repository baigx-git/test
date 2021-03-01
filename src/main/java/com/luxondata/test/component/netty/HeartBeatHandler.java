package com.luxondata.test.component.netty;

import com.luxondata.test.util.UserChannelRelation;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author bgx
 * @date 2021/3/1 16:28
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 判断evt是否是IdleStateEvent（用于触发用户事件，包含 读空闲/写空闲/读写空闲 ）
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                log.info("进入读空闲...");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                log.info("进入写空闲...");
            } else if (event.state() == IdleState.ALL_IDLE) {
                log.info("关闭无用的Channel，以防资源浪费。Channel Id：{}", ctx.channel().id());
                Channel channel = ctx.channel();
                channel.close();
                UserChannelRelation.remove(channel);
                log.info("Channel关闭后，client的数量为:{}", NoMaybeHandler.clients.size());
            }
        }
    }



}
