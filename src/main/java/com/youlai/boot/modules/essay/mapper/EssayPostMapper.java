package com.youlai.boot.modules.essay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.common.annotation.DataPermission;
import com.youlai.boot.modules.essay.model.EssayComment;
import com.youlai.boot.modules.essay.model.EssayLike;
import com.youlai.boot.modules.essay.model.EssayPost;
import com.youlai.boot.system.model.bo.UserBO;
import com.youlai.boot.system.model.query.UserPageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EssayPostMapper extends BaseMapper<EssayPost> {

    /**
     * 分页查询文章基本信息
     *
     */
//    Page<EssayPost> getEssayPostPage(Page<EssayPost> page, @Param("queryParams") EssayPost queryParams);
//    Page<EssayPost> getEssayPostPage(EssayPost queryParams,int pageNum,int pageSize);
    /**
     * 分页查询文章的评论
     *
     * @param postId 文章ID
     * @param page   分页参数
     * @return 评论分页结果
     */
    @Select("SELECT * FROM essaycomment WHERE post_id = #{postId}")
    Page<EssayComment> findCommentsByPostIdWithPagination(@Param("postId") Long postId, Page<EssayComment> page);

    /**
     * 查询文章的点赞信息
     *
     * @param postId 文章ID
     * @return 点赞列表
     */
    @Select("SELECT * FROM essaylike WHERE post_id = #{postId}")
    List<EssayLike> findLikesByPostId(@Param("postId") Long postId);
}
