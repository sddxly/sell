package com.imooc.VO;

import lombok.Data;

/**
 * 返回资源
 * @author Kun Liu
 * @date:2018/6/21
 */
@Data
public class ResourceResponseVo {
    
    //六级地址ID
    private Long id;
    //地址名称
    private LableVo lable;
    //地址纬度
    private Double latitude;
    //地址经度
    private Double longitude;
}
