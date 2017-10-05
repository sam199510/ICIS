package com.icis.backend.controller;

import com.icis.backend.entity.IcisAppointmentRecord;
import com.icis.backend.entity.IcisResident;
import com.icis.backend.entity.IcisWorker;
import com.icis.backend.service.IcisAppointmentRecordServiceI;
import com.icis.backend.service.IcisWorkerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/icisWorker")
public class IcisWorkerController {
    /**
     * 将HttpServletRequest自动注入
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 将IcisWorkerServiceI自动注入
     */
    @Autowired
    private IcisWorkerServiceI icisWorkerServiceI;

    public void setIcisWorkerServiceI(IcisWorkerServiceI icisWorkerServiceI) {
        this.icisWorkerServiceI = icisWorkerServiceI;
    }

    /**
     * 将IcisAppointmentRecordService接口自动注入
     */
    @Autowired
    private IcisAppointmentRecordServiceI icisAppointmentRecordServiceI;

    public void setIcisAppointmentRecordServiceI(IcisAppointmentRecordServiceI icisAppointmentRecordServiceI) {
        this.icisAppointmentRecordServiceI = icisAppointmentRecordServiceI;
    }

    /**
     * 一键预约
     * 请求：/IcisWorker/oneBtnToAppointment.html
     * 请求类型：POST
     * @return 一键预约的员工
     */
    @RequestMapping(value = "oneBtnToAppointment", method = RequestMethod.POST)
    @ResponseBody
    public IcisWorker oneBthToAppointment(IcisResident resident){
        //筛选空闲杂工
        IcisWorker icisWorker = new IcisWorker();
        icisWorker.setState(0);
        icisWorker.setRole("杂工");
        IcisWorker freeWorker = null;
        //判断杂工人员是否还有空闲的
        if (this.icisWorkerServiceI.selectWorkerFree(icisWorker).size() == 0){
            return null;
        } else {
            freeWorker = this.icisWorkerServiceI.selectWorkerFree(icisWorker).get(0);
        }
        //更改杂工状态
        freeWorker.setState(1);
        this.icisWorkerServiceI.updateByPrimaryKey(freeWorker);
        //将预约结果添加到预约记录表中
        IcisAppointmentRecord icisAppointmentRecord = new IcisAppointmentRecord();
        icisAppointmentRecord.setServiceContent("一键预约，等待上门解决！");
        icisAppointmentRecord.setWorkerId(freeWorker.getId());
        icisAppointmentRecord.setCompany("社区委员会");
        icisAppointmentRecord.setIsApproved(1);
        icisAppointmentRecord.setResidentId(resident.getId());
        icisAppointmentRecord.setIsCompleted(0);
        Date create_time = new Date();
        icisAppointmentRecord.setCreateTime(create_time);
        this.icisAppointmentRecordServiceI.insert(icisAppointmentRecord);
        //返回预约到的员工
        return freeWorker;
    }

    /**
     * 登录验证
     * 请求：/icisWorker/loginCheck.html
     * 请求类型：POST
     * @param icisWorker
     * @return 登录验证结果
     */
    @RequestMapping(value = "loginCheck", method = RequestMethod.POST)
    @ResponseBody
    public String loginCheck(IcisWorker icisWorker) {
        //判断该社工是否存在
        int isExistState = this.icisWorkerServiceI.isExistIcisWorker(icisWorker).size();
        if (isExistState == 0) {
            return "该社工不存在";
        } else {
            //判断社工密码是否正确
            int passwordIsRight = this.icisWorkerServiceI.selectWorkerByUsernameAndPassword(icisWorker).size();
            if (passwordIsRight == 1) {
                return "登录成功";
            } else {
                return "登录失败，密码不正确";
            }
        }
    }

    /**
     * 登录获取社工信息
     * 请求类型：POST
     * 请求：/icisWorker/login.html
     * @param icisWorker
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public IcisWorker login(IcisWorker icisWorker) {
        return this.icisWorkerServiceI.selectWorkerByUsernameAndPassword(icisWorker).get(0);
    }
}
