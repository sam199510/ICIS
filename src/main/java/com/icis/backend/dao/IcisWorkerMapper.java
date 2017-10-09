package com.icis.backend.dao;

import com.icis.backend.entity.IcisWorker;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisWorkerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisWorker record);

    int insertSelective(IcisWorker record);

    IcisWorker selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisWorker record);

    int updateByPrimaryKey(IcisWorker record);

    List<IcisWorker> selectWorkerFree(IcisWorker icisWorker);

    int updateIcisWorkState(IcisWorker icisWorker);

    IcisWorker selectIcisWorkerByPrimaryKey(Long id);

    List<IcisWorker> isExistIcisWorker(IcisWorker icisWorker);

    List<IcisWorker> selectWorkerByUsernameAndPassword(IcisWorker icisWorker);

    IcisWorker selectIcisWorkerByIcisWorkerId(Long id);
}