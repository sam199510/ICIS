package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisSignInMapper;
import com.icis.backend.entity.IcisSignIn;
import com.icis.backend.service.IcisSignInServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class IcisSignInServiceImpl implements IcisSignInServiceI {
    @Autowired
    private IcisSignInMapper icisSignInMapper;

    public void setIcisSignInMapper(IcisSignInMapper icisSignInMapper) {
        this.icisSignInMapper = icisSignInMapper;
    }

    @Override
    public int signIn(IcisSignIn record) {
        return this.icisSignInMapper.insert(record);
    }
}
