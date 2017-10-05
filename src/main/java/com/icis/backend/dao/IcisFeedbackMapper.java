package com.icis.backend.dao;

import com.icis.backend.entity.IcisFeedback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisFeedbackMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisFeedback record);

    int insertSelective(IcisFeedback record);

    IcisFeedback selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisFeedback record);

    int updateByPrimaryKey(IcisFeedback record);

    List<IcisFeedback> selectAllFeedbackRecord();
}