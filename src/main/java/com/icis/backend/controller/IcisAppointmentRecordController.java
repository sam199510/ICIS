package com.icis.backend.controller;

import com.alibaba.fastjson.JSON;
import com.icis.backend.entity.IcisAppointmentItem;
import com.icis.backend.entity.IcisAppointmentRecord;
import com.icis.backend.entity.IcisResident;
import com.icis.backend.entity.IcisWorker;
import com.icis.backend.service.IcisAppointmentItemServiceI;
import com.icis.backend.service.IcisAppointmentRecordServiceI;
import com.icis.backend.service.IcisResidentServiceI;
import com.icis.backend.service.IcisWorkerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/icisAppointmentRecord")
public class IcisAppointmentRecordController {
    /**
     * 将HttpServletRequest自动注入
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 将IcisAppointmentRecordService接口自动注入
     */
    @Autowired
    private IcisAppointmentRecordServiceI icisAppointmentRecordServiceI;

    public void setIcisAppointmentRecordServiceI(IcisAppointmentRecordServiceI icisAppointmentRecordServiceI) {
        this.icisAppointmentRecordServiceI = icisAppointmentRecordServiceI;
    }

    /**
     * 将ICISAppointmentItemService接口自动注入
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
     * 将IcisResidentService接口自动注入
     */
    @Autowired
    private IcisResidentServiceI icisResidentServiceI;

    public void setIcisResidentServiceI(IcisResidentServiceI icisResidentServiceI) {
        this.icisResidentServiceI = icisResidentServiceI;
    }

    /**
     * 完成预约，并修改预约完成时间和预约状态
     * 请求：/icisAppointmentRecord/completeAppointment.html
     * 请求类型：POST
     * @param icisAppointmentRecord
     *        其中需要传入的参数有：
     *        id（预约记录id）
     * @return 预约完成是否成功
     *         成功返回"预约完成"
     *         失败返回"预约完成失败"
     */
    @RequestMapping(value = "completeAppointment", method = RequestMethod.POST)
    @ResponseBody
    public String completeAppointment(IcisAppointmentRecord icisAppointmentRecord){
        //修改更新预约完成状态和时间
        icisAppointmentRecord.setIsCompleted(1);
        Date final_time = new Date();
        icisAppointmentRecord.setFinalTime(final_time);
        int completeState = this.icisAppointmentRecordServiceI.completeAppointment(icisAppointmentRecord);
        //判断预约记录中的修改状态是否成功
        if (completeState == 0) {
            return "预约完成失败";
        } else {
            //获取预约记录中的预约记录信息
            IcisAppointmentRecord icisAppointmentRecord1 = this.icisAppointmentRecordServiceI.selectByPrimaryKey(icisAppointmentRecord.getId());
            //获取预约记录中的社工id
            Long workerId = icisAppointmentRecord1.getWorkerId();
            //修改社工工作状态
            IcisWorker icisWorker = new IcisWorker();
            icisWorker.setId(workerId);
            icisWorker.setState(0);
            int updateWorkState = this.icisWorkerServiceI.updateIcisWorkState(icisWorker);
            //判断社工工作状态是否修改成功
            if (updateWorkState == 0) {
                return "预约完成失败";
            } else {
                return "预约完成";
            }
        }
    }

    /**
     * 评价社工服务
     * 请求：/icisAppointmentRecord/commentAppointment.html
     * 请求类型：POST
     * @param icisAppointmentRecord
     *        其中需要传入的参数有：
     *        id（预约记录id）、workerId（社工id）、serviceGrade（等级评价）、serviceComment（评价文字内容）
     * @return 评价是否完成
     *         成功返回"评价成功"
     *         失败返回"评价失败"
     */
    @RequestMapping(value = "commentAppointment", method = RequestMethod.POST)
    @ResponseBody
    public String commentAppointment(IcisAppointmentRecord icisAppointmentRecord){
        int commentState = this.icisAppointmentRecordServiceI.commentAppointment(icisAppointmentRecord);
        if (commentState == 1) {
            //定义初始化总分
            double sum = 0;
            //定义初始化记录数
            int n = 0;
            icisAppointmentRecord.setIsCompleted(1);
            //从数据库中读取预约记录列表
            List<IcisAppointmentRecord> icisAppointmentRecords = this.icisAppointmentRecordServiceI.selectAvgAppointmentGrade(icisAppointmentRecord);
            //循环遍历预约记录列表
            for (IcisAppointmentRecord icisAppointmentRecordItem : icisAppointmentRecords) {
                //计算总分
                sum += (double)icisAppointmentRecordItem.getServiceGrade();
                //计算总记录数
                n++;
            }
            //计算平均分
            double ave = sum/n;
            //格式化平均分
            BigDecimal b = new BigDecimal(ave);
            double average = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            //建立新的预约项目对象
            IcisAppointmentItem icisAppointmentItem = new IcisAppointmentItem();
            icisAppointmentItem.setWorkerId(icisAppointmentRecord.getWorkerId());
            icisAppointmentItem.setGrade(average);
            //更新数据库评分
            int updateGradeState = this.icisAppointmentItemServiceI.updateAppointmentGrade(icisAppointmentItem);
            //判断数据库更新是否成功
            if (updateGradeState == 0) {
                return "评价成功，但更新评分失败";
            } else {
                return "评价成功，更新评分也成功";
            }
        } else {
            return "评价失败";
        }
    }

