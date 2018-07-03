package com.imooc.VO;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Liam Liu
 * @date 2018/7/3 16:02
 */
@Component
@Data
@ConfigurationProperties(prefix = "qiniu")
public class QiNiuProperties {
    private String accessKey;

    private String secretKey;

    private String bucket;

    private String cdnPrefix;
}
