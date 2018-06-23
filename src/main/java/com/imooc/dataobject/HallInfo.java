package com.imooc.dataobject;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Kun Liu
 * @date:2018/6/22
 */
@Entity
@Data
public class HallInfo {
    @Id
    private Integer hallId;
    /** 营业厅名称 */
    private String hallName;
    /** 营业厅名称 */
    private String hallIcon;
    /** 营业厅类型 */
    private Integer hallType;
    /** 所属网格 */
    private String hallGrid;
    /** 纬度 */
    private Double hallLat;
    /** 经度 */
    private Double hallLng;
    /** 店长ID */
    private Long staffId;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 备注 */
    private String remark;
}
