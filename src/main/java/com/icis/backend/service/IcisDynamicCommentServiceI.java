package com.icis.backend.service;

import com.icis.backend.entity.IcisDynamicComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcisDynamicCommentServiceI {
    /**
     * 评论动态
     * @param icisDynamicComment
     * @return 评论动态是否成功
     */
    public int commentIcisDynamic(IcisDynamicComment icisDynamicComment);

    /**
     * 用动态id获取动态评论
     * @param dynamicId
     * @return 评论列表
     */
    public List<IcisDynamicComment> selectDynamicCommentByDynamicId(Long dynamicId);
}
