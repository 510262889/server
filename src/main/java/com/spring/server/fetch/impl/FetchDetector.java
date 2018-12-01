package com.spring.server.fetch.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.spring.server.ServerApplication;
import com.spring.server.config.CaptchaConfig;
import com.spring.server.fetch.FetchHandler;
import com.spring.server.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 检测体采集
 *
 * @author ykzhuo
 * @date 2018/11/28 15:49
 **/
@Component
public class FetchDetector implements FetchHandler {

    @Autowired
    private CaptchaConfig config;

    @Override
    public void fetch() {
        // 循环采集
        for ( int i = 0; i < config.getFetchNum(); i++ ) {

            // 1、请求验证码
            String base64Code = requestCaptcha();

            // 2、保存验证码为图片
            saveCaptcha( base64Code );
        }
    }

    /**
     * 请求验证码
     *
     * @return 图片base64码
     */
    private String requestCaptcha() {
        // 请求图片
        String response = null;
        try {
            Map map = new HashMap<>();
            response = HttpClientUtil.net( config.getUrl(), map, "GET" );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        // 解析返回base64码
        int start = response.indexOf( "{" );
        int end = response.indexOf( "}" );
        String createImagesStr = response.substring( start, end + 1 );
        Map<String, String> responseMap = (Map<String, String>) JSONUtils.parse( createImagesStr );
        return responseMap.get( "image" );
    }

    /**
     * 保存验证码
     */
    private void saveCaptcha(String base64Code){
        URL url = ServerApplication.class.getClassLoader().getResource( "" );
        String saveImage = url.getPath() + config.getImageUrl();
        if ( !FileUtil.exist( saveImage ) ) new File( saveImage ).mkdirs();
        // 转换base64码为图片
        Base64Util.generateImage( base64Code, saveImage + System.currentTimeMillis() + ".jpg" );
        System.out.println( "图片转换成功，文件路径：" + saveImage );
    }


}
