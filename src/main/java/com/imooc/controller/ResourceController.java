package com.imooc.controller;

import com.imooc.VO.ResourceRequestVo;
import com.imooc.VO.ResourceResponseVo;
import com.imooc.VO.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kun Liu
 * @date:2018/6/21
 */
@RestController
@RequestMapping("/buyer/resource")
@Slf4j
public class ResourceController {

    @Autowired
    private RestTemplate restTemplate;
    
    @PostMapping("/get")
    public ResultVo<ResourceResponseVo> getResource(@RequestBody ResourceRequestVo resourceRequestVo) {
        ResultVo<ResourceResponseVo> resultVo = new ResultVo<>();
        try {
            
            String url = "http://127.0.0.1:10001/api/add/getresourcebypoint?latitude={latitude}&longitude={longitude}";
            Map<String, Object> uriVariables = new HashMap<>();
            uriVariables.put("latitude", resourceRequestVo.getLatitude());
            uriVariables.put("longitude", resourceRequestVo.getLongitude());
            ResultVo<ResourceResponseVo> response = new ResultVo<ResourceResponseVo>();
            response = restTemplate.getForObject(url, ResultVo.class, uriVariables);

            resultVo.setCode(0);
            resultVo.setMsg("获取资源成功");
            resultVo.setData(response.getData());
            return resultVo;
        } catch (Exception e) {
            resultVo.setCode(1);
            resultVo.setMsg("获取资源失败");
            log.info(e.getMessage(), e);
            return resultVo;
        }
    }
}
