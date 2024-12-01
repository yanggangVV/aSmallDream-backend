package com.youlai.boot.modules.essay.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
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

    @TableId(type = IdType.AUTO)
    private Long id; // 文章唯一标识符

    private String title; // 文章标题

    private String content; // 文章内容

    private int authorId; // 作者ID，关联 sys_user 表中的 ID，用于获取作者信息

    private Timestamp createTime; // 文章创建时间

    private Timestamp updateTime; // 文章更新时间

    private String status; // 文章状态（DRAFT - 草稿，PUBLISHED - 已发布）

    private int tag; // 文章标签，使用 int 类型，映射为字典值

    @TableField(exist = false)
    private List<EssayComment> comments; // 非持久化字段，手动查询填充

    @TableField(exist = false)
    private List<EssayLike> likes; // 非持久化字段，手动查询填充
}
