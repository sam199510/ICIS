package com.icis.backend.controller;

import com.icis.backend.entity.SysUser;
import com.icis.backend.service.SysUserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {
    /**
     * 将HttpServletRequest自动注入
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 将SysUserService接口自动注入
     */
    @Autowired
    private SysUserServiceI sysUserServiceI;

    public void setSysUserServiceI(SysUserServiceI sysUserServiceI) {
        this.sysUserServiceI = sysUserServiceI;
    }

    /**
     * 登录验证
     * @param sysUser
     * @return 是否登录成功
     */
    @RequestMapping(value = "loginCheck", method = RequestMethod.POST)
    @ResponseBody
    public String isEixstSysUser(SysUser sysUser) {
        //判断是否存在该系统用户
        int isExistSysUserState = this.sysUserServiceI.isExistSysUser(sysUser).size();
        if (isExistSysUserState == 0) {
            return "该系统用户不存在";
        } else {
            //判断密码是否正确
            int isPasswordRight = this.sysUserServiceI.selectSysUserByUsernameAndPassword(sysUser).size();
            if (isPasswordRight == 0) {
                return "密码不正确";
            } else {
                return "登录成功";
            }
        }
    }

    /**
     * 登录获取用户对象
     * @param sysUser
     * @return 对象
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public SysUser login(SysUser sysUser) {
        return this.sysUserServiceI.selectSysUserByUsernameAndPassword(sysUser).get(0);
    }
}
