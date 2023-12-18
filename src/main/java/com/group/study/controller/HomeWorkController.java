package com.group.study.controller;

import com.group.study.annotation.Access;
import com.group.study.common.BaseResponse;
import com.group.study.common.ResultUtils;
import com.group.study.model.dto.request.HomeWorkRequest;
import com.group.study.model.entity.HomeWork;
import com.group.study.model.enums.AccessRole;
import com.group.study.service.HomeWorkService;
import com.group.study.struct.HomeWorkStructMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class HomeWorkController {

    @Resource
    private HomeWorkService homeWorkService;

    /**
     * 添加班级作业
     *
     * @param request 班级作业信息
     */
    @Access(AccessRole.Teacher)
    @PostMapping("/class/work/add")
    public BaseResponse<String> addHomeWork(@Valid @RequestBody HomeWorkRequest request) {
        homeWorkService.addHomeWork(HomeWorkStructMapper.MAPPER.from(request));
        return ResultUtils.success("成功");
    }

    /**
     * 查看班级列表
     *
     * @param classId 班级id
     * @return 班级列表
     */
    @Access({AccessRole.Teacher, AccessRole.Student})
    @GetMapping("/class/work/list/{classId}")
    public BaseResponse<List<HomeWork>> getHomeWorkList(@PathVariable String classId) {
        return ResultUtils.success(homeWorkService.getHomeWorkList(classId));
    }
}
