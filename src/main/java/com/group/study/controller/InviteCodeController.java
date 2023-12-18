package com.group.study.controller;

import com.group.study.annotation.Access;
import com.group.study.common.BaseResponse;
import com.group.study.common.ResultUtils;
import com.group.study.model.dto.request.InviteCodeRequest;
import com.group.study.model.enums.AccessRole;
import com.group.study.service.InviteCodeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class InviteCodeController {

    @Resource
    private InviteCodeService inviteCodeService;

    /**
     * 创建班级邀请码
     */
    @Access(AccessRole.Teacher)
    @PostMapping("/class/invite/code")
    public BaseResponse<String> createJoinUrl(@Valid @RequestBody InviteCodeRequest request) {
        //生成班级邀请码
        String inviteCode = inviteCodeService.createInviteCode(request.getClassId(), request.getExpires());
        return ResultUtils.success(request.getClassId() + "/" + inviteCode);
    }

    /**
     * 通过邀请码加入班级
     *
     * @param classId    班级id
     * @param inviteCode 班级邀请码
     */
    @Access(AccessRole.Student)
    @RequestMapping("/class/join/{classId}/{inviteCode}")
    public BaseResponse<String> joinClass(@PathVariable String classId, @PathVariable String inviteCode) {
        inviteCodeService.joinClassByInviteCode(classId, inviteCode);
        return ResultUtils.success("加入成功");
    }
}
