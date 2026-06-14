-- 创建数据库
CREATE DATABASE IF NOT EXISTS xuezhihui
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE xuezhihui;

-- ============================================================
-- 1. 用户表（3 个字段）
-- ============================================================
CREATE TABLE user (
                      id       INT          NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                      username VARCHAR(50)  NOT NULL                COMMENT '用户名（唯一）',
                      password VARCHAR(100) NOT NULL                COMMENT '密码（明文）',
                      PRIMARY KEY (id),
                      UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ============================================================
-- 2. 资源表（6 个字段）
-- ============================================================
CREATE TABLE resource (
                          id        INT          NOT NULL AUTO_INCREMENT COMMENT '资源ID',
                          title     VARCHAR(200) NOT NULL                COMMENT '资源标题',
                          file_path VARCHAR(500) NOT NULL                COMMENT '服务器存储路径',
                          file_name VARCHAR(200) NOT NULL                COMMENT '原始文件名',
                          user_id   INT          NOT NULL                COMMENT '上传者ID',
                          username  VARCHAR(50)  NOT NULL DEFAULT ''     COMMENT '上传者用户名',
                          PRIMARY KEY (id),
                          KEY idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资源表';

-- ============================================================
-- 3. 评论表（5 个字段，已添加username，和资源表保持一致）
-- ============================================================
CREATE TABLE comment (
                         id          INT          NOT NULL AUTO_INCREMENT COMMENT '评论ID',
                         resource_id INT          NOT NULL                COMMENT '资源ID',
                         user_id     INT          NOT NULL                COMMENT '评论者ID',
                         username    VARCHAR(50)  NOT NULL DEFAULT ''     COMMENT '评论者用户名',
                         content     TEXT         NOT NULL                COMMENT '评论内容',
                         PRIMARY KEY (id),
                         KEY idx_resource_id (resource_id),
                         KEY idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';
