package com.xuezhihui.mapper;

import com.xuezhihui.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {
    int insert(Resource resource);
    List<Resource> selectAll();
    Resource selectById(Integer id);
    int deleteById(@Param("id") Integer id);
}
