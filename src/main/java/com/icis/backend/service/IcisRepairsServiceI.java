package com.icis.backend.service;

import com.icis.backend.entity.IcisRepairs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisRepairsServiceI {
    /**
     * 添加报修记录
     * @param record
     * @return
     */
    public int insert(IcisRepairs record);

    /**
     * 筛选我的报修记录
     * @param residentId
     * @return 报修列表
     */
    public List<IcisRepairs> selectMyIcisRepairs(Long residentId);

    public int completeRepairs(IcisRepairs icisRepairs);
}
