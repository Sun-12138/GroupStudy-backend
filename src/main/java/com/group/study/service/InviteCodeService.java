package com.group.study.service;

import cn.hutool.core.util.RandomUtil;
import com.group.study.common.state.StatusCode;
import com.group.study.context.UserContextHolder;
import com.group.study.exception.BusinessException;
import com.group.study.utils.redis.operate.RedisValueOperate;
import com.group.study.utils.redis.operate.param.factory.RedisOperateFactory;
import io.lettuce.core.RedisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 与邀请码有关的业务代码
 */
@Service
public class InviteCodeService {

    @Resource
    private ClassService classService;

    @Autowired
    @Qualifier("addInviteCode")
    private RedisValueOperate<String> addInviteCode;

    @Autowired
    @Qualifier("getInviteCode")
    private RedisValueOperate<String> getInviteCode;

    @Autowired
    @Qualifier("getInviteCodeTTL")
    private RedisValueOperate<Long> getInviteCodeTTL;

    /**
     * 创建班级邀请码
     *
     * @param classId 班级id
     * @param expires 邀请码有效期
     * @return 邀请码
     */
    public String createInviteCode(String classId, long expires) {
        //判断是否为班级所有者
        if (!classService.checkUserIsClassOwner(classId)) {
            //非班级所有者，无法创建
            throw new BusinessException(StatusCode.NO_AUTH_ERROR);
        }
        int maxNum = 5;
        String randomInvite = null;
        for (int i = 0; i < maxNum; i++) {
            randomInvite = RandomUtil.randomString(8);
            try {
                addInviteCode.exec(RedisOperateFactory.create(classId, randomInvite, expires, TimeUnit.SECONDS));
            } catch (RedisException e) {
                if (i == maxNum - 1) {
                    throw new BusinessException(StatusCode.SYSTEM_ERROR, "生成邀请码错误");
                }
                //key已存在,重新尝试生成信息
                continue;
            }
            break;
        }
        return randomInvite;
    }

    /**
     * 通过邀请码加入班级
     *
     * @param classId    班级id
     * @param inviteCode 邀请码
     */
    public void joinClassByInviteCode(String classId, String inviteCode) {
        if (!checkInviteCodeEffective(classId, inviteCode)) throw new BusinessException(StatusCode.NO_AUTH_ERROR, "邀请码错误或者过期");
        //判断是否已加入班级
        if (classService.checkUserIsClassMember(classId)) {
            //当前用户可能为班级所有者或者成员
            throw new BusinessException(StatusCode.OPERATION_ERROR, "你已加入该班级");
        }
        //加入班级
        String currentUserId = UserContextHolder.getContext().getUserId();
        classService.addClassMember(classId, currentUserId);
    }

    /**
     * 通过班级id获取邀请码
     *
     * @param classId 班级id
     * @return 邀请码
     */
    public String[] getInviteCodeByClassId(String classId) {
        return getInviteCode.exec(classId);
    }

    /**
     * 获取邀请码的剩余有效期
     * @param classId 班级id
     * @return 剩余时间 单位秒
     */
    public Long getInviteCodeTTL(String classId) {
        return getInviteCodeTTL.exec(classId)[0];
    }

    /**
     * 检查邀请码是否正确
     *
     * @param classId    班级id
     * @param inviteCode 邀请码
     * @return 是否正确
     */
    public boolean checkInviteCodeEffective(String classId, String inviteCode) {
        String[] code = getInviteCodeByClassId(classId);
        if (code.length == 0) return false;
        return Objects.equals(inviteCode, code[0]);
    }
}
