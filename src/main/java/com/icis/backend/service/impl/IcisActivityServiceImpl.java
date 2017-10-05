package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisActivityMapper;
import com.icis.backend.entity.IcisActivity;
import com.icis.backend.service.IcisActivityServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IcisActivityServiceImpl implements IcisActivityServiceI {
    @Autowired
    private IcisActivityMapper icisActivityMapper;

    public void setIcisActivityMapper(IcisActivityMapper icisActivityMapper) {
        this.icisActivityMapper = icisActivityMapper;
    }

    @Override
    public List<IcisActivity> seleceAllIcisActivity() {
        return this.icisActivityMapper.seleceAllIcisActivity();
    }

    @Override
    public int publishActivity(IcisActivity icisActivity) {
        return this.icisActivityMapper.insert(icisActivity);
    }
}
