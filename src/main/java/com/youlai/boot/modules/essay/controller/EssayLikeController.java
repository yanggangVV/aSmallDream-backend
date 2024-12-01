package com.youlai.boot.modules.essay.controller;


import com.youlai.boot.modules.essay.service.EssayLikeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/essay/like")
public class EssayLikeController {

    @Resource
    private EssayLikeService essayLikeService;

}
