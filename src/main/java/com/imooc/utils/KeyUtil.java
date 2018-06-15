package com.imooc.utils;

import java.util.Random;

/**
 * @author Liam Liu
 * @date 2018/6/5 17:16
 */
public class KeyUtil {

    public static synchronized String genUniqueKey(){
        Random random = new Random();

        Integer number=random.nextInt(900000)+100000;

        return   System.currentTimeMillis()+String.valueOf(number);
    }
}
