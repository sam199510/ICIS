package com.icis.backend.dao;

import com.icis.backend.entity.IcisSignIn;
import org.springframework.stereotype.Repository;

@Repository
public interface IcisSignInMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisSignIn record);

    int insertSelective(IcisSignIn record);

    IcisSignIn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisSignIn record);

    int updateByPrimaryKey(IcisSignIn record);
}