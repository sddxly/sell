package com.imooc.service;

import com.imooc.dataobject.HallInfo;
import com.imooc.dto.HallDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Kun Liu
 * @date:2018/6/22
 */
public interface HallService {
    
    HallInfo create(HallDTO hallDTO);
    
    List<HallInfo> findHallsByRange(Double latmin,Double latmax,Double lngmin,Double lngmax); 
    
    List<HallInfo> findAll();
    
}
