package com.spring.server.service.impl;

import com.spring.server.ServerApplication;
import com.spring.server.config.CaptchaConfig;
import com.spring.server.handler.impl.TextHandler;
import com.spring.server.service.TextService;
import com.spring.server.util.FileUtil;
import com.spring.server.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

/**
 * 文字处理服务
 *
 * @author ykzhuo
 * @since 2018-12-01 11:02
 */
@Service
public class TextServiceImpl implements TextService {

    @Autowired
    private TextHandler textHandler;

    @Autowired
    private CaptchaConfig config;

    /**
     * 文字切割
     */
    @Override
    public void splitText() {
        try {
            cutImg();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * 文字识别
     */
    @Override
    public void recognition() {

    }

    private void cutImg() throws IOException {
        URL url = ServerApplication.class.getClassLoader().getResource( "" );
        String cutPath = url.getPath() + config.getImageUrl();

        File[] files = new File( cutPath ).listFiles();
        for ( File file : files ) {
            cut( file, url.getPath(), 118, 3, 53, 25 ); //裁剪图片
            // 降噪
            ImageUtil.cleanImage( new File( url.getPath() + config.getTextUrl() + file.getName() ), url.getPath() + config.getCleanUrl() + file.getName() );
            //cut( file ,url.getPath(),2 ,44, 5, 8, 12); //裁剪图片
            //cut( file ,url.getPath(),3 ,54, 5, 8, 12); //裁剪图片
            //cut( file ,url.getPath(),4 ,64, 5, 8, 12); //裁剪图片
        }
    }

    /**
     * 裁剪文件
     *
     * @param file 裁剪文件
     * @param path 资源路径
     * @param x    裁剪起始X
     * @param y    裁剪起始Y
     * @param w    裁剪宽度
     * @param h    裁剪高度
     */
    private void cut( File file, String path, int x, int y, int w, int h ) throws IOException {
        InputStream in = new FileInputStream( file );
        BufferedImage image = ImageIO.read( in );
        image = image.getSubimage( x, y, w, h ); //裁剪图片
        String textSaveUrl = path + config.getTextUrl();
        if ( !FileUtil.exist( textSaveUrl ) ) new File( path + config.getTextUrl() ).mkdirs();
        ImageIO.write( image, "png", new File( path + config.getTextUrl() + file.getName() ) );
        System.out.println( "图片裁剪成功,文件路径为：" + path + config.getTextUrl() + file.getName() );
    }
}
