package com.xuezhihui.controller;

import com.xuezhihui.entity.User;
import com.xuezhihui.mapper.UserMapper;
import com.xuezhihui.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserMapper userMapper;
    public UserController(UserMapper userMapper) { this.userMapper = userMapper; }

    @PostMapping("/register")
    public ResultUtil<?> register(@RequestBody User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return ResultUtil.error("用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return ResultUtil.error("密码不能为空");
        }
        if (userMapper.selectByUsername(user.getUsername()) != null) {
            return ResultUtil.error("用户名已存在");
        }
        userMapper.insert(user);
        return ResultUtil.success("注册成功");
    }
}
