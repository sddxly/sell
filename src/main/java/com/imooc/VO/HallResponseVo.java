package com.imooc.VO;

import lombok.Data;

import java.util.List;

/**
 * @author Kun Liu
 * @date:2018/6/23
 */
@Data
public class HallResponseVo {
    
    private Integer id;
    
    private String name;
    
    private Double latitude;
    
    private Double longitude;
    
    private List<String> icon;
}
