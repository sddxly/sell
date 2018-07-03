package com.imooc.utils;

import com.imooc.enums.CodeEnum;

/**
 * @author Liam Liu
 * @date 2018/6/23 14:07
 */
public class EnumUtil {
    public  static < T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each:enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
