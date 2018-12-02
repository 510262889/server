package com.spring.server.util;

import javax.imageio.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;

/**
 * 图片工具类
 *
 * @author ykzhuo
 * @since 2018-12-01 13:43
 */
public class ImageUtil {

    /**
     * 图片缩放
     *
     * @param src  文件路径
     * @param dest 缩放之后文件存放路径
     * @param w    缩放之后图片宽度
     * @param h    缩放之后图片高度
     */
    public static void zoomImage( String src, String dest, int w, int h ) throws Exception {

        double wr = 0, hr = 0;
        File srcFile = new File( src );
        File destFile = new File( dest );

        BufferedImage bufImg = ImageIO.read( srcFile ); //读取图片
        Image Itemp = bufImg.getScaledInstance( w, h, bufImg.SCALE_SMOOTH );//设置缩放目标图片模板

        wr = w * 1.0 / bufImg.getWidth();     //获取缩放比例
        hr = h * 1.0 / bufImg.getHeight();

        AffineTransformOp ato = new AffineTransformOp( AffineTransform.getScaleInstance( wr, hr ), null );
        Itemp = ato.filter( bufImg, null );
        try {
            ImageIO.write( (BufferedImage) Itemp, dest.substring( dest.lastIndexOf( "." ) + 1 ), destFile ); //写入缩减后的图片
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    /**
     * 压缩图片
     *
     * @param qality 参数qality是取值0~1范围内  代表压缩的程度
     */
    public static File compressPictureByQality( File file, float qality ) throws IOException {
        BufferedImage src = null;
        FileOutputStream out = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;
        // 指定写图片的方式为 jpg
        imgWrier = ImageIO.getImageWritersByFormatName( "jpg" ).next();
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(
                null );
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode( imgWriteParams.MODE_EXPLICIT );
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        imgWriteParams.setCompressionQuality( qality );
        imgWriteParams.setProgressiveMode( imgWriteParams.MODE_DISABLED );
        ColorModel colorModel = ImageIO.read( file ).getColorModel();// ColorModel.getRGBdefault();
        imgWriteParams.setDestinationType( new javax.imageio.ImageTypeSpecifier(
                colorModel, colorModel.createCompatibleSampleModel( 32, 32 ) ) );
        if ( !file.exists() ) {
            System.err.println( "文件不存在" );
            throw new FileNotFoundException( "Not Found Img File,文件不存在" );
        } else {
            src = ImageIO.read( file );
            out = new FileOutputStream( file );
            imgWrier.reset();
            // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
            // OutputStream构造
            imgWrier.setOutput( ImageIO.createImageOutputStream( out ) );
            // 调用write方法，就可以向输入流写图片
            imgWrier.write( null, new IIOImage( src, null, null ), imgWriteParams );
            out.flush();
            out.close();
            return file;
        }
    }

    /**
     * 功能描述: <br>
     * 〈调整图像到固定大小〉
     *
     * @param srcImageFile  源图像文件地址
     * @param descImageFile 缩放后的图像地址
     * @param width         缩放后的宽度
     * @param height        缩放后的高度
     * @param isPadding     是否补白
     */
    public static void changeSize( String srcImageFile, String descImageFile, int width, int height, boolean isPadding ) {
        try {
            // 缩放比例
            double ratio = 0.0;
            File file = new File( srcImageFile );
            BufferedImage bufferedImage = ImageIO.read( file );
            Image image = bufferedImage.getScaledInstance( width, height, bufferedImage.SCALE_SMOOTH );
            // 计算缩放比例
            if ( bufferedImage.getHeight() > bufferedImage.getWidth() ) {
                ratio = (new Integer( height )).doubleValue() / bufferedImage.getHeight();
            } else {
                ratio = (new Integer( width )).doubleValue() / bufferedImage.getWidth();
            }
            AffineTransformOp op = new AffineTransformOp( AffineTransform.getScaleInstance( ratio, ratio ), null );
            image = op.filter( bufferedImage, null );

            // 是否需要补白
            if ( isPadding ) {
                BufferedImage tempBufferedImage = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
                Graphics2D graphics2d = tempBufferedImage.createGraphics();
                graphics2d.setColor( Color.white );
                graphics2d.fillRect( 0, 0, width, height );
                if ( width == image.getWidth( null ) ) {
                    graphics2d.drawImage( image, 0, (height - image.getHeight( null )) / 2, image.getWidth( null ), image.getHeight( null ), Color.white, null );
                } else {
                    graphics2d.drawImage( image, (width - image.getWidth( null )) / 2, 0, image.getWidth( null ), image.getHeight( null ), Color.white, null );
                }
                graphics2d.dispose();
                image = tempBufferedImage;
            }
            ImageIO.write( (BufferedImage) image, "png", new File( descImageFile ) );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * 图片降噪
     *
     * @param sfile       需要去噪的图像
     * @param cleanImgDir 去噪后的图像保存地址
     */
    public static void cleanImage( File sfile, String cleanImgDir ) throws IOException {
        File destF = new File( cleanImgDir );
        if ( !destF.exists() ) {
            destF.mkdirs();
        }
        BufferedImage bufferedImage = ImageIO.read( sfile );

        int h = bufferedImage.getHeight();
        int w = bufferedImage.getWidth();

        int[][] gray = new int[w][h];
        int commonColor = 197;
        // 消除多余背景色
        for ( int x = 0; x < w; x++ ) {
            for ( int y = 0; y < h; y++ ) {
                int argb = bufferedImage.getRGB( x, y );
                Color color = new Color( argb );
                if ( color.getRed() >= commonColor && color.getGreen() >= commonColor && color.getBlue() >= commonColor )
                    gray[x][y] = getWhite();
                else if ( argb >= 11382189 )
                    gray[x][y] = getWhite();
                else {
                    //gray[x][y] = argb;
                    gray[x][y] = Color.black.getRGB();
                }
            }
        }

        BufferedImage binaryBufferedImage = new BufferedImage( w, h, BufferedImage.TYPE_4BYTE_ABGR );

        for ( int x = 0; x < w; x++ ) {
            for ( int y = 0; y < h; y++ ) {
                binaryBufferedImage.setRGB( x, y, gray[x][y] );
            }
        }
        File cleanFile = new File( cleanImgDir );
        if ( !FileUtil.exist( cleanFile.getParent() ) ) new File( cleanFile.getParent() ).mkdirs();
        ImageIO.write( binaryBufferedImage, "png", new File( cleanImgDir ) );
        System.out.println( "图片降噪成功,文件路径为:" + cleanImgDir );
    }

    /** 获取白色十进制色值 */
    private static int getWhite() { return Color.white.getRGB(); }


    /**
     * 获取灰度值
     */
    private static int getImageRgb( int i ) {
        String argb = Integer.toHexString( i );// 将十进制的颜色值转为十六进制
        // argb分别代表透明,红,绿,蓝 分别占16进制2位
        int r = Integer.parseInt( argb.substring( 2, 4 ), 16 );//后面参数为使用进制
        int g = Integer.parseInt( argb.substring( 4, 6 ), 16 );
        int b = Integer.parseInt( argb.substring( 6, 8 ), 16 );
        int result = (int) ((r + g + b) / 3);
        return result;
    }


    /**
     * 获取图片灰度
     */
    private static int getGray( int gray[][], int x, int y, int w, int h ) {
        int rs = gray[x][y]
                + (x == 0 ? 255 : gray[x - 1][y])
                + (x == 0 || y == 0 ? 255 : gray[x - 1][y - 1])
                + (x == 0 || y == h - 1 ? 255 : gray[x - 1][y + 1])
                + (y == 0 ? 255 : gray[x][y - 1])
                + (y == h - 1 ? 255 : gray[x][y + 1])
                + (x == w - 1 ? 255 : gray[x + 1][y])
                + (x == w - 1 || y == 0 ? 255 : gray[x + 1][y - 1])
                + (x == w - 1 || y == h - 1 ? 255 : gray[x + 1][y + 1]);
        return rs / 9;
    }
}

