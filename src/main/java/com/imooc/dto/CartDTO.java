package com.imooc.dto;

import lombok.Data;

/**
 * @author Liam Liu
 * @date 2018/6/5 17:30
 */
@Data
public class CartDTO {
    private  String productId;

    private Integer productQuantity;


    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
