package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisRepairsMapper;
import com.icis.backend.entity.IcisRepairs;
import com.icis.backend.service.IcisRepairsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IcisRepairsServiceImpl implements IcisRepairsServiceI {
    @Autowired
    private IcisRepairsMapper icisRepairsMapper;

    public void setIcisRepairsMapper(IcisRepairsMapper icisRepairsMapper) {
        this.icisRepairsMapper = icisRepairsMapper;
    }

    @Override
    public int insert(IcisRepairs record) {
        return this.icisRepairsMapper.insert(record);
    }

    @Override
    public List<IcisRepairs> selectMyIcisRepairs(Long residentId) {
        return this.icisRepairsMapper.selectMyIcisRepairs(residentId);
    }

    @Override
    public int completeRepairs(IcisRepairs icisRepairs) {
        return this.icisRepairsMapper.updateByPrimaryKeySelective(icisRepairs);
    }
}
