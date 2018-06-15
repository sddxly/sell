package com.imooc.controller;

import com.imooc.dto.OrderDTO;
import com.imooc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;

/**
 * @author Liam Liu
 * @date 2018/6/14 17:21
 */
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

      @Autowired
      private OrderService orderService;
      @GetMapping("/list")
      public ModelAndView list(@RequestParam("page") Integer page,
                               @RequestParam("size") Integer size,
                               Map<String,Object> map){
          PageRequest request = PageRequest.of(page-1,size);
          Page<OrderDTO> orderDTOPage = orderService.findList(request);
          map.put("orderDTOPage",orderDTOPage);
          return new ModelAndView("order/list",map);
      }
}
