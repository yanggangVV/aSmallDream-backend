package com.youlai.boot.modules.essay.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.time.LocalDateTime;

/**
 * 会员实体-业务模块演示
 *
 * @author haoxr
 * @since 2024/10/10
 */
@TableName("essaycomment")
@Getter
@Setter
public class EssayComment {
    @TableId(type = IdType.AUTO)
    private Long id; // 评论唯一标识符

    private Long postId; // 评论关联的文章ID

    private Long userId; // 评论者ID，与用户表中的ID关联，用于获取评论者信息

    private String content; // 评论内容

    private LocalDateTime createTime; // 评论创建时间
}

