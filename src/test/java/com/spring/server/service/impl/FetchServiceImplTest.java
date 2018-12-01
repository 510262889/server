package com.spring.server.service.impl;

import com.spring.server.service.FetchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 采集服务测试
 *
 * @author ykzhuo
 * @since 2018-12-01 10:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FetchServiceImplTest {

    @Autowired
    private FetchService fetchService;

    @Test
    public void fetchDetector() {
        fetchService.fetchDetector();
    }
}