package com.icis.backend.service;

import com.icis.backend.entity.IcisResident;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisResidentServiceI {
    /**
     * 筛选所有社区居民
     * @return 社区居民列表
     */
    public List<IcisResident> selectAllResident();

    /**
     * 是否存在社区居民用户
     * @param icisResident
     * @return 社区居民用户列表
     */
    public List<IcisResident> isExistAssignUser(IcisResident icisResident);

    /**
     * 确认用户名和密码是否正确
     * @param icisResident
     * @return 用户列表
     */
    public List<IcisResident> selectByUsernameAndPassword(IcisResident icisResident);

    /**
     * 更新居民用户信息
     * @param record
     * @return 更新结果
     */
    public int updateByPrimaryKey(IcisResident record);

    /**
     * 添加社区居民用户信息
     * @param record
     * @return 是否添加成功
     */
    public int insert(IcisResident record);

    /**
     * 按主键筛选社区居民
     * @param id
     * @return 社区居民对象
     */
    public IcisResident selectByPrimaryKey(Long id);

    public IcisResident selectIcisResidentByUsername(String username);

    public IcisResident selectIcisResidentByIcisResidentId(Long id);
}
