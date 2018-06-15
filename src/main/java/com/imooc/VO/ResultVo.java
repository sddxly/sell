package com.imooc.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * @author Liam Liu
 * @date 2018/6/4 14:40
 */
@Data
public class ResultVo<T> {

    /**错误码*/
    private Integer code;
    /**提示信息*/
    private String msg;
    /**具体内容*/
    private T data;
}
