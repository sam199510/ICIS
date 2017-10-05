package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisFeedbackMapper;
import com.icis.backend.entity.IcisFeedback;
import com.icis.backend.service.IcisFeedbackServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IcisFeedbackServiceImpl implements IcisFeedbackServiceI {
    @Autowired
    private IcisFeedbackMapper icisFeedbackMapper;

    public void setIcisFeedbackMapper(IcisFeedbackMapper icisFeedbackMapper) {
        this.icisFeedbackMapper = icisFeedbackMapper;
    }

    @Override
    public int insert(IcisFeedback record) {
        return this.icisFeedbackMapper.insert(record);
    }

    @Override
    public List<IcisFeedback> selectAllFeedbackRecord() {
        return this.icisFeedbackMapper.selectAllFeedbackRecord();
    }
}
