package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisAppointmentItemMapper;
import com.icis.backend.entity.IcisAppointmentItem;
import com.icis.backend.service.IcisAppointmentItemServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IcisAppointmentItemServiceImpl implements IcisAppointmentItemServiceI {

    @Autowired
    private IcisAppointmentItemMapper icisAppointmentItemMapper;

    public void setIcisAppointmentItemMapper(IcisAppointmentItemMapper icisAppointmentItemMapper) {
        this.icisAppointmentItemMapper = icisAppointmentItemMapper;
    }

    @Override
    public List<IcisAppointmentItem> selectAllIcisAppointmentItem() {
        return this.icisAppointmentItemMapper.selectAllIcisAppointmentItem();
    }

    @Override
    public IcisAppointmentItem selectIcisAppointmentItemByPrimaryKey(Long id) {
        return this.icisAppointmentItemMapper.selectIcisAppointmentItemByPrimaryKey(id);
    }

    @Override
    public int updateGrade(IcisAppointmentItem record) {
        return this.icisAppointmentItemMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateAppointmentGrade(IcisAppointmentItem icisAppointmentItem) {
        return this.icisAppointmentItemMapper.updateAppointmentGrade(icisAppointmentItem);
    }
}
