package com.oneblog.blog.service;

import com.oneblog.blog.entity.Blog;
import com.oneblog.blog.entity.BlogExample;
import com.oneblog.blog.mapper.BlogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.oneblog.blog.tools.TimeStampTranslate.timeStamp2Time;

@Service
public class ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Resource
    private BlogMapper blogMapper;

    /**
     * 新建文章
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
        logger.info("新建文章，主题为{}，创建时间为{}",title,timeStamp2Time(timeStamp));
    }

    public void newArticle(Blog blog){
        blogMapper.insert(blog);
        logger.info("新建文章，主题为{}，创建时间为{}",blog.getTitle(),timeStamp2Time(blog.getCreatedtime()));
    }

    /**
     * 删除文章
     * @param blogId 文章id
     */
    public void deleteArticle(Integer blogId){
        blogMapper.deleteByPrimaryKey(blogId);
        logger.info("{}号文章被删除",blogId);
    }

    /**
     *
     */
    public void updateArticle(Integer blogId,Integer categoryId, String title,String titleintro,
                              String content,String md){
        BlogExample example = new BlogExample();
        example.createCriteria().andIdEqualTo(blogId);
        Blog blog = new Blog(blogId,categoryId,title,titleintro,content,
                md,null,null);
        blogMapper.updateByExample(blog,example);
        logger.info("第{}文章被修改",blogId);

    }

    public void updateArticle(Blog blog){
        blogMapper.updateByPrimaryKey(blog);
        logger.info("{}文章被修改",blog.getTitle());
    }
    /**
     * 获取所有文章不带分类信息
     */
    public List<Blog> getAllArticles(){
        return blogMapper.selectAll();
    }

    /**
     * 根据id获取文章不带分类信息
     */
    public Blog getArticleById(Integer blogId){
        logger.info("获取{}号文章",blogId);
        return blogMapper.selectByPrimaryKey(blogId);
    }


    /**
     * 获取所有的文章带分类信息
     */
    public List<Blog> getAll() {
        logger.info("获取所有文章");
        return blogMapper.selectByExampleWithCategory(null);
    }

    /**
     * 通过主键获取博客文章带分类信息
     *
     */
    public Blog selectByPrimaryKeyWithCategory(Integer id) {
        logger.info("获取{}文章with tag",id);
        return blogMapper.selectByPrimaryKeyWithCategory(id);
    }

    public List<Blog> getArticlesByTag(Integer tagId){
        BlogExample example = new BlogExample();
        example.createCriteria().andCategoryidEqualTo(tagId);
        logger.info("根据{}号tag获取文章",tagId);
        return blogMapper.selectByExample(example);
    }

    public String getMdByArticleId(Integer id){
        Blog blog = blogMapper.selectByPrimaryKey(id);
        logger.info("获取{}号文章的md内容",id);
        return blog.getMd().isEmpty()?"":blog.getMd();
    }

}
