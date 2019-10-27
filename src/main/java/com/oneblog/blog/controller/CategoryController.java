package com.oneblog.blog.controller;

import com.oneblog.blog.entity.Blog;
import com.oneblog.blog.entity.Category;
import com.oneblog.blog.model.vo.BaseResponseVO;
import com.oneblog.blog.service.CategoryService;
import com.oneblog.blog.tools.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CategoryController {

    private static final Logger logger=LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @PostMapping(value = "/newTag")
    public BaseResponseVO newTag(@RequestParam(value = "tagId")Integer tagId,
                      @RequestParam(value = "level") Integer level,
                      @RequestParam(value = "name") String name){
            categoryService.newTag(tagId, level, name);
            return BaseResponseVO.success(null);
    }

    @GetMapping(value = "/deleteTag")
    public BaseResponseVO deleteTag(@RequestParam(value = "tagId") Integer tagId){
        categoryService.deleteTag(tagId);
        return BaseResponseVO.success(null);
    }

    //老项目中的getCategoryName就是这个
    @GetMapping(value = "/getAllTags")
    @ResponseBody
    public BaseResponseVO getAllTags(){
        List<Category> tags = categoryService.getAllTags();
        return BaseResponseVO.success(tags);
    }
}
