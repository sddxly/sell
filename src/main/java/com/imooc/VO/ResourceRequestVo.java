package com.imooc.VO;

import lombok.Data;

/**
 * 请求资源
 * @author Kun Liu
 * @date:2018/6/21
 */
@Data
public class ResourceRequestVo {
   
    //纬度
    private Double latitude;
    //精度
    private Double longitude;
    //用户ID
    private String openId;
    
}
