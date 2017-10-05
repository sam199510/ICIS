package com.icis.backend.controller;

import com.icis.backend.entity.IcisOrder;
import com.icis.backend.entity.IcisResident;
import com.icis.backend.service.IcisOrderServiceI;
import com.icis.backend.service.IcisResidentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/icisOrder")
public class IcisOrderController {
    /**
     * 将HttpServletRequest自动注入
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 将IcisOrderService接口自动注入
     */
    @Autowired
    private IcisOrderServiceI icisOrderServiceI;

    public void setIcisOrderServiceI(IcisOrderServiceI icisOrderServiceI) {
        this.icisOrderServiceI = icisOrderServiceI;
    }

    /**
     * 将IcisResidentService接口自动注入
     */
    @Autowired
    private IcisResidentServiceI icisResidentServiceI;

    public void setIcisResidentServiceI(IcisResidentServiceI icisResidentServiceI) {
        this.icisResidentServiceI = icisResidentServiceI;
    }

    /**
     * 新增订单功能
     * 请求：/icisOrder/addIcisOrder.html
     * 请求类型：POST
     * @param icisOrder
     * @param residentUsername
     * @return 增加订单是否成功
     */
    @RequestMapping(value = "addIcisOrder", method = RequestMethod.POST)
    @ResponseBody
    public String addIcisOrder(IcisOrder icisOrder, String residentUsername) {
        //创建订单号
        Date create_time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String serialNo = "ICIS" + sdf.format(create_time);
        icisOrder.setSerialNo(serialNo);
        //设置订单创建时间和支付状态
        icisOrder.setCreateTime(create_time);
        icisOrder.setState(0);
        //筛选对应账户名对应的业主的信息
        IcisResident icisResident = this.icisResidentServiceI.selectIcisResidentByUsername(residentUsername);
        //设置付费者id
        icisOrder.setPayorId(icisResident.getId());
        //将订单存储进入数据库
        int addIcisOrderState = this.icisOrderServiceI.addIcisOrder(icisOrder);
        //判断订单添加是否成功
        if (addIcisOrderState == 1) {
            return "新增订单成功";
        } else {
            return "新增订单失败";
        }
    }

    /**
     * 按支付者id获取订单列表
     * 请求：/icisOrder/selectIcisOrderByPayorId.html
     * 请求类型：POST
     * @param icisResidentId
     * @return 订单列表
     */
    @RequestMapping(value = "selectIcisOrderByPayorId", method = RequestMethod.POST)
    @ResponseBody
    public List<IcisOrder> selectIcisOrderByPayorId(Long icisResidentId) {
        return this.icisOrderServiceI.selectIcisOrderByIcisResidentId(icisResidentId);
    }

    /**
     * 完成订单
     * @param icisOrder
     * @return 完成订单是否成功
     */
    @RequestMapping(value = "completeIcisOrder", method = RequestMethod.POST)
    @ResponseBody
    public String completeIcisOrder(IcisOrder icisOrder) {
        //修改订单完成状态
        icisOrder.setState(1);
        //将修改状态存入数据库
        int completeIcisOrderState = this.icisOrderServiceI.completeIcisOrder(icisOrder);
        //判断订单完成是否成功
        if (completeIcisOrderState == 1) {
            return "订单完成成功";
        } else {
            return "订单完成失败";
        }
    }
}
