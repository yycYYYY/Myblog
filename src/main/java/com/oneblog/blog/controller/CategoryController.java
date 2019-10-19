package com.oneblog.blog.controller;

import com.oneblog.blog.service.CategoryService;
import com.oneblog.blog.tools.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    private static final Logger logger=LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @PostMapping(value = "/newTag")
    public Msg newTag(@RequestParam(value = "tagId")Integer tagId,
                      @RequestParam(value = "level") Integer level,
                      @RequestParam(value = "name") String name){
            categoryService.newTag(tagId, level, name);
            Msg msg=Msg.success();
            return msg;
    }

    @GetMapping(value = "/deleteTag")
    public void deleteTag(@RequestParam(value = "tagId") Integer tagId){
        categoryService.deleteTag(tagId);
    }

    @GetMapping(value = "/getAllTags")
    public void getAllTags(){

    }

    @GetMapping(value = "/getArticlesByTag")
    public void getArticlesdByTag(){}

}
