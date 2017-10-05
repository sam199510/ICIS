package com.icis.backend.dao;

import com.icis.backend.entity.IcisDynamicComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisDynamicCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IcisDynamicComment record);

    int insertSelective(IcisDynamicComment record);

    IcisDynamicComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IcisDynamicComment record);

    int updateByPrimaryKey(IcisDynamicComment record);

    List<IcisDynamicComment> selectDynamicCommentByDynamicId(Long dynamicId);
}