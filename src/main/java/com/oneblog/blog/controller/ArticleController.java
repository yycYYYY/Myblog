package com.oneblog.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oneblog.blog.entity.Blog;
import com.oneblog.blog.service.ArticleService;
import com.oneblog.blog.service.CategoryService;
import com.oneblog.blog.tools.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @PostMapping(value = "/newArticle")
    public void newArticle(){}

    @GetMapping(value = "/deleteArticleByid")
    public void deleteArticleById(){}

    @PostMapping(value = "/updateArticle    ")
    public void updateArticle(){}

    @GetMapping(value = "/articles")
    public Msg getAllArticles(@RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber){
        PageHelper.startPage(pageNumber,5);
        List<Blog> blogs = articleService.getAllArticles();

        PageInfo<Blog> pageInfo = new PageInfo<>(blogs,5);
        return null;
    }

    @GetMapping(value = "/articlesByTag")
    public void getArticlesByTag(){}
}