    /**
     * 预约指定项目
     * @param icisResidentId 此参数是用户id
     * @param icisAppointmentItemId 此参数是预约项目id
     * @return 预约是否成功
     */
    @RequestMapping(value = "selfAppointment", method = RequestMethod.POST)
    @ResponseBody
    public String selfAppointment(Long icisResidentId, Long icisAppointmentItemId) {
        //筛选对应id的预约项目
        IcisAppointmentItem icisAppointmentItem = this.icisAppointmentItemServiceI.selectIcisAppointmentItemByPrimaryKey(icisAppointmentItemId);
        IcisWorker icisWorker = this.icisWorkerServiceI.selectIcisWorkerByPrimaryKey(icisAppointmentItem.getWorkerId());
        //修改社工的工作状态为忙碌
        icisWorker.setState(1);
        this.icisWorkerServiceI.updateByPrimaryKey(icisWorker);
        //建立一个预约记录对象
        IcisAppointmentRecord icisAppointmentRecord = new IcisAppointmentRecord();
        icisAppointmentRecord.setIsCompleted(0);
        //开始时间
        Date create_time = new Date();
        icisAppointmentRecord.setCreateTime(create_time);
        icisAppointmentRecord.setResidentId(icisResidentId);
        icisAppointmentRecord.setIsApproved(icisAppointmentItem.getIsApproved());
        icisAppointmentRecord.setCompany(icisAppointmentItem.getCompany());
        icisAppointmentRecord.setWorkerId(icisWorker.getId());
        icisAppointmentRecord.setServiceContent(icisAppointmentItem.getServiceContent());
        icisAppointmentRecord.setServicePhoto(icisAppointmentItem.getServicePhoto());
        //判断是否预约成功
        int selfAppointmentState = this.icisAppointmentRecordServiceI.insert(icisAppointmentRecord);
        if (selfAppointmentState == 0) {
            return "预约失败";
        } else {
            return "预约成功";
        }
    }

    /**
     * 自我预约完成功能
     * @param icisAppointmentRecordId 此参数是预约id
     * @return 完成是否成功
     *         成功返回"预约完成成功"
     *         失败返回"预约完成失败"
     */
    @RequestMapping(value = "selfCompleteAppointment", method = RequestMethod.POST)
    @ResponseBody
    public String selfCompleteAppointment(Long icisAppointmentRecordId){
        //筛选出对应的预约记录对象
        IcisAppointmentRecord icisAppointmentRecord = this.icisAppointmentRecordServiceI.selectByPrimaryKey(icisAppointmentRecordId);
        //设置预约记录的完成状态并设置完成时间
        icisAppointmentRecord.setIsCompleted(1);
        Date final_time = new Date();
        icisAppointmentRecord.setFinalTime(final_time);
        //修改数据库
        int completeState = this.icisAppointmentRecordServiceI.updateByPrimaryKey(icisAppointmentRecord);
        //筛选对应id的社工账号
        Long icisWorkerId = icisAppointmentRecord.getWorkerId();
        IcisWorker icisWorker = this.icisWorkerServiceI.selectByPrimaryKey(icisWorkerId);
        icisWorker.setState(0);
        //修改数据库
        int workState = this.icisWorkerServiceI.updateIcisWorkState(icisWorker);
        //判断预约完成是否成功
        if (completeState == 1 && workState == 1) {
            return "预约完成成功";
        } else {
            return "预约完成失败";
        }
    }

