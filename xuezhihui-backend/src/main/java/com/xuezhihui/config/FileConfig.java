package com.xuezhihui.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件上传配置类
 * <p>
 * 使用 Jakarta EE 的 {@link jakarta.servlet.MultipartConfigElement}
 * 显式配置文件上传参数。文件大小限制从 application.properties 读取，
 * Spring Boot 自动配置已从 {@code spring.servlet.multipart.*} 中读取并生效，
 * 本配置类提供编程式的显式 Bean 注册作为补充。
 * </p>
 * <p>
 * 注意：Spring Boot 4.x 全面采用 Jakarta EE 规范，文件上传相关 API
 * 使用 {@code jakarta.servlet} 包（而非旧版的 {@code javax.servlet}）。
 * </p>
 *
 * @author xuezhihui
 * @version 1.0.0
 */
@Configuration
public class FileConfig {

    @Value("${spring.servlet.multipart.max-file-size:100MB}")
    private String maxFileSize;

    @Value("${spring.servlet.multipart.max-request-size:100MB}")
    private String maxRequestSize;

    /**
     * 配置 MultipartConfigElement Bean
     * <p>
     * 使用 Jakarta EE 标准 API 创建文件上传配置。
     * 配置了单个文件最大大小、总请求最大大小等参数。
     * </p>
     *
     * @return Jakarta EE 标准的 MultipartConfigElement 实例
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        // 文件上传临时目录（null表示使用Servlet容器的默认临时目录）
        String location = null;
        // 单个文件最大大小：100MB = 104857600 字节
        long maxFileSizeBytes = 100L * 1024 * 1024;
        // 单次请求最大大小：100MB = 104857600 字节
        long maxRequestSizeBytes = 100L * 1024 * 1024;
        // 文件写入磁盘阈值：0表示所有上传文件先写入临时目录，避免大文件占满内存
        int fileSizeThreshold = 0;

        return new MultipartConfigElement(location, maxFileSizeBytes,
                maxRequestSizeBytes, fileSizeThreshold);
    }
}
