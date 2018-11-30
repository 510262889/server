package com.spring.server.fetch.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.spring.server.ServerApplication;
import com.spring.server.fetch.FetchHandler;
import com.spring.server.util.Base64Util;
import com.spring.server.util.HttpClientUtil;

import java.net.URL;
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
        for (int i = 0; i < 10; i++) {
            String response = null;
            try {
                Map map = new HashMap<>();
                response = HttpClientUtil.net(url, map, "GET");
            } catch (Exception e) {
                e.printStackTrace();
            }
            int start = response.indexOf("{");
            int end = response.indexOf("}");
            String createImagesStr = response.substring(start, end + 1);
            Map<String, String> responseMap = (Map<String, String>) JSONUtils.parse(createImagesStr);
            URL url = ServerApplication.class.getClassLoader().getResource("");
            Base64Util.generateImage(responseMap.get("image"), url.getPath() + "fetch/" + System.currentTimeMillis() + ".jpg");
        }
    }
}
