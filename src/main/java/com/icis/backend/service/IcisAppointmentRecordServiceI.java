package com.icis.backend.service;

import com.icis.backend.entity.IcisAppointmentRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisAppointmentRecordServiceI {
    /**
     * 预约结果插入记录表中
     * @param record
     * @return 预约记录插入结果
     */
    public int insert(IcisAppointmentRecord record);

    /**
     * 按照预约记录的主键修改完成状态和结束时间
     * @param record
     * @return 是否修改成功
     */
    public int updateByPrimaryKey(IcisAppointmentRecord record);

    /**
     * 按照预约记录的主键修改完成状态和结束时间
     * @param record
     * @return 是否修改成功
     */
    public int completeAppointment(IcisAppointmentRecord record);

    /**
     * 按主键查询预约记录
     * @param id
     * @return 预约记录对象
     */
    public IcisAppointmentRecord selectByPrimaryKey(Long id);

    /**
     * 评价订单
     * @param icisAppointmentRecord
     * @return 评价订单结果
     */
    public int commentAppointment(IcisAppointmentRecord icisAppointmentRecord);

    /**
     * 获取所有预约记录
     * @return 预约记录列表
     */
    public List<IcisAppointmentRecord> selectAllAppointmentRecord();

    /**
     * 获取我的预约记录
     * @param residentId
     * @return 我的预约记录列表
     */
    public List<IcisAppointmentRecord> selectMyAppointmentRecord(Long residentId);

    /**
     * 获取预约记录中的平均分
     * @param icisAppointmentRecord
     * @return 平均分
     */
    public List<IcisAppointmentRecord> selectAvgAppointmentGrade(IcisAppointmentRecord icisAppointmentRecord);

    /**
     * 按工号和用户号选择正在预约中的项目
     * @param icisAppointmentRecord
     * @return 预约项目记录列表
     */
    public List<IcisAppointmentRecord> selectByIcisResidentIdAndIcisWorkerId(IcisAppointmentRecord icisAppointmentRecord);

    /**
     * 删除预约记录
     * @param id
     * @return 删除是否成功
     */
    public int deleteByPrimaryKey(Long id);
}
