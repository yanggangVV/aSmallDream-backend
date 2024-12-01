package com.youlai.boot.modules.essay.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.modules.essay.mapper.EssayPostMapper;
import com.youlai.boot.modules.essay.model.EssayComment;
import com.youlai.boot.modules.essay.model.EssayPost;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

@Service
@Transactional
public class EssayPostServiceImpl3 {

    @Resource
    private EssayPostMapper essayPostMapper;

    /**
     * 获取带有详情的文章
     *
     * @param postId 文章ID
     * @return 包含点赞信息的文章详情
     */
    public EssayPost getEssayPostWithDetails(Long postId) {
        EssayPost post = essayPostMapper.selectById(postId);
        if (post == null) {
            throw new IllegalArgumentException("文章不存在，ID: " + postId);
        }
        post.setLikes(essayPostMapper.findLikesByPostId(postId));
        return post;
    }

    /**
     * 获取文章的评论分页
     *
     * @param postId 文章ID
     * @param page   当前页
     * @param size   每页大小
     * @return 分页的评论数据
     */
    public Page<EssayComment> getCommentsWithPagination(Long postId, int page, int size) {
        if (essayPostMapper.selectById(postId) == null) {
            throw new IllegalArgumentException("文章不存在，ID: " + postId);
        }
        Page<EssayComment> commentPage = new Page<>(page, size);
        return essayPostMapper.findCommentsByPostIdWithPagination(postId, commentPage);
    }
}
