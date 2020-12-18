package com.pandau.cloud.mapper;

import com.pandau.cloud.entities.PayInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PayMapper {

    int create(PayInfo payInfo);

    PayInfo getById(@Param("id")Long id);
}
