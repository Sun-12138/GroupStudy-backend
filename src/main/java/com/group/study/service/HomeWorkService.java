package com.group.study.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group.study.common.state.StatusCode;
import com.group.study.exception.BusinessException;
import com.group.study.mapper.HomeWorkMapper;
import com.group.study.model.entity.HomeWork;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class HomeWorkService {

    @Resource
    private HomeWorkMapper homeWorkMapper;

    @Resource
    private ClassService classService;

    /**
     * 班级添加作业
     *
     * @param homeWork 作业内容
     */
    public void addHomeWork(HomeWork homeWork) {
        //检查是否为班级所有者
        if (!classService.checkUserIsClassOwner(homeWork.getClassId())) {
            throw new BusinessException(StatusCode.NO_AUTH_ERROR);
        }
        //设置当前时间为创建时间
        homeWork.setCreateTime(new Timestamp(System.currentTimeMillis()));
        homeWorkMapper.insert(homeWork);
    }

    /**
     * 获得班级成员家庭作业
     *
     * @param classId 班级id
     * @return 作业列表
     */
    public List<HomeWork> getHomeWorkList(String classId) {
        QueryWrapper<HomeWork> qw = new QueryWrapper<>();
        qw.eq("class_id", classId)
                .eq("is_delete", 0);
        return homeWorkMapper.selectList(qw);
    }

}
