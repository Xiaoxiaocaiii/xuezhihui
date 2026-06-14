package com.xuezhihui.mapper;

import com.xuezhihui.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int insert(User user);
    User selectByUsername(@Param("username") String username);
}
