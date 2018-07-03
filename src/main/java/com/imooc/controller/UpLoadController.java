package com.imooc.controller;

import com.google.gson.Gson;
import com.imooc.VO.QiNiuProperties;
import com.imooc.utils.KeyUtil;
import com.imooc.VO.ResultVo;
import com.imooc.utils.ResultVoUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;


/**
 * @author Liam Liu
 * @date 2018/7/3 16:11
 */
@RestController
@RequestMapping("/seller")
@Slf4j
public class UpLoadController {

    @Autowired
    private QiNiuProperties qiNiuProperties;
    /**
     * 上传文件到七牛云存储
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/qiniu/upload")
    public ResultVo uploadImg(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
        String path =uploadQNImg(inputStream, KeyUtil.genUniqueKey());
        return ResultVoUtil.success(path);
    }

    private String uploadQNImg(FileInputStream file, String key){
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);

        try {
            Auth auth = Auth.create(qiNiuProperties.getAccessKey(),qiNiuProperties.getSecretKey());
            String upToken=auth.uploadToken(qiNiuProperties.getBucket());
            try{
                Response response = uploadManager.put(file,key,upToken,null,null);
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
                String returnPath = qiNiuProperties.getCdnPrefix()+"/"+putRet.key;
                return returnPath;
            }catch (QiniuException ex){
                Response r =ex.response;
                log.error(r.toString());
                try {
                    log.error(r.bodyString());
                }catch (QiniuException ex2){
                    //ignore
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}

