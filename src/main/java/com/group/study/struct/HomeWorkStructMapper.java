package com.group.study.struct;

import com.group.study.model.dto.request.HomeWorkRequest;
import com.group.study.model.entity.HomeWork;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HomeWorkStructMapper {

    HomeWorkStructMapper MAPPER = Mappers.getMapper(HomeWorkStructMapper.class);

    HomeWork from(HomeWorkRequest request);

}
