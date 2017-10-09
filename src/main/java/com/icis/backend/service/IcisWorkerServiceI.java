package com.icis.backend.service;

import com.icis.backend.entity.IcisWorker;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisWorkerServiceI {
    /**
     * 按照主键搜索社工人员
     * @param id
     * @return 社工人员对象
     */
    public IcisWorker selectByPrimaryKey(Long id);

    /**
     * 筛选空闲社工人员
     * @param icisWorker
     * @return 社工人员列表
     */
    public List<IcisWorker> selectWorkerFree(IcisWorker icisWorker);

    /**
     * 更新社工人员状态
     * @param record
     * @return 是否更新成功
     */
    public int updateByPrimaryKey(IcisWorker record);

    /**
     * 更新员工工作状态
     * @param icisWorker
     * @return 是否更新成功
     */
    public int updateIcisWorkState(IcisWorker icisWorker);

    /**
     * 按主键筛选社工对象
     * @param id
     * @return 社工对象
     */
    public IcisWorker selectIcisWorkerByPrimaryKey(Long id);

    /**
     * 查找是否有该用户
     * @param icisWorker
     * @return 用户列表
     */
    public List<IcisWorker> isExistIcisWorker(IcisWorker icisWorker);

    /**
     * 查找用户
     * @param icisWorker
     * @return 用户对象
     */
    public List<IcisWorker> selectWorkerByUsernameAndPassword(IcisWorker icisWorker);

    public IcisWorker selectIcisWorkerByIcisWorkerId(Long id);
}
