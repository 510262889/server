package com.spring.server.util;

import org.apache.commons.lang.StringUtils;

import java.io.File;

/**
 * 文件工具类
 * @author ykzhuo
 * @date 2018/11/30 16:38
 **/
public class FileUtil {

    /**
     * 判断文件是否存在
     */
    public static boolean exist(String path){
        if(StringUtils.isBlank(path)) throw new RuntimeException("文件路径不能为空!");
        File file = new File(path);
        return file.exists();
    }

}
