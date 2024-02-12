package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Mapper
public interface TestMapper {
    List<HashMap<String, Object>> selectAll();

    HashMap<String, Object> selectOne();
}
