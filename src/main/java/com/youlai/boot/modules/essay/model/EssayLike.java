package com.youlai.boot.modules.essay.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

/**
 * 会员实体-业务模块演示
 *
 * @author haoxr
 * @since 2024/10/10
 */
@TableName("essaylike")
@Getter
@Setter
public class EssayLike {
    @TableId(type = IdType.AUTO)
    private Long id; // 点赞记录唯一标识符

    private Long postId; // 点赞关联的文章ID

    private Long userId; // 点赞者ID，与用户表中的ID关联，用于获取点赞者信息

    private Timestamp createTime; // 点赞时间
}

