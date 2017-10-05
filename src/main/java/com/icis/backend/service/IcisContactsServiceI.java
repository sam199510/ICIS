package com.icis.backend.service;

import com.icis.backend.entity.IcisContacts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisContactsServiceI {
    /**
     * 向通讯录表中添加联系人
     * @param record
     * @return 添加联系人是否成功
     */
    public int addContact(IcisContacts record);

    /**
     * 判断联系人是否已存在
     * @param icisContacts
     * @return 联系人结果列表
     */
    public List<IcisContacts> isExistIcisContact(IcisContacts icisContacts);

    /**
     * 查询所有联系人
     * @return 联系人列表
     */
    public List<IcisContacts> selectAllIcisContacts();

    /**
     * 按id删除联系人
     * @param id
     * @return 联系人删除是否成功
     */
    public int deleteContact(Long id);

    /**
     * 更新联系人信息
     * @param icisContacts
     * @return 更新联系人信息是否成功
     */
    public int updateContact(IcisContacts icisContacts);
}
