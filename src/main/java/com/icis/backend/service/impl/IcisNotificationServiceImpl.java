package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisNotificationMapper;
import com.icis.backend.entity.IcisNotification;
import com.icis.backend.service.IcisNotificationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IcisNotificationServiceImpl implements IcisNotificationServiceI {
    @Autowired
    private IcisNotificationMapper icisNotificationMapper;

    public void setIcisNotificationMapper(IcisNotificationMapper icisNotificationMapper) {
        this.icisNotificationMapper = icisNotificationMapper;
    }

    @Override
    public List<IcisNotification> selectAllNotification() {
        return this.icisNotificationMapper.selectAllNotification();
    }

    @Override
    public int publishNotification(IcisNotification icisNotification) {
        return this.icisNotificationMapper.insert(icisNotification);
    }

    @Override
    public int modifyNotification(IcisNotification icisNotification) {
        return this.icisNotificationMapper.updateByPrimaryKeySelective(icisNotification);
    }
}
