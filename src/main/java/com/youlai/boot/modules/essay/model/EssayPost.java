package com.youlai.boot.modules.essay.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 时光广场-文章类
 *
 * @author hxx
 * @since 2024/10/10
 */
@TableName("essaypost")
@Getter
@Setter
public class EssayPost extends BaseEntity {

    private String title; // 文章标题
    // 修改 content 字段的列定义

    private String content; // 文章内容

    private Integer authorId; // 作者ID，关联 sys_user 表中的 ID，用于获取作者信息

    private Integer likesCount; // 文章点赞数量
    private Integer commentsCount; // 文章评论标签
    private Integer status; // 文章状态（0 - 草稿，1 - 已发布）

    private Integer tag; // 文章标签，使用 int 类型，映射为字典值

    @TableField(exist = false)
    private List<EssayComment> comments; // 非持久化字段，手动查询填充

    @TableField(exist = false)
    private List<EssayLike> likes; // 非持久化字段，手动查询填充
}
