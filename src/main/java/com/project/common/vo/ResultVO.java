package com.project.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {
    /*默认成功码*/
    private static final int SUCCESS_CODE = 200;
    /*默认成功消息*/
    private static final String SUCCESS_MSG = "success";
    /*默认失败码*/
    private static final int ERROR_CODE  = 300;
    /*默认失败消息*/
    private static final String ERROR_MSG = "系统错误";
    /*没有访问权限*/
    private static final int NO_AUTH_CODE  = 403;

    private Integer code;
    private String msg;
    private T data;//T任意泛型
    public static <T> ResultVO<T>success(T data){
        return new ResultVO<>(SUCCESS_CODE,SUCCESS_MSG,data);
    }

    public static <T>ResultVO<T>error(){
        return ResultVO.error("");
    }

    public static <T> ResultVO<T> error(String msg){
        msg =   StringUtils.hasLength(msg)?msg: ERROR_MSG;
        return new ResultVO<>(ERROR_CODE,msg,null);
    }
    /*没有访问权限*/
    public static <T>ResultVO<T>noAuth(String msg){
        return new ResultVO<>(NO_AUTH_CODE,msg,null);
    }
}
