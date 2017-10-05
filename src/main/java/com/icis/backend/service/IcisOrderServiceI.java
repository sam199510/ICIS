package com.icis.backend.service;

import com.icis.backend.entity.IcisOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisOrderServiceI {
    /**
     * 添加订单
     * @param icisOrder
     * @return 添加订单是否成功
     */
    public int addIcisOrder(IcisOrder icisOrder);

    /**
     * 按订单id查询订单
     * @param icisOrderId
     * @return 订单查询结果列表
     */
    public List<IcisOrder> selectIcisOrderByIcisResidentId(Long icisOrderId);

    /**
     * 完成订单，并完成支付方式和支付状态的增加和修改
     * @param icisOrder
     * @return 完成订单是否成功
     */
    public int completeIcisOrder(IcisOrder icisOrder);
}
