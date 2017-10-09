package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisAppointmentRecordMapper;
import com.icis.backend.entity.IcisAppointmentRecord;
import com.icis.backend.service.IcisAppointmentRecordServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IcisAppointmentRecordServiceImpl implements IcisAppointmentRecordServiceI {
    @Autowired
    private IcisAppointmentRecordMapper icisAppointmentRecordMapper;

    public void setIcisAppointmentRecordMapper(IcisAppointmentRecordMapper icisAppointmentRecordMapper) {
        this.icisAppointmentRecordMapper = icisAppointmentRecordMapper;
    }

    @Override
    public int insert(IcisAppointmentRecord record) {
        return this.icisAppointmentRecordMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(IcisAppointmentRecord record) {
        return this.icisAppointmentRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public int completeAppointment(IcisAppointmentRecord record) {
        return this.icisAppointmentRecordMapper.completeAppointment(record);
    }

    @Override
    public IcisAppointmentRecord selectByPrimaryKey(Long id) {
        return this.icisAppointmentRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int commentAppointment(IcisAppointmentRecord icisAppointmentRecord) {
        return this.icisAppointmentRecordMapper.commentAppointment(icisAppointmentRecord);
    }

    @Override
    public List<IcisAppointmentRecord> selectAllAppointmentRecord() {
        return this.icisAppointmentRecordMapper.selectAllAppointmentRecord();
    }

    @Override
    public List<IcisAppointmentRecord> selectMyAppointmentRecord(Long residentId) {
        return this.icisAppointmentRecordMapper.selectMyAppointmentRecord(residentId);
    }
}
