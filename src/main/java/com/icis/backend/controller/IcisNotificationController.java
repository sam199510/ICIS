package com.icis.backend.controller;

import com.icis.backend.entity.IcisNotification;
import com.icis.backend.service.IcisNotificationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/icisNotification")
public class IcisNotificationController {
    @Autowired
    private IcisNotificationServiceI icisNotificationServiceI;

    public void setIcisNotificationServiceI(IcisNotificationServiceI icisNotificationServiceI) {
        this.icisNotificationServiceI = icisNotificationServiceI;
    }

    /**
     * 获取所有通知信息
     * 请求：/icisNotification/selectAllNotification.html
     * 请求类型：POST
     * @return 通知列表
     */
    @RequestMapping(value = "selectAllNotification", method = RequestMethod.POST)
    @ResponseBody
    public List<IcisNotification> selectAllNotification() {
        return this.icisNotificationServiceI.selectAllNotification();
    }

    /**
     * 发布通知
     * 请求：/icisNotification/publishNotification.html
     * 请求类型：POST
     * @param icisNotification
     * @return
     */
    @RequestMapping(value = "publishNotification", method = RequestMethod.POST)
    @ResponseBody
    public String publishNotification(IcisNotification icisNotification) {
        //获取当前日期
        Date publish_date = new Date();
        icisNotification.setDate(publish_date);
        //将通知数据存储进入数据库
        int publishState = this.icisNotificationServiceI.publishNotification(icisNotification);
        //判断通知发布是否成功
        if (publishState == 1) {
            return "发布通知成功";
        } else {
            return "发布通知失败";
        }
    }

    /**
     * 通知修改
     * 请求类型：POST
     * 请求：/icisNotification/modifyNotification.html
     * @param icisNotification
     * @return 通知修改是否成功
     */
    @RequestMapping(value = "modifyNotification", method = RequestMethod.POST)
    @ResponseBody
    public String modifyNotification(IcisNotification icisNotification) {
        if (this.icisNotificationServiceI.modifyNotification(icisNotification) == 0) {
            return "修改通知失败";
        } else {
            return "修改通知成功";
        }
    }
}
