package com.xuezhihui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 学知汇·大学版 后端项目 — 启动类
 * <p>
 * Spring Boot 4.0.6 项目入口，使用 {@link SpringBootApplication} 注解
 * 自动开启组件扫描、自动配置等核心功能。
 * </p>
 *
 * @author xuezhihui
 * @version 1.0.0
 * @since 2024
 */
@SpringBootApplication
public class XuezhihuiApplication {

    /**
     * 应用程序主入口方法
     *
     * @param args 命令行启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(XuezhihuiApplication.class, args);
        System.out.println("========================================");
        System.out.println("  学知汇·大学版 后端服务启动成功！");
        System.out.println("  访问地址: http://localhost:8080");
        System.out.println("========================================");
    }
}
