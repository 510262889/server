/*
 *  Copyright (c) 2017. 福建亿立方网络科技有限公司，版权所有。
 *  本项目所有的源代码、配置以及业务逻辑设计均属福建亿立方网络科技有限公司所有。
 *  未经许可对本项目的所有内容的不论任何形式的使用、扩散和传播都将视为侵权。
 */

package com.spring.server.service.impl;

import com.spring.server.handler.impl.TextHandler;
import com.spring.server.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文字处理服务
 *
 * @author ykzhuo
 * @since 2018-12-01 11:02
 */
@Service
public class TextServiceImpl implements TextService {

    @Autowired
    private TextHandler textHandler;

    /**
     * 文字切割
     */
    @Override
    public void splitText() {

    }

    /**
     * 文字识别
     */
    @Override
    public void recognition() {

    }
}
