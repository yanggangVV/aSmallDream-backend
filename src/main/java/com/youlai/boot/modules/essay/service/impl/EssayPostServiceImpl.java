package com.youlai.boot.modules.essay.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.modules.essay.mapper.EssayPostMapper;
import com.youlai.boot.modules.essay.model.EssayPost;
import com.youlai.boot.modules.essay.service.EssayPostService;
import com.youlai.boot.system.mapper.MenuMapper;
import com.youlai.boot.system.model.entity.Menu;
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

    @Override
    public Page<EssayPost> getEssayPostsWithDetails(Long postId, int page, int size) {
        return null;
    }
}
