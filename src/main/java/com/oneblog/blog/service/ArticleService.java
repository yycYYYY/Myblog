package com.oneblog.blog.service;

import com.oneblog.blog.entity.Blog;
import com.oneblog.blog.entity.BlogExample;
import com.oneblog.blog.mapper.BlogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    private BlogMapper blogMapper;

    /**
     *
     */
    public void newArticle(Integer categoryId,String title,String titleintro,
                           String content,String md){
        Blog blog = new Blog();
        blog.setCategoryid(categoryId);
        blog.setTitle(title);
        blog.setTitleintro(titleintro);
        blog.setContent(content);
        blog.setTitleintro(md);
        Long timeStamp = System.currentTimeMillis();
        blog.setCreatedtime(timeStamp);
        blogMapper.insert(blog);
        logger.info("新建文章，主题为{}，创建时间为{}"+title+timeStamp);
    }

    /**
     *
     * @param blogId
     */
    public void deleteArticle(Integer blogId){
        blogMapper.deleteByPrimaryKey(blogId);
        logger.info("{}号文章被删除"+blogId);
    }

    /**
     *
     * @param blogId
     * @param categoryId
     * @param title
     * @param titleintro
     * @param content
     * @param md
     */
    public void updateArticle(Integer blogId,Integer categoryId, String title,String titleintro,
                              String content,String md){
        BlogExample example = new BlogExample();
        example.createCriteria().andIdEqualTo(blogId);
        Blog blog = new Blog(blogId,categoryId,title,titleintro,content,
                md,null,null);
        blogMapper.updateByExample(blog,example);
        logger.info("第{}文章被修改"+blogId);

    }
    /**
     *
     * @return
     */
    public List<Blog> getAllArticles(){
        return blogMapper.selectAll();
    }

    /**
     *
     * @param blogId
     * @return
     */
    public Blog getArticleById(Integer blogId){
        return blogMapper.selectByPrimaryKey(blogId);
    }
}
