package com.project.demo.dao.base;


import javax.persistence.MapKey;
import java.util.List;
import java.util.Map;

public interface BaseMapper<E> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<E> {

//    @MapKey("id")
    List<Map<String,Object>> selectBaseList(String select);

    Integer selectBaseCount(String count);

    Object selectBaseOne(String select);

    int updateBaseSql(String sql);

    int deleteBaseSql(String sql);
}
