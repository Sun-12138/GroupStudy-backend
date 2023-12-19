package com.group.study.controller;

import com.group.study.annotation.Access;
import com.group.study.common.BaseResponse;
import com.group.study.common.ResultUtils;
import com.group.study.model.dto.request.InviteCodeRequest;
import com.group.study.model.dto.response.InviteCodeResponse;
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
     * 获得班级已有的邀请码
     *
     * @param classId 班级id
     * @return 邀请码和剩余时间
     */
    @Access(AccessRole.Teacher)
    @GetMapping("/class/invite/{classId}")
    public BaseResponse<InviteCodeResponse> getInviteCodeList(@PathVariable String classId) {
        String[] code = inviteCodeService.getInviteCodeByClassId(classId);
        if (code.length == 0 || code[0] == null) {
            return ResultUtils.success(new InviteCodeResponse(null, 0L));
        }
        Long ttl = inviteCodeService.getInviteCodeTTL(classId);
        InviteCodeResponse response = new InviteCodeResponse(classId + "/" + code[0], ttl);
        return ResultUtils.success(response);
    }

    /**
     * 通过邀请码加入班级
     *
     * @param classId 班级id
     * @param code    班级邀请码
     */
    @Access(AccessRole.Student)
    @RequestMapping("/class/join/{classId}/{code}")
    public BaseResponse<String> joinClass(@PathVariable String classId, @PathVariable String code) {
        inviteCodeService.joinClassByInviteCode(classId, code);
        return ResultUtils.success("加入成功");
    }
}
