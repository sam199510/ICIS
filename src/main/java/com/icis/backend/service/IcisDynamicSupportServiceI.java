package com.icis.backend.service;

import com.icis.backend.entity.IcisDynamicSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisDynamicSupportServiceI {
    /**
     * 给动态点赞的方法
     * @param icisDynamicSupport
     * @return 给动态点赞是否成功
     */
    public int supportIcisDynamic(IcisDynamicSupport icisDynamicSupport);

    /**
     * 给动态取消点赞的方法
     * @param icisDynamicSupport
     * @return 取消点赞是否成功
     */
    public int unsupportDynamic(IcisDynamicSupport icisDynamicSupport);

    public List<IcisDynamicSupport> selectSupportorByDynamicId(Long dynamicId);
}
