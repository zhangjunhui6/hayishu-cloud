package org.example.feign.util;

import org.example.feign.result.Result;
import org.example.feign.result.ResultCode;

/**
 * ResultUtil
 *
 * @author Zjh
 * @date 2020/5/24 23:23
 **/
public class ResultUtil {
    public static Result buildResult(int resultCode, String message, Object data){
        return new Result(resultCode,message,data);
    }

    public static Result buildResult(ResultCode resultCode, String message, Object data){
        return buildResult(resultCode.getCode(), message, data);
    }

    public static Result buildSuccessResult(String message,Object data){
        return buildResult(ResultCode.SUCCESS,message,data);
    }

    public static Result buildFailResult(String message,Object data){
        return buildResult(ResultCode.FAIL,message,data);
    }
}
