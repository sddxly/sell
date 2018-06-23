package com.imooc.controller;

import com.imooc.VO.BuildingResourceRequestVo;
import com.imooc.VO.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping("/getbuilding")
    public ResultVo getBuildingResource(@RequestBody BuildingResourceRequestVo resourceRequestVo) {
        ResultVo resultVo = new ResultVo<>();
        try {
            if(resourceRequestVo.getOpenId() == ""){
                resultVo.setCode(1);
                resultVo.setMsg("获取资源失败，请登录");
                return resultVo;
            }else{
                String url = "http://137.0.51.22:10001/api/add/getbuildingresourcebypoint?latitude={latitude}&longitude={longitude}";
                Map<String, Object> uriVariables = new HashMap<>();
                uriVariables.put("latitude", resourceRequestVo.getLatitude());
                uriVariables.put("longitude", resourceRequestVo.getLongitude());
                ResultVo response;
                response = restTemplate.getForObject(url, ResultVo.class, uriVariables);
                resultVo.setCode(0);
                resultVo.setMsg("获取资源成功");
                resultVo.setData(response.getData());
                return resultVo;
            }
        } catch (Exception e) {
            resultVo.setCode(1);
            resultVo.setMsg("获取资源失败");
            log.info(e.getMessage(), e);
            return resultVo;
        }
    }
    
    @GetMapping("/getcell")
    public ResultVo getCellResource(@RequestParam("id") Long id){
        ResultVo resultVo = new ResultVo<>();
        try{
            String url = "http://137.0.51.22:10001/api/add/getcellresourcebypoint?area_sa6={id}";
            Map<String, Object> uriVariables = new HashMap<>();
            uriVariables.put("id", id);
            ResultVo response;
            response = restTemplate.getForObject(url, ResultVo.class, uriVariables);
            resultVo.setCode(0);
            resultVo.setMsg("获取资源成功");
            resultVo.setData(response.getData());
            return resultVo;
        }catch(Exception e){
            resultVo.setCode(1);
            resultVo.setMsg("获取资源失败");
            log.info(e.getMessage(), e);
            return resultVo;
        }
    }
}
