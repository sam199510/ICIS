package com.icis.backend.service;

import com.icis.backend.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserServiceI {
    /**
     * 检查是否存在系统用户
     * @param sysUser
     * @return 系统用户列表
     */
    List<SysUser> isExistSysUser(SysUser sysUser);

    /**
     * 检查密码是否正确
     * @param sysUser
     * @return 系统用户列表
     */
    List<SysUser> selectSysUserByUsernameAndPassword(SysUser sysUser);
}
