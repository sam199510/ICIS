package com.icis.backend.dao;

import com.icis.backend.entity.IcisRepairs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisRepairsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisRepairs record);

    int insertSelective(IcisRepairs record);

    IcisRepairs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisRepairs record);

    int updateByPrimaryKey(IcisRepairs record);

    List<IcisRepairs> selectMyIcisRepairs(Long residentId);
}