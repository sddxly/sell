package com.imooc.form;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Kun Liu
 * @date:2018/6/20
 */
@Data
public class ProductInsertForm {
    
    //商品名称
    @NotEmpty(message = "商品名称必填")
    private String name;

    //商品描述
    @NotEmpty(message = "商品描述必填")
    private String description;

    //商品分类
    @NotNull(message = "商品分类必填")
    private Integer category;

    //商品二级分类
    @NotEmpty(message = "商品二级分类必填")
    private String sec_category;

    //商品单价
    @NotNull(message = "商品单价必填")
    private Double price;

    //商品库存
    @NotNull(message = "商品库存必填")
    private Integer stock;

    //商品照片
    private String pic;

    //商品详情分类
    @Column(length=100000)
    @NotEmpty(message = "商品详情必填")
    private String detail;
}
