package com.icis.backend.service.impl;

import com.icis.backend.dao.IcisDynamicCommentMapper;
import com.icis.backend.entity.IcisDynamicComment;
import com.icis.backend.service.IcisDynamicCommentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IcisDynamicCommentServiceImpl implements IcisDynamicCommentServiceI {
    @Autowired
    private IcisDynamicCommentMapper icisDynamicCommentMapper;

    public void setIcisDynamicCommentMapper(IcisDynamicCommentMapper icisDynamicCommentMapper) {
        this.icisDynamicCommentMapper = icisDynamicCommentMapper;
    }

    @Override
    public int commentIcisDynamic(IcisDynamicComment icisDynamicComment) {
        return this.icisDynamicCommentMapper.insert(icisDynamicComment);
    }

    @Override
    public List<IcisDynamicComment> selectDynamicCommentByDynamicId(Long dynamicId) {
        return this.icisDynamicCommentMapper.selectDynamicCommentByDynamicId(dynamicId);
    }
}
