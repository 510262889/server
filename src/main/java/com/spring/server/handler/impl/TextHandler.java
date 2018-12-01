/*
 *  Copyright (c) 2017. 福建亿立方网络科技有限公司，版权所有。
 *  本项目所有的源代码、配置以及业务逻辑设计均属福建亿立方网络科技有限公司所有。
 *  未经许可对本项目的所有内容的不论任何形式的使用、扩散和传播都将视为侵权。
 */

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
