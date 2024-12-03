package com.youlai.boot.modules.essay.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.annotation.RepeatSubmit;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.common.result.PageResult;
import com.youlai.boot.common.result.Result;
import com.youlai.boot.modules.essay.model.EssayPost;
import com.youlai.boot.modules.essay.service.EssayPostService;
import com.youlai.boot.system.model.form.UserForm;
import com.youlai.boot.system.model.query.UserPageQuery;
import com.youlai.boot.system.model.vo.LogPageVO;
import com.youlai.boot.system.model.vo.RolePageVO;
import com.youlai.boot.system.model.vo.UserPageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/essay/posts")
@Tag(name = "时光广场.文章接口")
@RequiredArgsConstructor
public class EssayPostController {

    @Resource
    private final EssayPostService essayPostService;
    @Operation(summary = "文章分页列表")
    @GetMapping("/page")
    @Log(value = "文章分页列表", module = LogModuleEnum.USER)
    public PageResult<EssayPost> getPostPage(
            EssayPost queryParams,  // 这里接收查询条件
            @RequestParam int pageNum,  // 单独获取分页参数
            @RequestParam int pageSize  // 单独获取分页参数
    ) {
        Page<EssayPost> result = essayPostService.getEssayPostPage(queryParams,pageNum,pageSize);
        return PageResult.success(result);
    }

    @Operation(summary = "新增/修改文章")
    @PostMapping()
    public Result<Void> saveOrUpdatePost(
            @RequestBody @Valid EssayPost essayPost) {
        boolean result = essayPostService.saveOrUpdate(essayPost);
        return Result.judge(result);
    }
    @Operation(summary = "文章表单数据")
    @GetMapping("/{id}")
    public Result<EssayPost> getPostForm(
            @Parameter(description = "文章ID") @PathVariable Long id
    ) {
        EssayPost formData = essayPostService.getById(id);
        return Result.success(formData);
    }
    @Operation(summary = "删除文章")
    @DeleteMapping("/{ids}")
    public Result<Void> deletePosts(
            @Parameter(description = "用户ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        // 将逗号分隔的字符串拆分成数组
        String[] idArray = ids.split(",");

        // 将数组中的每个id转换为Integer并放入List中
        List<Integer> idList = Arrays.stream(idArray)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        boolean result = essayPostService.removeBatchByIds(idList);
        return Result.judge(result);
    }
}
