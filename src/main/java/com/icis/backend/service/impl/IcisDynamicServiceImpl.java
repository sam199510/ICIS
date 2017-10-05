package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisDynamicMapper;
import com.icis.backend.entity.IcisDynamic;
import com.icis.backend.service.IcisDynamicServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IcisDynamicServiceImpl implements IcisDynamicServiceI {
    @Autowired
    private IcisDynamicMapper icisDynamicMapper;

    public void setIcisDynamicMapper(IcisDynamicMapper icisDynamicMapper) {
        this.icisDynamicMapper = icisDynamicMapper;
    }

    @Override
    public int addIcisDynamic(IcisDynamic icisDynamic) {
        return this.icisDynamicMapper.insert(icisDynamic);
    }

    @Override
    public List<IcisDynamic> selectAllIcisDynamic() {
        return this.icisDynamicMapper.selectAllIcisDynamic();
    }
}
