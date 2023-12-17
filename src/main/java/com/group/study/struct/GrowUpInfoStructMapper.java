package com.group.study.struct;

import com.group.study.model.dto.request.GrowInfoRequest;
import com.group.study.model.entity.GrowUpInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GrowUpInfoStructMapper {

    GrowUpInfoStructMapper MAPPER = Mappers.getMapper(GrowUpInfoStructMapper.class);

    GrowUpInfo from(GrowInfoRequest request);
}
