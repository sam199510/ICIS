package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisContactsMapper;
import com.icis.backend.entity.IcisContacts;
import com.icis.backend.service.IcisContactsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IcisContactsServiceImpl implements IcisContactsServiceI {
    @Autowired
    private IcisContactsMapper icisContactsMapper;

    public void setIcisContactsMapper(IcisContactsMapper icisContactsMapper) {
        this.icisContactsMapper = icisContactsMapper;
    }

    @Override
    public int addContact(IcisContacts record) {
        return this.icisContactsMapper.insert(record);
    }

    @Override
    public List<IcisContacts> isExistIcisContact(IcisContacts icisContacts) {
        return this.icisContactsMapper.isExistIcisContact(icisContacts);
    }

    @Override
    public List<IcisContacts> selectAllIcisContacts() {
        return this.icisContactsMapper.selectAllIcisContacts();
    }

    @Override
    public int deleteContact(Long id) {
        return this.icisContactsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateContact(IcisContacts icisContacts) {
        return this.icisContactsMapper.updateByPrimaryKey(icisContacts);
    }
}
