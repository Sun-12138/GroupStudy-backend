package com.group.study.controller;

import com.group.study.annotation.Access;
import com.group.study.common.BaseResponse;
import com.group.study.common.ResultUtils;
import com.group.study.model.dto.response.ClassInfoResponse;
import com.group.study.model.entity.Class;
import com.group.study.model.entity.User;
import com.group.study.model.enums.AccessRole;
import com.group.study.service.ClassService;
import com.group.study.service.UserService;
import com.group.study.struct.ClassStructMapper;
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

    @Resource
    private UserService userService;

    /**
     * 创建班级
     *
     * @param className 班级名称
     * @return 成功信息
     */
    @Access(AccessRole.Teacher)
    @PostMapping("/class/add")
    public BaseResponse<String> createClass(@NotBlank(message = "班级名不能为空") String className) {
        classService.createClass(className);
        return ResultUtils.success("班级:" + className + "注册成功");
    }

    /**
     * 获取老师创建的班级
     *
     * @return 班级列表
     */
    @Access(AccessRole.Teacher)
    @GetMapping("/class/teacher/all")
    public BaseResponse<List<Class>> getTeacherClassList() {
        return ResultUtils.success(classService.getTeacherClass());
    }

    /**
     * 获取学生所有班级
     *
     * @return 加入的班级
     */
    @Access(AccessRole.Student)
    @GetMapping("/class/student/all")
    public BaseResponse<ClassInfoResponse> getStudentClassList() {
        Class classInfo = classService.getStudentClass();
        if (classInfo == null) {
            return ResultUtils.success(null);
        }
        User teacherInfo = userService.getUserByUserId(classInfo.getUserId());
        ClassInfoResponse response = ClassStructMapper.MAPPER.from(classInfo);
        //设置创建者教师信息
        response.setUser(UserStructMapper.MAPPER.from(teacherInfo));
        return ResultUtils.success(response);
    }
}
