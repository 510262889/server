package com.spring.server.fetch.impl;

import com.spring.server.fetch.FetchHandler;
import com.spring.server.util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 检测体采集
 *
 * @author ykzhuo
 * @date 2018/11/28 15:49
 **/
public class FetchDetector implements FetchHandler {

    String url = "https://kyfw.12306.cn/passport/captcha/captcha-image64?login_site=E&module=login&rand=sjrand&1543558363631&callback=jQuery19104340823452365097_1543558357966&_=1543558357968";

    @Override
    public void fetch() {
        String response = null;
        Map map = new HashMap<>();
        try {
            response = HttpClientUtil.net(url,map,"GET");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response);
    }
}
