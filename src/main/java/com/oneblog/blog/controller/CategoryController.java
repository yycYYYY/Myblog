package com.oneblog.blog.controller;

import com.oneblog.blog.entity.Category;
import com.oneblog.blog.model.vo.BaseResponseVO;
import com.oneblog.blog.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class CategoryController {

    private static final Logger logger=LoggerFactory.getLogger(CategoryController.class);

    @Resource
    private CategoryService categoryService;

    @PostMapping(value = "/newTag")
    public BaseResponseVO newTag(@RequestParam(value = "tagId")Integer tagId,
                      @RequestParam(value = "level") Integer level,
                      @RequestParam(value = "name") String name){
            categoryService.newTag(tagId, level, name);
            logger.info("新建tag");
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
