package com.spring.server.service.impl;

import com.spring.server.service.CaptchaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CaptchaServiceImpTest {

    @Autowired
    private CaptchaService captchaService;

    @Test
    public void create() {
        captchaService.create();
    }
}