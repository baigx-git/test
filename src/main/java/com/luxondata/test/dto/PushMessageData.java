package com.luxondata.test.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author bgx
 * @date 2021/3/1 16:51
 */

@Data
public class PushMessageData {

    private Integer msgType;

    private String msgVariety;

    private Object msgBody;

    private LocalDateTime time;

}
