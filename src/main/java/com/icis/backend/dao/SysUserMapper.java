package com.icis.backend.dao;

import com.icis.backend.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> isExistSysUser(SysUser sysUser);

    List<SysUser> selectSysUserByUsernameAndPassword(SysUser sysUser);
}