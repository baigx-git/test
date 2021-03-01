package com.luxondata.test.vo;

import com.luxondata.test.dto.UserWebsocketSalt;
import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author bgx
 * @date 2021/3/1 16:13
 */

@Data
public class WsDataContent implements Serializable {

    private static final long serialVersionUID = -6071363786339871606L;

    /**
     * 消息类型
     */
    private Integer action;
    /**
     * msgId
     */
    private String msgId;
    /**
     * 发起连接需要的参数
     */
    private UserWebsocketSalt salt;
    /**
     * data
     */
    private Object data;

}
