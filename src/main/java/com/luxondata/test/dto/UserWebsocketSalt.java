package com.luxondata.test.dto;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author bgx
 * @date 2021/3/1 16:23
 */

@Data
public class UserWebsocketSalt implements Serializable {

    private static final long serialVersionUID = -6290552417964638549L;
    /**
     * userId
     */
    private String userId;

    /**
     * loginLabel 当前登录标签
     */
    private String loginLabel;
}
