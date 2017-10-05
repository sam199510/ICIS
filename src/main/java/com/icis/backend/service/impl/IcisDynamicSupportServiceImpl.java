package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisDynamicSupportMapper;
import com.icis.backend.entity.IcisDynamicSupport;
import com.icis.backend.service.IcisDynamicSupportServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IcisDynamicSupportServiceImpl implements IcisDynamicSupportServiceI {
    @Autowired
    private IcisDynamicSupportMapper icisDynamicSupportMapper;

    public void setIcisDynamicSupportMapper(IcisDynamicSupportMapper icisDynamicSupportMapper) {
        this.icisDynamicSupportMapper = icisDynamicSupportMapper;
    }

    @Override
    public int supportIcisDynamic(IcisDynamicSupport icisDynamicSupport) {
        return this.icisDynamicSupportMapper.insert(icisDynamicSupport);
    }

    @Override
    public int unsupportDynamic(IcisDynamicSupport icisDynamicSupport) {
        return this.icisDynamicSupportMapper.unsupportDynamic(icisDynamicSupport);
    }

    @Override
    public List<IcisDynamicSupport> selectSupportorByDynamicId(Long dynamicId) {
        return this.icisDynamicSupportMapper.selectSupportorByDynamicId(dynamicId);
    }
}
