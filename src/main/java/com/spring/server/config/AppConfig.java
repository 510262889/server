package com.spring.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ykzhuo
 * @date 2018/11/30 14:32
 **/
@ConfigurationProperties("service.detector")
@Getter
@Setter
public class AppConfig {

    // 请求地址
    private String url;
}
