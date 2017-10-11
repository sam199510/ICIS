package com.icis.backend.dao;

import com.icis.backend.entity.IcisActivity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisActivity record);

    int insertSelective(IcisActivity record);

    IcisActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisActivity record);

    int updateByPrimaryKey(IcisActivity record);

    List<IcisActivity> seleceAllIcisActivity();

    IcisActivity selectUnderwayIcisActivity(Integer state);
}