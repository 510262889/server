package com.spring.server.handler.impl;

import com.spring.server.config.CaptchaConfig;
import com.spring.server.handler.CaptchaHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 验证码文字处理
 *
 * @author ykzhuo
 * @since 2018-12-01 10:54
 */
@Component
public class TextHandler implements CaptchaHandler {

    @Autowired
    private CaptchaConfig config;

    @Override
    public void handler() {

    }
}
