package com.xuezhihui.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultUtil<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> ResultUtil<T> success(T data) {
        return new ResultUtil<>(200, "操作成功", data);
    }

    public static <T> ResultUtil<T> error(String message) {
        return new ResultUtil<>(500, message, null);
    }
}
