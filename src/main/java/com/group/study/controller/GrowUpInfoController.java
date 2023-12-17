package com.group.study.controller;

import com.group.study.annotation.Access;
import com.group.study.common.BaseResponse;
import com.group.study.common.ResultUtils;
import com.group.study.model.dto.request.GrowInfoRequest;
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

    @Access(AccessRole.Teacher)
    @PostMapping("/add/grow")
    public BaseResponse<String> addStudentGrowUpInfo(@Valid @RequestBody GrowInfoRequest request) {
        growService.addGrowUpInfo(GrowUpInfoStructMapper.MAPPER.from(request));
        return ResultUtils.success("成功");
    }

    @Access(AccessRole.Student)
    @GetMapping("/grow/all/{classId}")
    public BaseResponse<List<GrowUpInfo>> getAllInfo(@PathVariable String classId) {
        return ResultUtils.success(growService.getUserAllInfo(classId));
    }

}
