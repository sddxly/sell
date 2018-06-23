package com.imooc.controller;

import com.imooc.VO.ProductInfoVo;
import com.imooc.VO.ProductVo;
import com.imooc.VO.ResultVo;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.ResultVoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * @author Liam Liu
 * @date 2018/6/4 14:28
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResultVo list(){

        //1.查询所有的上架商品  数据库查询

        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查询类目（一次性查询） 数据库查询 不要将数据库查询放到for循环中
        //List<Integer> categoryTypeList= new ArrayList<>();
        //传统
        //for(ProductInfo productInfo : productInfoList){
       //     categoryTypeList.add(productInfo.getCategoryType());
       // }
        //精简(lambda)

        List<Integer> categoryTypeList=productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVo> productVoList = new ArrayList<>();
        for(ProductCategory productCategory :productCategoryList){
            ProductVo productVo =new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());
            
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for(ProductInfo productInfo: productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        
        return ResultVoUtil.success(productVoList);
    }
}
