package com.icis.backend.dao;

import com.icis.backend.entity.IcisDynamic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisDynamicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisDynamic record);

    int insertSelective(IcisDynamic record);

    IcisDynamic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisDynamic record);

    int updateByPrimaryKey(IcisDynamic record);

    List<IcisDynamic> selectAllIcisDynamic();
}