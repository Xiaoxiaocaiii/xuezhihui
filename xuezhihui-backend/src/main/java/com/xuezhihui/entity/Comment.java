package com.xuezhihui.entity;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Integer resourceId;
    private Integer userId;
    private String content;
    private String username;  // 查询时通过 JOIN user 表获取
}
