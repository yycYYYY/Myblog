package com.oneblog.blog.controller;

import com.oneblog.blog.service.ArticleService;
import com.oneblog.blog.tools.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    ArticleService articleService;

    @PostMapping(value = "/newArticle")
    public void newArticle(){}

    @GetMapping(value = "/deleteArticleByid")
    public void deleteArticleById(){}

    @PostMapping(value = "/updateArticle")
    public void updateArticle(){}

    @GetMapping(value = "/articles")
    public Msg getAllArticles(){
        return null;
    }

    @GetMapping(value = "/articlesByTag")
    public void getArticlesByTag(){}
}
