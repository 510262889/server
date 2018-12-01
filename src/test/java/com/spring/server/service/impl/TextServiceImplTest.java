/*
 *  Copyright (c) 2017. 福建亿立方网络科技有限公司，版权所有。
 *  本项目所有的源代码、配置以及业务逻辑设计均属福建亿立方网络科技有限公司所有。
 *  未经许可对本项目的所有内容的不论任何形式的使用、扩散和传播都将视为侵权。
 */

package com.spring.server.service.impl;

import com.spring.server.service.TextService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * 文字服务测试
 *
 * @author ykzhuo
 * @since 2018-12-01 11:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TextServiceImplTest {

    @Autowired
    private TextService textService;

    @Test
    public void splitText() {
        textService.splitText();
    }

    @Test
    public void recognition() {
        textService.recognition();
    }
}