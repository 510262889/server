package com.spring.server.service.impl;

import ch.qos.logback.core.util.CloseUtil;
import com.spring.server.service.TheasuresService;
import com.spring.server.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 词库服务
 *
 * @author ykzhuo
 * @since 2018-12-02 10:32
 */
@Service
public class TheasuresServiceImpl implements TheasuresService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private String url = "H:\\旧文件\\汉语词库\\data";

    @Override
    public void init() {
        File theasuresFile = new File( url );

        File[] files = theasuresFile.listFiles();
        int count = 0;
        for ( File file : files ) {
            count++;
            threadPoolTaskExecutor.execute( new ReadFileThread( file, count ) );
        }

        System.err.println( "文件读取完成，总文件数:" + count );
    }

    /**
     * 校验文字内容
     *
     * @param content 验证内容
     *
     * @return 没有包含英文和数字就返回true
     */
    private boolean checkContent( String content ) {
        // 英文
        Pattern p1 = Pattern.compile( "[a-zA-z]" );
        // 数字
        Pattern p2 = Pattern.compile( "[0-9]" );
        return !p1.matcher( content ).find() && !p2.matcher( content ).find();
    }

    /**
     * 文件读取线程
     */
    private class ReadFileThread implements Runnable {

        private FileInputStream fileInputStream = null;
        private InputStreamReader inputStreamReader = null;
        private BufferedReader bufferedReader = null;

        private String content;
        private List<Object[]> theasures = new ArrayList<>();
        private int batch = 500;
        private int count;

        ReadFileThread( File file, int count ) {
            try {
                this.fileInputStream = new FileInputStream( file );
                this.inputStreamReader = new InputStreamReader( fileInputStream );
                this.bufferedReader = new BufferedReader( inputStreamReader );
                this.theasures = new ArrayList<>();
                this.count = count;
            } catch ( FileNotFoundException e ) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while ( (content = bufferedReader.readLine()) != null ) {
                    // 过滤无法识别的词
                    if ( checkContent( content ) && content.length() <= 3 ) theasures.add( new String[] { UUIDUtil.generateUUID(), content.trim() } );
                    if ( theasures.size() % batch == 0 ) {
                        List<Object[]> insertList = new ArrayList<>( theasures );
                        threadPoolTaskExecutor.execute( new InsertTheasuresThread( insertList ) );
                        theasures.clear();
                    }
                }
                // 文件读取结束如果还有剩余，则直接录入到数据库
                if ( theasures.size() > 0 ) {
                    List<Object[]> insertList = new ArrayList<>( theasures );
                    threadPoolTaskExecutor.execute( new InsertTheasuresThread( insertList ) );
                    theasures.clear();
                }
                System.out.println( "解析文件第" + count + "个文件完成" );
            } catch ( IOException e ) {} finally {
                CloseUtil.closeQuietly( fileInputStream );
                CloseUtil.closeQuietly( inputStreamReader );
                CloseUtil.closeQuietly( bufferedReader );
            }

        }

    }

    /**
     * 新增词库线程
     */
    private class InsertTheasuresThread implements Runnable {

        private final String SQL = "insert into detector (id, name) values (?, ?)";
        private List<Object[]> theasures;

        InsertTheasuresThread( List<Object[]> theasures ) {
            this.theasures = theasures;
        }

        @Override
        public void run() {
            try {
                jdbcTemplate.batchUpdate( SQL, theasures );
            } catch ( Exception e ) {
                // 批量处理有一条发生错误就会全部回滚，这时候重新按条插入
                for ( Object[] objects : theasures ) try { jdbcTemplate.update( SQL, objects ); } catch ( Exception x ) {}
            }
        }
    }
}
