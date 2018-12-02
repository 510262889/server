package com.spring.server.service.impl;

import com.spring.server.service.TheasuresService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 词库服务测试
 *
 * @author ykzhuo
 * @since 2018-12-02 10:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TheasuresServiceImplTest {

    @Autowired
    private TheasuresService theasuresService;

    @Test
    public void init() {
        theasuresService.init();
    }
}