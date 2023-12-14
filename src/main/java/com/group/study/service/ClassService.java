package com.group.study.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group.study.common.state.StatusCode;
import com.group.study.context.UserContextHolder;
import com.group.study.exception.BusinessException;
import com.group.study.mapper.ClassMapper;
import com.group.study.model.entity.Class;
import com.group.study.model.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * 与班级有关的业务代码
 */
@Service
public class ClassService {

    @Resource
    private ClassMapper classMapper;

    /**
     * 创建班级
     *
     * @param className 班级名称
     */
    public void createClass(String className) {
        //查询班级名为 className 的班级对象
        Class dbClass = this.getClassByName(className);
        if (dbClass != null) {
            throw new BusinessException(StatusCode.PARAMS_ERROR, "当前班级名已存在");
        }
        //新的班级对象
        Class newClass = new Class();
        newClass.setClassName(className);
        //生成一个唯一班级id
        int maxNumber = 5;
        for (int i = 0; i < maxNumber; i++) {
            String uniqueId = RandomUtil.randomNumbers(8 + i);
            if (this.getClassById(uniqueId) == null) {
                newClass.setClassId(uniqueId);
                break;
            }
            if (i == maxNumber - 1) {
                //运气不好 随机不到唯一id
                throw new BusinessException(StatusCode.SYSTEM_ERROR, "创建失败");
            }
        }
        //设置当前时间为创建时间
        newClass.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //设置当前用户id为班级创建者
        newClass.setUserId(UserContextHolder.getContext().getUserId());
        if (!this.addClass(newClass)) {
            throw new BusinessException(StatusCode.SYSTEM_ERROR, "创建失败");
        }
    }

    /**
     * 通过班级名获取当前用户所有的班级
     *
     * @param className 班级名
     * @return 班级对象
     */
    public Class getClassByName(String className) {
        //获得当前用户id
        String userId = UserContextHolder.getContext().getUserId();
        QueryWrapper<Class> qw = new QueryWrapper<>();
        qw.eq("user_id", userId)
                .eq("class_name", className);
        return classMapper.selectOne(qw);
    }

    /**
     * 通过id获取班级对象
     *
     * @param classId 班级id
     * @return 班级对象
     */
    public Class getClassById(String classId) {
        QueryWrapper<Class> qw = new QueryWrapper<>();
        qw.eq("class_id", classId);
        return classMapper.selectOne(qw);
    }

    /**
     * 获得当前用户所有班级
     *
     * @return 当前用户班级列表
     */
    public List<Class> getAllClass() {
        //当前用户id
        String userId = UserContextHolder.getContext().getUserId();
        QueryWrapper<Class> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        return classMapper.selectList(qw);
    }

    /**
     * 获得班级的成员
     *
     * @param classId 班级id
     * @return 成员用户id列表
     */
    public List<String> getClassMember(String classId) {
        return classMapper.getClassMemberId(classId);
    }

    /**
     * 添加一个新的班级
     *
     * @param newClass 新的班级对象
     * @return 是否成功
     */
    public boolean addClass(Class newClass) {
        return classMapper.insert(newClass) != 0;
    }

    /**
     * 添加班级成员
     *
     * @param classId 班级id
     * @param userId  用户id
     */
    public void addClassMember(String classId, String userId) {
        classMapper.addClassMember(classId, userId);
    }

    /**
     * 检查当前用户是否为班级所有者
     *
     * @param classId 班级id
     * @return true为属于 false为不属于
     */
    public boolean checkUserIsClassOwner(String classId) {
        //当前用户Id
        String currentUserId = UserContextHolder.getContext().getUserId();
        //检查是否为对应的班级创建者
        String ownerUserId = this.getClassById(classId).getUserId();
        return ownerUserId.equals(currentUserId);
    }

    /**
     * 检查当前用户是否加入该班级
     *
     * @param classId 班级id
     * @return true为已加入 false为未加入
     */
    public boolean checkUserIsClassMember(String classId) {
        //当前用户id
        String userid = UserContextHolder.getContext().getUserId();
        //如果是班级所有者则为该班级成员
        return this.getClassMember(classId).contains(userid);
    }

    /**
     * 检查用户是否为班级的所有者或者成员
     *
     * @param classId 班级id
     * @return true是班级的所有者或者成员 false则相反
     */
    public boolean checkUserIsClassOwnerOrMember(String classId) {
        return this.checkUserIsClassOwner(classId) || this.checkUserIsClassMember(classId);
    }

    /**
     * 分页方式查询成员
     *
     * @param pageNum 页面页号
     * @param pageSize 每页数据大小
     * @param classId 班级id
     */
    public IPage<User> getClassMemberPage(int pageNum, int pageSize, String classId) {
        //数据库中的User数据
        return classMapper.getClassMember(new Page<>(pageNum, pageSize), classId);
    }


}
