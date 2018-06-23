package com.imooc.repository;

import com.imooc.dataobject.HallInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

/**
 * @author Kun Liu
 * @date:2018/6/22
 */
public interface HallInfoRepository extends JpaRepository<HallInfo,String> {
    
    @Query("select h from HallInfo h where h.hallLat>:latmin and h.hallLat<:latmax and h.hallLng>:lngmin and h.hallLng<:lngmax")
    List<HallInfo> findHallsByRange(@Param("latmin") Double latmin, @Param("latmax") Double latmax, @Param("lngmin") Double lngmin, @Param("lngmax") Double lngmax);
}
