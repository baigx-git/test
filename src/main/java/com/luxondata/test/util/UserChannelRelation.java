package com.luxondata.test.util;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author bgx
 * @date 2021/3/1 16:25
 */

@Slf4j
public class UserChannelRelation {

    private static HashMap<String, Channel> manager = new HashMap<>();

    public static void put(String userId, Channel channel) {
        manager.put(userId, channel);
    }

    public static Channel get(String userId) {
        return manager.get(userId);
    }

    public static void remove(String userId) {
        manager.remove(userId);
    }

    public static void output() {
        for (HashMap.Entry<String, Channel> entry : manager.entrySet()) {
            log.info("UserId:{},ChannelId{}", entry.getKey(), entry.getValue().id().asLongText());
        }
    }

    /**
     * 移除Channel
     *
     * @param channel
     */
    public static void remove(Channel channel) {
        for (Map.Entry<String, Channel> entry : manager.entrySet()) {
            if (entry.getValue().equals(channel)) {
                manager.remove(entry.getKey());
            }
        }
    }
}
