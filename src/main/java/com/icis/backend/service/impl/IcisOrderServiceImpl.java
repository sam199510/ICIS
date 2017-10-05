package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisOrderMapper;
import com.icis.backend.entity.IcisOrder;
import com.icis.backend.service.IcisOrderServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IcisOrderServiceImpl implements IcisOrderServiceI {
    @Autowired
    private IcisOrderMapper icisOrderMapper;

    public void setIcisOrderMapper(IcisOrderMapper icisOrderMapper) {
        this.icisOrderMapper = icisOrderMapper;
    }

    @Override
    public int addIcisOrder(IcisOrder icisOrder) {
        return this.icisOrderMapper.insert(icisOrder);
    }

    @Override
    public List<IcisOrder> selectIcisOrderByIcisResidentId(Long payorId) {
        return this.icisOrderMapper.selectIcisOrderByIcisOrderId(payorId);
    }

    @Override
    public int completeIcisOrder(IcisOrder icisOrder) {
        return this.icisOrderMapper.completeIcisOrder(icisOrder);
    }
}
