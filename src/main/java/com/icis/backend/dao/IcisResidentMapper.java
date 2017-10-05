package com.icis.backend.dao;

import com.icis.backend.entity.IcisResident;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisResidentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisResident record);

    int insertSelective(IcisResident record);

    IcisResident selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisResident record);

    int updateByPrimaryKey(IcisResident record);

    List<IcisResident> selectAllResident();

    List<IcisResident> isExistAssignUser(IcisResident icisResident);

    List<IcisResident> selectByUsernameAndPassword(IcisResident icisResident);

    IcisResident selectIcisResidentByUsername(String username);

}