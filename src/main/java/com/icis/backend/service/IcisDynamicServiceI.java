package com.icis.backend.service;

import com.icis.backend.entity.IcisDynamic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisDynamicServiceI {
    /**
     * 发表动态
     * @param icisDynamic
     * @return
     */
    public int addIcisDynamic(IcisDynamic icisDynamic);

    /**
     * 获取所有动态
     * @return 动态列表
     */
    public List<IcisDynamic> selectAllIcisDynamic();
}
