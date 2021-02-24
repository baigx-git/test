package com.luxondata.test.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "返回结果")
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "返回状态",required = true)
    private Boolean success;

    @ApiModelProperty(value = "code",required = true)
    private Integer code;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;
}
