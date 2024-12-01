package com.youlai.boot.modules.essay.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.modules.essay.model.EssayComment;
import com.youlai.boot.modules.essay.model.EssayPost;
import com.youlai.boot.modules.essay.service.impl.EssayPostServiceImpl3;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/api/essay/post")
public class EssayPostController {

    @Resource
    private EssayPostServiceImpl3 essayPostServiceImpl3;

    /**
     * 获取包含点赞的文章详情
     *
     * @param postId 文章ID
     * @return 文章详情
     */
    @GetMapping("/{postId}")
    public ResponseEntity<EssayPost> getEssayPostWithDetails(@PathVariable Long postId) {
        try {
            EssayPost post = essayPostServiceImpl3.getEssayPostWithDetails(postId);
            return ResponseEntity.ok(post);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * 获取文章的评论分页
     *
     * @param postId 文章ID
     * @param page   当前页，默认为 1
     * @param size   每页大小，默认为 10
     * @return 评论分页数据
     */
    @GetMapping("/{postId}/comments")
    public ResponseEntity<Page<EssayComment>> getCommentsWithPagination(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<EssayComment> comments = essayPostServiceImpl3.getCommentsWithPagination(postId, page, size);
            return ResponseEntity.ok(comments);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
