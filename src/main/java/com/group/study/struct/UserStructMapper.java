package com.group.study.struct;

import com.group.study.model.dto.request.RegisterRequest;
import com.group.study.model.dto.response.ClassMemberResponse;
import com.group.study.model.dto.response.UserInfoResponse;
import com.group.study.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 该 @Mapper为Mapstruct的Mapper
 */
@Mapper
public interface UserStructMapper {

    UserStructMapper MAPPER = Mappers.getMapper(UserStructMapper.class);

    /**
     * 注册请求对象转换用户实体对象
     *
     * @param request 注册请求对象
     * @return 用户实体对象
     */
    User from(RegisterRequest request);

    List<ClassMemberResponse.Member> from(List<User> user);

    UserInfoResponse from(User user);
}
