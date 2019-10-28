package com.oneblog.blog.service;


import com.oneblog.blog.entity.BlogExample;
import com.oneblog.blog.entity.Category;
import com.oneblog.blog.entity.CategoryExample;
import com.oneblog.blog.mapper.BlogMapper;
import com.oneblog.blog.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 新建分类
     */
    public void newTag(Integer tagId,Integer level,String name){
        Category category=new Category(tagId,name,level);
        categoryMapper.insert(category);
        logger.info("{}号tag已新建",tagId);
    }

    /**
     * 删除tag时会删除对应的文章
     */
    public void deleteTag(Integer tagId){
        BlogExample example = new BlogExample();
        example.createCriteria().andCategoryidEqualTo(tagId);
        //删除该tag对应的文章
        blogMapper.deleteByExample(example);
        categoryMapper.deleteByPrimaryKey(tagId);
    }

    /**
     * 修改tag信息
     *
     */
    public void updateTag(Integer tagId,String name,Integer level){
        CategoryExample example = new CategoryExample();
        example.createCriteria().andIdEqualTo(tagId);
        categoryMapper.updateByExample(new Category(tagId,name,level) ,example);
        logger.info("{}号tag被修改",tagId);
    }

    /**
     * 获取所有tag
     *
     */
    public List<Category> getAllTags(){
        CategoryExample example = new CategoryExample();
        example.createCriteria().andIdIsNotNull();
        return categoryMapper.selectByExample(example);
    }

    /**
     * 根据tagname获取tagId
     *
     */
    public Integer getIdByTagname(String tagname){
        CategoryExample example = new CategoryExample();
        example.createCriteria().andNameEqualTo(tagname);
        int tagId = categoryMapper.selectByExample(example).get(0).getId();
        logger.info("{}tag的id为{}",tagname,tagId);
        return tagId;

    }
}
