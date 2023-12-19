package com.group.study.controller;

import com.group.study.annotation.Access;
import com.group.study.common.BaseResponse;
import com.group.study.common.ResultUtils;
import com.group.study.model.dto.request.GrowInfoRequest;
import com.group.study.model.dto.request.GrowInfoStudentRequest;
import com.group.study.model.entity.GrowUpInfo;
import com.group.study.model.enums.AccessRole;
import com.group.study.service.GrowService;
import com.group.study.struct.GrowUpInfoStructMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GrowUpInfoController {

    @Resource
    private GrowService growService;

    /**
     * 添加学生成长信息信息
     *
     * @param request 成长信息
     */
    @Access(AccessRole.Teacher)
    @PostMapping("/add/grow")
    public BaseResponse<String> addStudentGrowUpInfo(@Valid @RequestBody GrowInfoRequest request) {
        growService.addGrowUpInfo(GrowUpInfoStructMapper.MAPPER.from(request));
        return ResultUtils.success("成功");
    }

    /**
     * 获得所有成长信息列表
     *
     * @param classId 班级id
     * @return 成长信息列表
     */
    @Access({AccessRole.Student})
    @GetMapping("/grow/all/{classId}")
    public BaseResponse<List<GrowUpInfo>> getAllInfo(@PathVariable String classId) {
        return ResultUtils.success(growService.getUserAllInfo(classId));
    }

    @Access({AccessRole.Teacher})
    @PostMapping("/grow/student/info")
    public BaseResponse<List<GrowUpInfo>> getStudentAllInfo(@Valid @RequestBody GrowInfoStudentRequest request) {
        return ResultUtils.success(growService.getStudentAllInfo(request.getClassId(), request.getUserId()));
    }

}
