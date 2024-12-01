package com.youlai.boot.modules.essay.controller;

import com.youlai.boot.modules.essay.service.EssayCommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/essay/comment")
public class EssayCommentController {

    @Resource
    private EssayCommentService essayCommentService;

}
