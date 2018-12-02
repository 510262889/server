package com.spring.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 验证码配置
 * @author ykzhuo
 * @date 2018/11/30 14:32
 **/
@ConfigurationProperties(prefix = "service.captcha")
@Component
@Getter
@Setter
public class CaptchaConfig {

    // 请求地址
    private String url;

    // 图片保存路径
    private String imageUrl;

    // 文字图片保存路径
    private String textUrl;

    // 降噪图片存放路径
    private String cleanUrl;

    // 采集数量
    private int fetchNum;
}
