package com.example.demo.common.share;

import io.swagger.annotations.ApiModelProperty;

/**
 * 接口返回对象
 * @param <T> 返回数据泛型
 */
public class JsonMessage<T> {
    @ApiModelProperty(value="返回码(SUCCESS:正常 ,其它：异常)")
    private String code;          // 返回码(SUCCESS:正常 ,其它：异常)

    @ApiModelProperty(value="说明信息")
    private String message;    // 说明信息

    @ApiModelProperty(value="返回数据")
    private T data;            // 返回数据

    public JsonMessage(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public JsonMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonMessage(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public JsonMessage(String code) {
        this.code = code;
    }

    public JsonMessage() {
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
