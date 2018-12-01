package com.spring.server.service;

/**
 * 文字处理服务
 * @author ykzhuo
 * @since 2018-12-01 10:59
 */
public interface TextService {

    /**
     * 切割文字
     */
    void splitText();

    /**
     * 识别文字
     */
    void recognition();
}
