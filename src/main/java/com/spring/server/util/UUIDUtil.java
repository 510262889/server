package com.spring.server.util;

import java.util.UUID;

/**
 * UUID生成工具
 *
 * @author ykzhuo
 * @since 2018-12-02 11:43
 */
public class UUIDUtil {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll( "-", "" );
    }
}
