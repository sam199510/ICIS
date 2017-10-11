package com.icis.backend.controller;

import com.icis.backend.entity.IcisActivity;
import com.icis.backend.entity.IcisSignIn;
import com.icis.backend.service.IcisActivityServiceI;
import com.icis.backend.service.IcisSignInServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
     * 将IcisActivityService接口自动注入
     */
    @Autowired
    private IcisActivityServiceI icisActivityServiceI;

    public void setIcisActivityServiceI(IcisActivityServiceI icisActivityServiceI) {
        this.icisActivityServiceI = icisActivityServiceI;
    }

    /**
     * 签到功能
     * 请求类型：POST
     * 请求：/icisSignIn/signIn.html
     * @param icisSignIn
     *        传入的参数有：
     *        signInAddress（签到地址）
     *        signInOrId（签到者id）
     *        signInActivity（签到的活动）
     * @return 签到是否成功
     *         成功返回"签到成功"
     *         失败返回"签到失败"
     */
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    @ResponseBody
    private String signIn(IcisSignIn icisSignIn) {
        //获取当前时间
        Date sign_in_time = new Date();
        //从数据库中读取正在进行的活动
        IcisActivity icisActivity = this.icisActivityServiceI.selectUnderwayIcisActivity(1);
        //判断是否在时间段内
        if (sign_in_time.before(icisActivity.getAllowSignInStartTime())) {
            return "时间未到";
        } else if ((sign_in_time.after(icisActivity.getAllowSignInStartTime()) ||
                    sign_in_time.equals(icisActivity.getAllowSignInStartTime())) &&
                   (sign_in_time.before(icisActivity.getAllowSignInFinalTime()) ||
                    sign_in_time.equals(icisActivity.getAllowSignInFinalTime()))) {
            //设置签到时间
            icisSignIn.setSignInTime(sign_in_time);
            //设置签到活动
            icisSignIn.setSignInActivity(icisActivity.getTitle());
            //设置前到活动内容
            icisSignIn.setSignInActivityContent(icisActivity.getContent1()+"\n"+icisActivity.getContent2() + "\n" + icisActivity.getContent3());
            //设置活动结束和开始时间
            icisSignIn.setSignInActivityFinalTime(icisActivity.getFinalTime());
            icisSignIn.setSignInActivityStartTime(icisActivity.getStartTime());
            //将签到存入数据库
            int signInState = this.icisSignInServiceI.signIn(icisSignIn);
            //判断签到是否成功
            if (signInState == 1) {
                return "签到成功";
            } else {
                return "签到失败";
            }
        } else {
            return "时间已过期";
        }
    }
}
