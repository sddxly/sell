package com.imooc.enums;

import lombok.Getter;

/**
 * @author Liam Liu
 * @date 2018/6/1 17:09
 */
@Getter
public enum ProductStatusEnum {

    UP(0,"在架"),
    DOWN(1,"下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message=message;
    }
}
