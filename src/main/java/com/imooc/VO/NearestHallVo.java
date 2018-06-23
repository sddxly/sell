package com.imooc.VO;

import lombok.Data;

/**
 * @author Kun Liu
 * @date:2018/6/23
 */
@Data
public class NearestHallVo {

    private Integer id;

    private String name;

    private Double latitude;

    private Double longitude;
    
    private Integer distance;
}
