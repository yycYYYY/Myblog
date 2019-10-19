package com.oneblog.blog.controller;

import com.oneblog.blog.tools.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

    @GetMapping(value = "/articles")
    public Msg get(){
        return null;
    }
}
