package com.imooc.converter;

import com.imooc.dto.ProductDTO;
import com.imooc.form.ProductInsertForm;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author Kun Liu
 * @date:2018/6/20
 */
@Slf4j
public class ProductInsertForm2ProductDTOConvert {
    public static ProductDTO convert(ProductInsertForm productInsertForm){
        ProductDTO productDTO = new ProductDTO();
        
        productDTO.setProductName(productInsertForm.getName());
        productDTO.setProductDescription(productInsertForm.getDescription());
        productDTO.setCategoryType(productInsertForm.getCategory());
        productDTO.setProductPrice(new BigDecimal(productInsertForm.getPrice()));
        productDTO.setProductStock(productInsertForm.getStock());
        productDTO.setProductIcon(productInsertForm.getPic());
        
        return productDTO;
    }
}
