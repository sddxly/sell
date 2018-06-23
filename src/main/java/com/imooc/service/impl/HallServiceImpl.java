package com.imooc.service.impl;

import com.imooc.dataobject.HallInfo;
import com.imooc.dto.HallDTO;
import com.imooc.repository.HallInfoRepository;
import com.imooc.service.HallService;
import com.imooc.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kun Liu
 * @date:2018/6/22
 */
@Service
public class HallServiceImpl implements HallService {
    
    @Autowired
    private HallInfoRepository hallInfoRepository;
    
    @Override
    public HallInfo create(HallDTO hallDTO){
        HallInfo hallInfo = new HallInfo();
        
        BeanUtils.copyProperties(hallDTO,hallInfo);
        hallInfoRepository.save(hallInfo);
        return hallInfo;
    }
    
    @Override
    public List<HallInfo> findHallsByRange(Double latmin,Double latmax,Double lngmin,Double lngmax){
       return hallInfoRepository.findHallsByRange(latmin,latmax,lngmin,lngmax);
    }
    
    @Override
    public List<HallInfo> findAll(){ return hallInfoRepository.findAll();}
}
