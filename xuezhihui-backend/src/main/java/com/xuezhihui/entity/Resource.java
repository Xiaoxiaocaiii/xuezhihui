package com.xuezhihui.entity;

import lombok.Data;

@Data
public class Resource {
    private Integer id;
    private String title;
    private String filePath;
    private String fileName;
    private Integer userId;
    private String username;
}