    /**
     * 自我预约评价
     * @param icisAppointmentRecord
     *        其中需要传入的参数有：
     *        id（预约记录id）、workerId（社工id）、serviceGrade（等级评价）、serviceComment（评价文字内容）
     * @return 是否评价成功
     *         成功返回"预约评价成功"
     *         失败返回"预约评价失败"
     */
    @RequestMapping(value = "selfCommentAppointment", method = RequestMethod.POST)
    @ResponseBody
    public String selfCommentAppointment(IcisAppointmentRecord icisAppointmentRecord) {
        int commentState = this.icisAppointmentRecordServiceI.commentAppointment(icisAppointmentRecord);
        if (commentState == 0) {
            return "预约评价失败";
        } else {
            //定义初始化总分
            double sum = 0;
            //定义初始化记录数
            int n = 0;
            icisAppointmentRecord.setIsCompleted(1);
            //从数据库中读取预约记录列表
            List<IcisAppointmentRecord> icisAppointmentRecords = this.icisAppointmentRecordServiceI.selectAvgAppointmentGrade(icisAppointmentRecord);
            //循环遍历预约记录列表
            for (IcisAppointmentRecord icisAppointmentRecordItem : icisAppointmentRecords) {
                //计算总分
                sum += (double)icisAppointmentRecordItem.getServiceGrade();
                //计算总记录数
                n++;
            }
            //计算平均分
            double ave = sum/n;
            //格式化平均分
            BigDecimal b = new BigDecimal(ave);
            double average = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            //建立新的预约项目对象
            IcisAppointmentItem icisAppointmentItem = new IcisAppointmentItem();
            icisAppointmentItem.setWorkerId(icisAppointmentRecord.getWorkerId());
            icisAppointmentItem.setGrade(average);
            //更新数据库评分
            int updateGradeState = this.icisAppointmentItemServiceI.updateAppointmentGrade(icisAppointmentItem);
            //判断数据库更新是否成功
            if (updateGradeState == 0) {
                return "评价成功，但更新评分失败";
            } else {
                return "评价成功，更新评分也成功";
            }
        }
    }

    /**
     * 获取所有预约项目
     * 请求：/icisAppointmentRecord/selectAllIcisAppointmentRecord.html
     * 请求类型：POST
     * @return 预约项目列表
     */
    @RequestMapping(value = "selectAllIcisAppointmentRecord", method = RequestMethod.POST)
    @ResponseBody
    public List<com.icis.backend.model.IcisAppointmentRecord> selectAllIcisAppointmentRecord() {
        List<com.icis.backend.model.IcisAppointmentRecord> icisAppointmentRecordModel = new ArrayList<com.icis.backend.model.IcisAppointmentRecord>();
        List<IcisAppointmentRecord> icisAppointmentRecords = this.icisAppointmentRecordServiceI.selectAllAppointmentRecord();
        for (IcisAppointmentRecord icisAppointmentRecord:icisAppointmentRecords) {
            com.icis.backend.model.IcisAppointmentRecord icisAppointmentRecordItem = new com.icis.backend.model.IcisAppointmentRecord();
            icisAppointmentRecordItem.setId(icisAppointmentRecord.getId());
            icisAppointmentRecordItem.setCompany(icisAppointmentRecord.getCompany());
            icisAppointmentRecordItem.setCreateTime(icisAppointmentRecord.getCreateTime());
            icisAppointmentRecordItem.setFinalTime(icisAppointmentRecord.getFinalTime());
            icisAppointmentRecordItem.setIsApproved(icisAppointmentRecord.getIsApproved());
            icisAppointmentRecordItem.setIsCompleted(icisAppointmentRecord.getIsCompleted());
            icisAppointmentRecordItem.setResidentId(icisAppointmentRecord.getResidentId());
            icisAppointmentRecordItem.setServiceComment(icisAppointmentRecord.getServiceComment());
            icisAppointmentRecordItem.setServiceContent(icisAppointmentRecord.getServiceContent());
            icisAppointmentRecordItem.setServiceGrade(icisAppointmentRecord.getServiceGrade());
            icisAppointmentRecordItem.setWorkerId(icisAppointmentRecord.getWorkerId());
            icisAppointmentRecordItem.setServicePhoto("/icisAppointmentRecord/getPhoto.html?filePath=" + icisAppointmentRecord.getServicePhoto());

            IcisResident icisResident = this.icisResidentServiceI.selectIcisResidentByIcisResidentId(icisAppointmentRecord.getResidentId());
            if (icisResident.getHeadPhoto() == null) {
                icisResident.setHeadPhoto(null);
            } else {
                icisResident.setHeadPhoto("/icisAppointmentRecord/getPhoto.html?filePath=" + icisResident.getHeadPhoto());
            }
            icisAppointmentRecordItem.setIcisResident(icisResident);

            IcisWorker icisWorker = this.icisWorkerServiceI.selectIcisWorkerByIcisWorkerId(icisAppointmentRecord.getWorkerId());
            if (icisWorker.getHeadPhoto() == null) {
                icisWorker.setHeadPhoto(null);
            } else {
                icisWorker.setHeadPhoto("/icisAppointmentRecord/getPhoto.html?filePath=" + icisWorker.getHeadPhoto());
            }
            icisAppointmentRecordItem.setIcisWorker(icisWorker);
            icisAppointmentRecordModel.add(icisAppointmentRecordItem);
        }
        return icisAppointmentRecordModel;
    }

