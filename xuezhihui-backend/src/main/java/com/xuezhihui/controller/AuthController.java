package com.xuezhihui.controller;

import com.xuezhihui.entity.User;
import com.xuezhihui.mapper.UserMapper;
import com.xuezhihui.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserMapper userMapper;
    public AuthController(UserMapper userMapper) { this.userMapper = userMapper; }

    @PostMapping("/login")
    public ResultUtil<?> login(@RequestBody User user) {
        User exist = userMapper.selectByUsername(user.getUsername());
        if (exist == null || !exist.getPassword().equals(user.getPassword())) {
            return ResultUtil.error("用户名或密码错误");
        }
        exist.setPassword(null);
        return ResultUtil.success(exist);
    }
}
