package com.icis.backend.dao;

import com.icis.backend.entity.IcisNotification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisNotificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisNotification record);

    int insertSelective(IcisNotification record);

    IcisNotification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisNotification record);

    int updateByPrimaryKey(IcisNotification record);

    List<IcisNotification> selectAllNotification();
}