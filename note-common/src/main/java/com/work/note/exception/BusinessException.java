package com.work.note.exception;


import com.work.note.response.ResultCode;
import lombok.Data;

/**
 *  @author: 李臣臣
 *  @Date: 2019/12/25 0025 13:48
 *  @Description: 自定义服务异常类
 */
@Data
public class BusinessException extends  RuntimeException {
    private int code;
    private String message;


    public BusinessException(int code, String message){
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message){
        this.code = ResultCode.HTTP_500;
        this.message = message;
    }
}
