package com.group.study.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.group.study.annotation.Access;
import com.group.study.common.BaseResponse;
import com.group.study.common.ResultUtils;
import com.group.study.common.state.StatusCode;
import com.group.study.exception.BusinessException;
import com.group.study.model.dto.response.ClassMemberResponse;
import com.group.study.model.entity.Class;
import com.group.study.model.entity.User;
import com.group.study.model.enums.AccessRole;
import com.group.study.service.ClassService;
import com.group.study.struct.UserStructMapper;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ClassController {

    @Resource
    private ClassService classService;

    /**
     * 创建班级
     *
     * @param className 班级名称
     * @return 成功信息
     */
    @Access({AccessRole.Teacher})
    @PostMapping("/class/add")
    public BaseResponse<String> createClass(@NotBlank(message = "班级名不能为空") String className) {
        classService.createClass(className);
        return ResultUtils.success("班级:" + className + "注册成功");
    }

    /**
     * 获取当前用户所有班级
     *
     * @return 班级列表
     */
    @GetMapping("/class/all")
    public BaseResponse<List<Class>> getUserClassList() {
        return ResultUtils.success(classService.getAllClass());
    }

    /**
     * 获得班级成员列表
     */
    @Access({AccessRole.Teacher})
    @GetMapping("/class/member")
    public BaseResponse<ClassMemberResponse> getClassMember(int pageNum, String classId, int pageSize) {
        //检查当前用户是否为的当前的班级成员或创建者
        if (!classService.checkUserIsClassOwnerOrMember(classId)) {
            throw new BusinessException(StatusCode.NO_AUTH_ERROR);
        }
        IPage<User> page = classService.getClassMemberPage(pageNum, pageSize, classId);
        //转化为响应对象
        List<ClassMemberResponse.Member> classMember = UserStructMapper.MAPPER.from(page.getRecords());
        return ResultUtils.success(new ClassMemberResponse(classMember, page.getCurrent(), page.getTotal()));
    }
}
