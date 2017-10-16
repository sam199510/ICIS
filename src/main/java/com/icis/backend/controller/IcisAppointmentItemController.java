package com.icis.backend.controller;

import com.icis.backend.entity.*;
import com.icis.backend.entity.IcisAppointmentItem;
import com.icis.backend.service.IcisAppointmentItemServiceI;
import com.icis.backend.service.IcisWorkerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/icisAppointmentItem")
public class IcisAppointmentItemController {

    /**
     * 将IcisAppointmentItemService接口自动注入
     */
    @Autowired
    private IcisAppointmentItemServiceI icisAppointmentItemServiceI;

    public void setIcisAppointmentItemServiceI(IcisAppointmentItemServiceI icisAppointmentItemServiceI) {
        this.icisAppointmentItemServiceI = icisAppointmentItemServiceI;
    }

    /**
     * 将IcisWorkerService接口自动注入
     */
    @Autowired
    private IcisWorkerServiceI icisWorkerServiceI;

    public void setIcisWorkerServiceI(IcisWorkerServiceI icisWorkerServiceI) {
        this.icisWorkerServiceI = icisWorkerServiceI;
    }

    /**
     * 筛选所有预约项目
     * 请求类型：POST
     * 请求：/icisAppointmentItem/selectAllIcisAppointmentItem.html
     * @return 预约项目的JSON
     */
    @RequestMapping(value = "selectAllIcisAppointmentItem", method = RequestMethod.POST)
    @ResponseBody
    public List<com.icis.backend.model.IcisAppointmentItem> selectAllIcisAppointmentItem(){
        //先将所有预约的项目筛选出来
        List<IcisAppointmentItem> icisAppointmentItems = this.icisAppointmentItemServiceI.selectAllIcisAppointmentItem();
        //建立一个预约的列表
        List<com.icis.backend.model.IcisAppointmentItem> icisAppointmentItemsModel = new ArrayList<com.icis.backend.model.IcisAppointmentItem>();
        //循环遍历所有预约项目
        for (IcisAppointmentItem icisAppointmentItem: icisAppointmentItems) {
            //建立一个模型对象，以备之后添加到列表中使用
            com.icis.backend.model.IcisAppointmentItem icisAppointmentItemUnitModel = new com.icis.backend.model.IcisAppointmentItem();
            //将各种属性设置到之前建立的对象中
            icisAppointmentItemUnitModel.setId(icisAppointmentItem.getId());
            icisAppointmentItemUnitModel.setServicePhoto(icisAppointmentItem.getServicePhoto());
            icisAppointmentItemUnitModel.setServiceContent(icisAppointmentItem.getServiceContent());
            icisAppointmentItemUnitModel.setWorkerId(icisAppointmentItem.getWorkerId());
            icisAppointmentItemUnitModel.setGrade(icisAppointmentItem.getGrade());
            icisAppointmentItemUnitModel.setState(icisAppointmentItem.getState());
            icisAppointmentItemUnitModel.setCompany(icisAppointmentItem.getCompany());
            icisAppointmentItemUnitModel.setIsApproved(icisAppointmentItem.getIsApproved());
            icisAppointmentItemUnitModel.setType(icisAppointmentItem.getType());
            icisAppointmentItemUnitModel.setServiceAbbreviation(icisAppointmentItem.getServiceAbbreviation());
            //筛选出对应社工号的社工
            IcisWorker icisWorker = this.icisWorkerServiceI.selectByPrimaryKey(icisAppointmentItem.getWorkerId());
            icisAppointmentItemUnitModel.setIcisWorker(icisWorker);
            //将每个筛选到的社工服务项目添加到List中
            icisAppointmentItemsModel.add(icisAppointmentItemUnitModel);
        }
        //返回服务项目模型列表
        return icisAppointmentItemsModel;
    }
}
