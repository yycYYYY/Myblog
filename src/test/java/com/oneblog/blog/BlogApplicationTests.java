package com.oneblog.blog;

import com.oneblog.blog.entity.Blog;
import com.oneblog.blog.entity.Category;
import com.oneblog.blog.mapper.BlogMapper;
import com.oneblog.blog.service.ArticleService;
import com.oneblog.blog.service.CategoryService;
import com.oneblog.blog.tools.TimeStampTranslate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    TimeStampTranslate timeStampTranslate;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    DataSource dataSource;

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @Test
    public void contextLoads(){
    }

    @Test
    public void articleServiceTest(){

        for (Blog blog : articleService.getAllArticles()){
            System.out.println(blog.getTitle());
        }

        System.out.println(articleService.getArticleById(1).getTitle());

        articleService.newArticle(1,"demo2","","aaa","aaa");

        articleService.deleteArticle(3);
    }

    @Test
    public void categoryServiceTest(){
        categoryService.newTag(2,1,"demo2");

        for (Category category:categoryService.getAllTags()){
            System.out.println(category.getName());
        }

        categoryService.updateTag(2,"demodemo",1);

        System.out.println(categoryService.getIdByTagname("demoTag"));
    }

    @Test
    public void timeTest(){
        long timeStamp=System.currentTimeMillis();
        System.out.println(timeStamp);
        System.out.println(TimeStampTranslate.timeStamp2Time(timeStamp));
    }

    @Test
    public void datasourceTest() throws SQLException {
        System.out.println(dataSource);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();

    }

    @Test
    public void DAOTest(){
        Blog blog = blogMapper.selectByPrimaryKey(1);
        System.out.println(blog.getTitle());
        blogMapper.selectAll();
        blogMapper.selectByPrimaryKeyWithCategory(1);
    }

}
