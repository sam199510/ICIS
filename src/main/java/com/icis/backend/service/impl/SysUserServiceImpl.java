package com.icis.backend.service.impl;

import com.icis.backend.dao.SysUserMapper;
import com.icis.backend.entity.SysUser;
import com.icis.backend.service.SysUserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserServiceI {
    @Autowired
    private SysUserMapper sysUserMapper;

    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public List<SysUser> isExistSysUser(SysUser sysUser) {
        return this.sysUserMapper.isExistSysUser(sysUser);
    }

    @Override
    public List<SysUser> selectSysUserByUsernameAndPassword(SysUser sysUser) {
        return this.sysUserMapper.selectSysUserByUsernameAndPassword(sysUser);
    }
}
