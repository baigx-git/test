package com.luxondata.test.common.enums;

/**
 * TODO
 *
 * @author bgx
 * @date 2021/3/1 17:00
 */

public enum MessageEnums {

    ClientMsgTp(1, "客户端已被下线");

    private  Integer id;
    private  String code;

    MessageEnums(Integer id, String code) {
        this.id = id;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
