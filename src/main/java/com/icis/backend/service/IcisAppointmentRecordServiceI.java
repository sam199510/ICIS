package com.icis.backend.service;

import com.icis.backend.entity.IcisAppointmentRecord;
import org.springframework.stereotype.Repository;

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
}
