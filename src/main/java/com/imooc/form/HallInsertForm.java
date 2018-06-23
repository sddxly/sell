package com.imooc.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Kun Liu
 * @date:2018/6/22
 */
@Data
public class HallInsertForm {
    
    //营业厅名字
    @NotEmpty(message = "营业厅名字必填")
    private String name;

    //营业厅照片
    @NotEmpty(message = "营业厅照片必填")
    private String icon;
    
    //营业厅类型
    @NotNull(message = "营业厅类型必填")
    private Integer type;
    
    //营业厅所属网格
    @NotEmpty(message = "营业厅所属网格必填")
    private String grid;
    
    //店长姓名
    @NotNull(message = "店长姓名必填")
    private Long manager;
    
    //营业厅纬度
    @NotNull(message = "营业厅纬度必填")
    private Double lat;

    //营业厅经度
    @NotNull(message = "营业厅经度必填")
    private Double lng;
    
    //备注
    private String remark;
}
