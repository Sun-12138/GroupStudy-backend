package com.group.study.struct;

import com.group.study.model.dto.response.ClassInfoResponse;
import com.group.study.model.entity.Class;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClassStructMapper {
    ClassStructMapper MAPPER = Mappers.getMapper(ClassStructMapper.class);

    @Mapping(ignore = true, target = "user")
    ClassInfoResponse from(Class source);
}
