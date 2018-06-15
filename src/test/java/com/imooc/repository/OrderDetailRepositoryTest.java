package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Liam Liu
 * @date 2018/6/5 11:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123576657887");
        orderDetail.setOrderId("11234557");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("123456");
        orderDetail.setProductName("宽带");
        orderDetail.setProductPrice(new BigDecimal(1.2));
        orderDetail.setProductQuantity(3);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);

    }
    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList= repository.findByOrderId("11234557");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}