package com.spring.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 *
 * @author ykzhuo
 * @since 2018-12-02 12:31
 */
@Configuration
public class TheadPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(6);
        pool.setMaxPoolSize(12);
        pool.setKeepAliveSeconds(3000);
        pool.setRejectedExecutionHandler( new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy() ); // 队列满，线程被拒绝执行策略
        return pool;
    }
}
