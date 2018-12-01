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