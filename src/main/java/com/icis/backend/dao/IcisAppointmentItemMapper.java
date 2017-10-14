package com.icis.backend.dao;

import com.icis.backend.entity.IcisAppointmentItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisAppointmentItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisAppointmentItem record);

    int insertSelective(IcisAppointmentItem record);

    IcisAppointmentItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisAppointmentItem record);

    int updateByPrimaryKey(IcisAppointmentItem record);

    List<IcisAppointmentItem> selectAllIcisAppointmentItem();

    IcisAppointmentItem selectIcisAppointmentItemByPrimaryKey(Long id);

    int updateAppointmentGrade(IcisAppointmentItem icisAppointmentItem);

    int updateAppintmentItemState(IcisAppointmentItem icisAppointmentItem);
}