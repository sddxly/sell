package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Liam Liu
 * @date 2018/6/5 10:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private  OrderMasterRepository repository;

    private final String OPENID ="110110";

    @Test
    public void  saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("11234557");
        orderMaster.setBuyerName("刘洋");
        orderMaster.setBuyerPhone("18953197049");
        orderMaster.setBuyerAddress("舜华路");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(18.99));

        OrderMaster result =repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(1,3);
        Page<OrderMaster> result= repository.findByBuyerOpenid(OPENID,request);
        Assert.assertNotEquals(0,result.getTotalElements());
       // System.out.println(result.getTotalElements());
    }
}