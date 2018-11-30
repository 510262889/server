package com.spring.server.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ykzhuo
 * @date 2018/11/30 15:49
 **/
public class IOUtil {

    public static String ipsToString(InputStream input) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = input.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }
}
