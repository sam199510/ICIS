package com.icis.backend.service;

import com.icis.backend.entity.IcisAppointmentItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisAppointmentItemServiceI {
    /**
     * 筛选所有预约项目
     * @return 预约项目列表
     */
    public List<IcisAppointmentItem> selectAllIcisAppointmentItem();

    /**
     * 根据主键获取预约项目对象
     * @param id
     * @return 预约项目对象
     */
    public IcisAppointmentItem selectIcisAppointmentItemByPrimaryKey(Long id);
}
