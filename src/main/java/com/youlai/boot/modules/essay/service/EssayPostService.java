package com.youlai.boot.modules.essay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.modules.essay.model.EssayComment;
import com.youlai.boot.modules.essay.model.EssayPost;
import java.util.List;

/**
 * Service接口 - 提供文章的增删改查功能
 */
public interface EssayPostService extends IService<EssayPost> {

    /**
     * 获取带评论和点赞量的文章详细信息
     *
     * @param postId
     * @return
     */
    Page<EssayPost> getEssayPostsWithDetails(Long postId, int page, int size);
}
