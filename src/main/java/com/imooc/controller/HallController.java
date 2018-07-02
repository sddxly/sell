package com.imooc.controller;

import com.imooc.VO.HallResponseVo;
import com.imooc.VO.NearestHallVo;
import com.imooc.VO.ResultVo;
import com.imooc.converter.HallInsertForm2HallDTOConvert;
import com.imooc.dataobject.HallInfo;
import com.imooc.dto.HallDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.HallInsertForm;
import com.imooc.service.HallService;
import com.imooc.utils.MathUtil;
import com.imooc.utils.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Kun Liu
 * @date:2018/6/22
 */
@RestController
@RequestMapping("/seller/hall")
@Slf4j
public class HallController {
   
    @Autowired 
    private HallService hallService;

    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@RequestBody HallInsertForm hallInsertForm,
                                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建营业厅】参数不正确，hallInsertForm={}",hallInsertForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        HallDTO hallDTO = HallInsertForm2HallDTOConvert.convert(hallInsertForm);

        HallInfo createResult = hallService.create(hallDTO);

        Map<String,Integer> map = new HashMap<>();
        map.put("hallId",createResult.getHallId());

        return ResultVoUtil.success(map);
        
    }
    
    @GetMapping("/getallhalls")
    public ResultVo<List<HallResponseVo>> getAllHalls(){
        ResultVo<List<HallResponseVo>> resultVo = new ResultVo<>();
        List<HallResponseVo> hallResponseVoList = new ArrayList<>();
        try{
            List<HallInfo> hallInfoList = hallService.findAll();
            for(HallInfo  hallInfo : hallInfoList){
                String icons[] = hallInfo.getHallIcon().split(",");
                List<String> iconList = new ArrayList<>();
                if(icons.length > 0){
                    for(int i =0;i < icons.length;i++){
                        iconList.add(icons[i]);
                    }
                }
                HallResponseVo hallResponseVo = new HallResponseVo();
                hallResponseVo.setId(hallInfo.getHallId());
                hallResponseVo.setName(hallInfo.getHallName());
                hallResponseVo.setIcon(iconList);
                hallResponseVo.setLatitude(hallInfo.getHallLat());
                hallResponseVo.setLongitude(hallInfo.getHallLng());
                hallResponseVoList.add(hallResponseVo);
            }
            resultVo.setCode(0);
            resultVo.setMsg("获取营业厅成功");
            resultVo.setData(hallResponseVoList);
            return resultVo;
        }catch(Exception e){
            resultVo.setCode(1);
            resultVo.setMsg("获取营业厅失败");
            log.info(e.getMessage(), e);
            return resultVo;
        }
    }
    
    @GetMapping("/gethallbyrange")
    public ResultVo<List<HallResponseVo>> getHallByRange(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude) {
        ResultVo<List<HallResponseVo>> resultVo = new ResultVo<>();
        List<HallResponseVo> hallResponseVoList = new ArrayList<>();
        Double latLngRange = 0.000012 * 5000;
        Double latmin = latitude - latLngRange;
        Double latmax = latitude + latLngRange;
        Double lngmin = longitude - latLngRange;
        Double lngmax = longitude + latLngRange;
        try{
            List<HallInfo> hallInfoList = hallService.findHallsByRange(latmin,latmax,lngmin,lngmax);
            for(HallInfo  hallInfo : hallInfoList){
                String icons[] = hallInfo.getHallIcon().split(",");
                List<String> iconList = new ArrayList<>();
                if(icons.length > 0){
                    for(int i =0;i < icons.length;i++){
                        iconList.add(icons[i]);
                    }
                }
                if(MathUtil.GetDistance(latitude,longitude,hallInfo.getHallLat(),hallInfo.getHallLng()) * 1000 < 5000){
                    HallResponseVo hallResponseVo = new HallResponseVo();
                    hallResponseVo.setId(hallInfo.getHallId());
                    hallResponseVo.setName(hallInfo.getHallName());
                    hallResponseVo.setIcon(iconList);
                    hallResponseVo.setLatitude(hallInfo.getHallLat());
                    hallResponseVo.setLongitude(hallInfo.getHallLng());
                    hallResponseVoList.add(hallResponseVo);
                }
            }
            resultVo.setCode(0);
            resultVo.setMsg("获取营业厅成功");
            resultVo.setData(hallResponseVoList);
            return resultVo;
        }catch(Exception e){
            resultVo.setCode(1);
            resultVo.setMsg("获取营业厅失败");
            log.info(e.getMessage(), e);
            return resultVo;
        }
    } 
    
    @GetMapping("/getnearesthall")
    public ResultVo<NearestHallVo> getNearestHall(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude){
        ResultVo<NearestHallVo> resultVo = new ResultVo<>();
        try{
            List<HallInfo> hallInfoList = hallService.findAll();
            Collections.sort(hallInfoList, new Comparator<HallInfo>() {
                @Override
                public int compare(HallInfo o1, HallInfo o2) {
                    if(MathUtil.GetDistance(latitude,longitude,o1.getHallLat(),o1.getHallLng()) - MathUtil.GetDistance(latitude,longitude,o2.getHallLat(),o2.getHallLng()) > 0)
                        return 1;
                    else if(MathUtil.GetDistance(latitude,longitude,o1.getHallLat(),o1.getHallLng()) - MathUtil.GetDistance(latitude,longitude,o2.getHallLat(),o2.getHallLng()) < 0)
                        return -1;
                    else 
                        return 0;
                }
            });
            
            NearestHallVo nearestHallVo = new NearestHallVo();
            nearestHallVo.setId(hallInfoList.get(0).getHallId());
            nearestHallVo.setName(hallInfoList.get(0).getHallName());
            nearestHallVo.setLatitude(hallInfoList.get(0).getHallLat());
            nearestHallVo.setLongitude(hallInfoList.get(0).getHallLng());
            nearestHallVo.setDistance((int) (MathUtil.GetDistance(latitude,longitude,hallInfoList.get(0).getHallLat(),hallInfoList.get(0).getHallLng()) * 1000));
           
            resultVo.setCode(0);
            resultVo.setMsg("获取营业厅成功");
            resultVo.setData(nearestHallVo);
            return resultVo;
        }catch(Exception e){
            resultVo.setCode(1);
            resultVo.setMsg("获取营业厅失败");
            log.info(e.getMessage(), e);
            return resultVo;
        }
    }
}
