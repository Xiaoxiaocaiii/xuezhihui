package com.xuezhihui;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 学知汇·大学版 后端项目 — 测试基类
 * <p>
 * 使用 Spring Boot 4.0.6 + JUnit 5 进行集成测试，
 * {@code @SpringBootTest} 注解会加载完整的 Spring 应用上下文。
 * </p>
 *
 * @author xuezhihui
 * @version 1.0.0
 */
@SpringBootTest
class XuezhihuiApplicationTests {

    /**
     * 验证 Spring 应用上下文能否正常加载
     * <p>最基本的冒烟测试，确保所有Bean能正确装配。</p>
     */
    @Test
    void contextLoads() {
        // 如果上下文加载失败，此测试将抛出异常
    }
}
