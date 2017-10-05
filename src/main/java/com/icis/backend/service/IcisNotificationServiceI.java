package com.icis.backend.service;

import com.icis.backend.entity.IcisNotification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisNotificationServiceI {
    /**
     * 显示所有通知
     * @return 所有通知
     */
    public List<IcisNotification> selectAllNotification();

    /**
     * 发布通知
     * @param icisNotification
     * @return 发布通知是否成功
     */
    public int publishNotification(IcisNotification icisNotification);
}
