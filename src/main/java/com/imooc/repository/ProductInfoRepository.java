package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Liam Liu
 * @date 2018/6/1 16:39
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
 List<ProductInfo> findByProductStatus(Integer productStatus);
}
