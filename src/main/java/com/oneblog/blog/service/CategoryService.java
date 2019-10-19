package com.oneblog.blog.service;

import com.oneblog.blog.entity.Category;
import com.oneblog.blog.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 新建分类
     * @param tagId
     * @param level
     * @param name
     */
    public void newTag(Integer tagId,Integer level,String name){
        Category category=new Category(tagId,name,level);
        categoryMapper.insert(category);
        logger.info("{}号tag已新建",tagId);
    }

    /**
     * 删除tag
     * @param tagId
     */
    public void deleteTag(Integer tagId){
        categoryMapper.deleteByPrimaryKey(tagId);
    }

    public void getAllTags(){

    }

    public void newTag(String name,Integer level){
        Category category = new Category();
        category.setName(name);
        level = (level==null||level.equals(""))?1:level;
        category.setLevel(level);
        categoryMapper.insert(category);
        logger.info("新建tag{}"+name);
    }
}
