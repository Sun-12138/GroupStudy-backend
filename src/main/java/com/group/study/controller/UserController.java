package com.group.study.controller;

import cn.hutool.core.lang.Validator;
import com.group.study.annotation.PassToken;
import com.group.study.common.BaseResponse;
import com.group.study.common.ResultUtils;
import com.group.study.common.state.StatusCode;
import com.group.study.context.UserContextHolder;
import com.group.study.exception.BusinessException;
import com.group.study.model.dto.request.LoginRequest;
import com.group.study.model.dto.request.RegisterRequest;
import com.group.study.model.dto.response.LoginResponse;
import com.group.study.model.dto.response.UserInfoResponse;
import com.group.study.model.entity.Role;
import com.group.study.model.entity.User;
import com.group.study.service.UserService;
import com.group.study.service.security.AccountService;
import com.group.study.struct.UserStructMapper;
import com.group.study.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private AccountService accountService;


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
        //检查手机号是否正确格式
        if (!Validator.isNumber(request.getTelephone())) {
            throw new BusinessException(StatusCode.PARAMS_ERROR, "手机号格式错误");
        }
        //获取token
        String token = accountService.login(request.getTelephone(), request.getPassword());
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
        accountService.register(UserStructMapper.MAPPER.from(request), request.getRoleId());
        return ResultUtils.success("注册成功");
    }

    /**
     * 获得角色列表
     */
    @PassToken
    @GetMapping("/role/list")
    public BaseResponse<List<Role>> roleList() {
        return ResultUtils.success(userService.getAllRole());
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/user/info")
    public BaseResponse<UserInfoResponse> getUserInfo() {
        User user = userService.getUserByUserId(UserContextHolder.getContext().getUserId());
        return ResultUtils.success(UserStructMapper.MAPPER.from(user));
    }

    /**
     * 检查登录状态
     *
     * @return 登录成功
     */
    @RequestMapping("/test")
    public BaseResponse<String> checkLogin() {
        return ResultUtils.success("成功");
    }
}
