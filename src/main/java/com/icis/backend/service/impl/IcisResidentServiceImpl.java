package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisResidentMapper;
import com.icis.backend.entity.IcisResident;
import com.icis.backend.service.IcisResidentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IcisResidentServiceImpl implements IcisResidentServiceI {
    @Autowired
    private IcisResidentMapper icisResidentMapper;

    public void setIcisResidentMapper(IcisResidentMapper icisResidentMapper) {
        this.icisResidentMapper = icisResidentMapper;
    }

    @Override
    public List<IcisResident> selectAllResident() {
        return this.icisResidentMapper.selectAllResident();
    }

    @Override
    public List<IcisResident> isExistAssignUser(IcisResident icisResident) {
        return this.icisResidentMapper.isExistAssignUser(icisResident);
    }

    @Override
    public List<IcisResident> selectByUsernameAndPassword(IcisResident icisResident) {
        return this.icisResidentMapper.selectByUsernameAndPassword(icisResident);
    }

    @Override
    public int updateByPrimaryKey(IcisResident record) {
        return this.icisResidentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insert(IcisResident record) {
        return this.icisResidentMapper.insert(record);
    }

    @Override
    public IcisResident selectByPrimaryKey(Long id) {
        return this.icisResidentMapper.selectByPrimaryKey(id);
    }

    @Override
    public IcisResident selectIcisResidentByUsername(String username) {
        return this.icisResidentMapper.selectIcisResidentByUsername(username);
    }
}
