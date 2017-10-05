package com.icis.backend.dao;

import com.icis.backend.entity.IcisOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisOrder record);

    int insertSelective(IcisOrder record);

    IcisOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisOrder record);

    int updateByPrimaryKey(IcisOrder record);

    List<IcisOrder> selectIcisOrderByIcisOrderId(Long payorId);

    int completeIcisOrder(IcisOrder icisOrder);
}