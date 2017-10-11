package com.icis.backend.service;

import com.icis.backend.entity.IcisActivity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisActivityServiceI {
    /**
     * 获取活动列表
     * @return 活动列表
     */
    public List<IcisActivity> seleceAllIcisActivity();

    /**
     * 发布活动
     * @param icisActivity
     * @return 发布活动是否成功
     */
    public int publishActivity(IcisActivity icisActivity);

    public IcisActivity selectUnderwayIcisActivity(Integer state);

    public int updateByPrimaryKeySelective(IcisActivity record);
}
