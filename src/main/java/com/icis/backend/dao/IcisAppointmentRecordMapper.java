package com.icis.backend.dao;

import com.icis.backend.entity.IcisAppointmentRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface IcisAppointmentRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisAppointmentRecord record);

    int insertSelective(IcisAppointmentRecord record);

    IcisAppointmentRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisAppointmentRecord record);

    int updateByPrimaryKey(IcisAppointmentRecord record);

    int completeAppointment(IcisAppointmentRecord record);

    int commentAppointment(IcisAppointmentRecord icisAppointmentRecord);
}