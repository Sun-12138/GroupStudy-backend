package com.group.study;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group.study.mapper.UserMapper;
import com.group.study.model.entity.User;
import com.group.study.model.enums.Sex;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class GroupStudyApplicationTests {

//    @Autowired
    UserMapper userMapper;

    @Test
    public void testService() {
//        System.out.println(roleMapper.selectById("10001"));
        Boolean flag = null;
        boolean flag2 = flag;
        System.out.println(flag2);
    }

    @Test
    public void testUser() {
        System.out.println(JSON.toJSON(Sex.MAN));
    }
}
