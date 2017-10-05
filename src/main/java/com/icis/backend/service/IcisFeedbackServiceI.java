package com.icis.backend.service;

import com.icis.backend.entity.IcisFeedback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisFeedbackServiceI {
    /**
     * 意见反馈添加
     * @param record
     * @return 反馈是否成功
     */
    public int insert(IcisFeedback record);

    /**
     * 获取所有意见反馈
     * @return 意见反馈列表
     */
    public List<IcisFeedback> selectAllFeedbackRecord();

}
