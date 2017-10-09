package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisWorkerMapper;
import com.icis.backend.entity.IcisWorker;
import com.icis.backend.service.IcisWorkerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IcisWorkerServiceImpl implements IcisWorkerServiceI {
    @Autowired
    private IcisWorkerMapper icisWorkerMapper;

    public void setIcisWorkerMapper(IcisWorkerMapper icisWorkerMapper) {
        this.icisWorkerMapper = icisWorkerMapper;
    }

    @Override
    public IcisWorker selectByPrimaryKey(Long id) {
        return this.icisWorkerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<IcisWorker> selectWorkerFree(IcisWorker icisWorker) {
        return this.icisWorkerMapper.selectWorkerFree(icisWorker);
    }

    @Override
    public int updateByPrimaryKey(IcisWorker record) {
        return this.icisWorkerMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateIcisWorkState(IcisWorker icisWorker) {
        return this.icisWorkerMapper.updateIcisWorkState(icisWorker);
    }

    @Override
    public IcisWorker selectIcisWorkerByPrimaryKey(Long id) {
        return this.icisWorkerMapper.selectIcisWorkerByPrimaryKey(id);
    }

    @Override
    public List<IcisWorker> isExistIcisWorker(IcisWorker icisWorker) {
        return this.icisWorkerMapper.isExistIcisWorker(icisWorker);
    }

    @Override
    public List<IcisWorker> selectWorkerByUsernameAndPassword(IcisWorker icisWorker) {
        return this.icisWorkerMapper.selectWorkerByUsernameAndPassword(icisWorker);
    }

    @Override
    public IcisWorker selectIcisWorkerByIcisWorkerId(Long id) {
        return this.icisWorkerMapper.selectIcisWorkerByIcisWorkerId(id);
    }
}
