package com.imooc.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品（包含类目)
 * @author Liam Liu
 * @date 2018/6/4 15:22
 */
@Data
public class ProductVo {
    @JsonProperty("name")
    private  String categoryName;
    @JsonProperty("type")
    private  Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;


}
