package com.luxondata.test.component.netty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author bgx
 * @date 2021/3/1 17:21
 */


@Component
@Slf4j
public class NettyNIOServer implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${netty.server.port}")
    private Integer port;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        try {
            WebSocketServer.getInstance().start(port);
            log.info(" ----Netty WebSocket Server 启动完毕，监听端口: {} ----",port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
