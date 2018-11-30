package com.spring.server.util;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * base64工具类
 * @author ykzhuo
 * @date 2018/11/30 16:22
 **/
public class Base64Util {
    /**
     * @param imgStr base64编码字符串
     * @param path   图片路径-具体到文件
     */
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null) return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            if (!FileUtil.exist(path)) new File(path).createNewFile();
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
