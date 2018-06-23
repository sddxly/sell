package com.imooc.converter;

import com.imooc.dto.HallDTO;
import com.imooc.form.HallInsertForm;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Kun Liu
 * @date:2018/6/22
 */
@Slf4j
public class HallInsertForm2HallDTOConvert {
    
    public static HallDTO convert(HallInsertForm hallInsertForm){
        HallDTO hallDTO = new HallDTO();
        
        hallDTO.setHallName(hallInsertForm.getName());
        hallDTO.setHallType(hallInsertForm.getType());
        hallDTO.setHallGrid(hallInsertForm.getGrid());
        hallDTO.setStaffId(hallInsertForm.getManager());
        hallDTO.setHallLat(hallInsertForm.getLat());
        hallDTO.setHallLng(hallInsertForm.getLng());
        hallDTO.setRemark(hallInsertForm.getRemark());
        
        return hallDTO;
    }
}
