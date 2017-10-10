package com.icis.backend.dao;

import com.icis.backend.entity.IcisDynamicSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisDynamicSupportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisDynamicSupport record);

    int insertSelective(IcisDynamicSupport record);

    IcisDynamicSupport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisDynamicSupport record);

    int updateByPrimaryKey(IcisDynamicSupport record);

    int unsupportDynamic(IcisDynamicSupport icisDynamicSupport);

    List<IcisDynamicSupport> selectSupportorByDynamicId(Long dynamicId);

    List<IcisDynamicSupport> selectIsSupport(IcisDynamicSupport icisDynamicSupport);
}