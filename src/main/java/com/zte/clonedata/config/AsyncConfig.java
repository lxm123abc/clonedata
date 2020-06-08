package com.zte.clonedata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 定时任务线程池
 */
@Configuration
@EnableAsync
public class AsyncConfig {
    /**
     * @Value从配置中读取
     */


    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);//核心线程数
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(100);//队列容量
        executor.setKeepAliveSeconds(300);//空闲时间
        executor.initialize();
        executor.setThreadNamePrefix("JOB-");
        //executor.setAllowCoreThreadTimeOut(true);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
