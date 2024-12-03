package com.youlai.boot.modules.essay.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.modules.essay.mapper.EssayPostMapper;
import com.youlai.boot.modules.essay.model.EssayPost;
import com.youlai.boot.modules.essay.service.EssayPostService;
import com.youlai.boot.system.mapper.MenuMapper;
import com.youlai.boot.system.model.bo.UserBO;
import com.youlai.boot.system.model.entity.Menu;
import com.youlai.boot.system.model.query.UserPageQuery;
import com.youlai.boot.system.model.vo.UserPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Service实现类 - 文章管理功能
 */
@Service
public class EssayPostServiceImpl extends ServiceImpl<EssayPostMapper, EssayPost> implements EssayPostService {


    @Autowired
    private EssayPostMapper essayPostMapper;
    @Override
    public Page<EssayPost> getEssayPostPage(EssayPost queryParams,int pageNum,int pageSize) {
        // 创建一个 Page 对象，并设置页码和每页大小
        Page<EssayPost> page = new Page<>(pageNum, pageSize);
        // 创建查询条件
        QueryWrapper<EssayPost> queryWrapper = new QueryWrapper<>();
        // 根据需要添加其他查询条件，例如按标题模糊查询
        if (queryParams.getTitle() != null) {
            queryWrapper.like("title", queryParams.getTitle());
        }
        // 可以根据其他字段添加查询条件，比如状态、标签等

        // 添加状态查询条件
        if (queryParams.getStatus() != null) {
            queryWrapper.eq("status", queryParams.getStatus());
        }
        // 日期模糊查询（匹配日期部分）
        if (queryParams.getCreateTime() != null) {
            // 使用 MySQL 的 DATE 函数，仅匹配日期部分
            queryWrapper.apply("DATE(create_time) = {0}", queryParams.getCreateTime().toLocalDate());
        }
        // 设置排序，按 createTime 降序排序（最新的文章显示在前面）
        queryWrapper.orderByDesc("create_time");  // 按 create_time 降序排列
        // 调用分页查询方法
        return essayPostMapper.selectPage(page, queryWrapper);
    }
    @Override
    public Page<EssayPost> getEssayPostsWithDetails(Long postId, int page, int size) {
        return null;
    }
}
