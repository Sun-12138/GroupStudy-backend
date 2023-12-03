package com.group.study.controller;

import com.group.study.annotation.PassToken;
import com.group.study.common.BaseResponse;
import com.group.study.common.ResultUtils;
import com.group.study.model.dto.request.LoginRequest;
import com.group.study.model.dto.request.RegisterRequest;
import com.group.study.model.dto.response.LoginResponse;
import com.group.study.model.entity.Role;
import com.group.study.service.UserService;
import com.group.study.service.security.LoginService;
import com.group.study.service.security.RegisterService;
import com.group.study.struct.UserStructMapper;
import com.group.study.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @Resource
    private RegisterService registerService;

    @Resource
    private UserService userService;

    /**
     * 登录
     *
     * @param request 登录请求对象
     * @return 响应信息
     */
    @PassToken
    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        //获取token
        String token = loginService.login(request.getTelephone(), request.getPassword());
        //获取用户角色
        String userId = JwtUtils.getTokenAud(token).get(0);
        Role userRole = userService.getRoleByUserId(userId);
        return ResultUtils.success(new LoginResponse(token, userRole));
    }

    /**
     * 注册
     *
     * @param request 注册请求对象
     * @return 响应信息
     */
    @PassToken
    @PostMapping("/register")
    public BaseResponse<String> register(@Valid @RequestBody RegisterRequest request) {
        registerService.register(UserStructMapper.MAPPER.from(request), request.getRoleId());
        return ResultUtils.success("注册成功");
    }
}