    /**
     * 获取我的预约项目
     * 请求：/icisAppointmentRecord/selectMyIcisAppointmentRecord.html
     * 请求类型：POST
     * @param id 此id为社区用户id
     * @return 我的预约列表
     */
    @RequestMapping(value = "selectMyIcisAppointmentRecord", method = RequestMethod.POST)
    @ResponseBody
    public List<com.icis.backend.model.IcisAppointmentRecord> selectMyIcisAppointmentRecord(Long id) {
        List<com.icis.backend.model.IcisAppointmentRecord> icisAppointmentRecordModel = new ArrayList<com.icis.backend.model.IcisAppointmentRecord>();
        List<IcisAppointmentRecord> icisAppointmentRecords = this.icisAppointmentRecordServiceI.selectMyAppointmentRecord(id);
        for (IcisAppointmentRecord icisAppointmentRecord:icisAppointmentRecords) {
            com.icis.backend.model.IcisAppointmentRecord icisAppointmentRecordItem = new com.icis.backend.model.IcisAppointmentRecord();
            icisAppointmentRecordItem.setId(icisAppointmentRecord.getId());
            icisAppointmentRecordItem.setCompany(icisAppointmentRecord.getCompany());
            icisAppointmentRecordItem.setCreateTime(icisAppointmentRecord.getCreateTime());
            icisAppointmentRecordItem.setFinalTime(icisAppointmentRecord.getFinalTime());
            icisAppointmentRecordItem.setIsApproved(icisAppointmentRecord.getIsApproved());
            icisAppointmentRecordItem.setIsCompleted(icisAppointmentRecord.getIsCompleted());
            icisAppointmentRecordItem.setResidentId(icisAppointmentRecord.getResidentId());
            icisAppointmentRecordItem.setServiceComment(icisAppointmentRecord.getServiceComment());
            icisAppointmentRecordItem.setServiceContent(icisAppointmentRecord.getServiceContent());
            icisAppointmentRecordItem.setServiceGrade(icisAppointmentRecord.getServiceGrade());
            icisAppointmentRecordItem.setWorkerId(icisAppointmentRecord.getWorkerId());
            icisAppointmentRecordItem.setServicePhoto("/icisAppointmentRecord/getPhoto.html?filePath=" + icisAppointmentRecord.getServicePhoto());

            IcisResident icisResident = this.icisResidentServiceI.selectIcisResidentByIcisResidentId(icisAppointmentRecord.getResidentId());
            if (icisResident.getHeadPhoto() == null) {
                icisResident.setHeadPhoto(null);
            } else {
                icisResident.setHeadPhoto("/icisAppointmentRecord/getPhoto.html?filePath=" + icisResident.getHeadPhoto());
            }
            icisAppointmentRecordItem.setIcisResident(icisResident);

            IcisWorker icisWorker = this.icisWorkerServiceI.selectIcisWorkerByIcisWorkerId(icisAppointmentRecord.getWorkerId());
            if (icisWorker.getHeadPhoto() == null) {
                icisWorker.setHeadPhoto(null);
            } else {
                icisWorker.setHeadPhoto("/icisAppointmentRecord/getPhoto.html?filePath=" + icisWorker.getHeadPhoto());
            }
            icisAppointmentRecordItem.setIcisWorker(icisWorker);
            icisAppointmentRecordModel.add(icisAppointmentRecordItem);
        }
        return icisAppointmentRecordModel;
    }

    /**
     * 设置链接显示服务内容图片
     * 请求类型：GET
     * 请求：/icisResident/getPhoto.html
     * @param response
     * @param filePath
     *        此filePath为图片目录
     * @throws Exception
     */
    @RequestMapping(value = "getPhoto", method = RequestMethod.GET)
    public void getPhoto(HttpServletResponse response, String filePath) throws Exception {
        OutputStream outputStream = response.getOutputStream();
        File file = new File(filePath);
        FileInputStream fips = new FileInputStream(file);
        byte[] byImg = readStream(fips);
        outputStream.write(byImg);
        outputStream.flush();
    }

    /**
     * 以流形式返回用户头像
     * @param inputStream
     * @return 流形式返回用户头像
     */
    public byte[] readStream(InputStream inputStream){
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        int data = -1;
        try {
            while ((data = inputStream.read()) != -1) {
                bops.write(data);
            }
            return bops.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
