package com.imooc.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author Kun Liu
 * @date:2018/6/22
 */
@Data
public class HallDTO {
    
    private Integer hallId;
    private String hallName;
    private String icon;
    private int hallType;
    private String hallGrid;
    private Double hallLat;
    private Double hallLng;
    private Long staffId;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
    
    private String remark;
}
