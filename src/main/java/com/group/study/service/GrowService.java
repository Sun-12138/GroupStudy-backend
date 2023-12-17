package com.group.study.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group.study.common.state.StatusCode;
import com.group.study.context.UserContextHolder;
import com.group.study.exception.BusinessException;
import com.group.study.mapper.GrowMapper;
import com.group.study.model.entity.GrowUpInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GrowService {

    @Resource
    private GrowMapper growMapper;

    @Resource
    private ClassService classService;

    public void addGrowUpInfo(GrowUpInfo info) {
        //判断当前用户是否为当前班级的成员或者拥有者 并且userId是否属于当前班级
        if (!classService.checkUserIsClassOwnerOrMember(info.getClassId())) {
            throw new BusinessException(StatusCode.NO_AUTH_ERROR);
        }
        //设置当前时间为创建时间
        growMapper.insert(info);
    }

    public List<GrowUpInfo> getUserAllInfo(String classId) {
        String userId = UserContextHolder.getContext().getUserId();
        QueryWrapper<GrowUpInfo> qw = new QueryWrapper<>();
        qw.eq("class_id", classId)
                .eq("user_id", userId);
        return growMapper.selectList(qw);
    }
}
