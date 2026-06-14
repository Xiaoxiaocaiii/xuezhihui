package com.xuezhihui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步任务配置类
 * <p>
 * 使用 {@link EnableAsync} 注解开启 Spring 的异步任务支持，
 * 配合 {@code @Async} 注解可让方法在独立线程池中异步执行，
 * 适用于发送邮件、日志记录、文件处理等耗时操作。
 * </p>
 *
 * @author xuezhihui
 * @version 1.0.0
 */
@Configuration
@EnableAsync  // 开启Spring异步任务支持
public class AsyncConfig {

    /**
     * 配置异步任务线程池
     * <p>
     * 核心线程数5、最大线程数10、队列容量100，
     * 拒绝策略为"调用者运行策略"（CallerRunsPolicy）——
     * 当线程池和队列都满时，任务由调用线程直接执行，
     * 这种方式不会丢失任务，同时能减缓新任务的提交速度。
     * </p>
     *
     * @return 线程池执行器
     */
    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数：线程池中始终保持活跃的线程数量
        executor.setCorePoolSize(5);
        // 最大线程数：线程池中允许的最大线程数量
        executor.setMaxPoolSize(10);
        // 缓冲队列容量：当核心线程都在忙时，新任务在队列中等待
        executor.setQueueCapacity(100);
        // 线程名前缀：便于在日志和监控工具中识别异步任务线程
        executor.setThreadNamePrefix("xuezhihui-async-");
        // 拒绝策略：调用者运行策略（队列满时由主线程执行，防止任务丢失）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务完成后再关闭线程池（优雅停机）
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 等待超时时间：30秒（超过此时间未完成的任务将被中断）
        executor.setAwaitTerminationSeconds(30);
        // 初始化线程池
        executor.initialize();
        return executor;
    }
}
