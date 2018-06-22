package com.imooc.controller;

import com.imooc.VO.ResultVo;
import com.imooc.converter.ProductInsertForm2ProductDTOConvert;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.ProductDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.ProductInsertForm;
import com.imooc.service.ProductService;
import com.imooc.utils.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kun Liu
 * @date:2018/6/20
 */
@RestController
@RequestMapping("/seller/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@RequestBody ProductInsertForm productInsertForm,
                                               BindingResult bindingResult){
        System.out.println("收到请求");
        if(bindingResult.hasErrors()){
            log.error("【创建商品】参数不正确，productInsertForm={}",productInsertForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        ProductDTO productDTO = ProductInsertForm2ProductDTOConvert.convert(productInsertForm);
        
        ProductInfo createResult = productService.create(productDTO);
        
        Map<String,String> map = new HashMap<>();
        map.put("productId",createResult.getProductId());

        return ResultVoUtil.success(map);
    }
}
