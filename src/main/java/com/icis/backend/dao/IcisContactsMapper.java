package com.icis.backend.dao;

import com.icis.backend.entity.IcisContacts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisContactsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisContacts record);

    int insertSelective(IcisContacts record);

    IcisContacts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisContacts record);

    int updateByPrimaryKey(IcisContacts record);

    List<IcisContacts> isExistIcisContact(IcisContacts icisContacts);

    List<IcisContacts> selectAllIcisContacts();
}