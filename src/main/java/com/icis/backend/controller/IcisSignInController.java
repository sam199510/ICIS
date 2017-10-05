package com.icis.backend.controller;

import com.icis.backend.entity.IcisSignIn;
import com.icis.backend.service.IcisSignInServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/icisSignIn")
public class IcisSignInController {
    /**
     * 将HttpServletRequest自动注入
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 将IcisSignInService接口自动注入
     */
    @Autowired
    private IcisSignInServiceI icisSignInServiceI;

    public void setIcisSignInServiceI(IcisSignInServiceI icisSignInServiceI) {
        this.icisSignInServiceI = icisSignInServiceI;
    }

    /**
     * 签到功能
     * 请求类型：POST
     * 请求：/icisSignIn/signIn.html
     * @param icisSignIn
     * @return 签到是否成功
     */
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    @ResponseBody
    private String signIn(IcisSignIn icisSignIn) {
        Date sign_in_time = new Date();
        icisSignIn.setSignInTime(sign_in_time);
        int signInState = this.icisSignInServiceI.signIn(icisSignIn);
        if (signInState == 1) {
            return "签到成功";
        } else {
            return "签到失败";
        }
    }
}
