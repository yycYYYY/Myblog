package com.oneblog.blog;

import com.oneblog.blog.entity.Blog;
import com.oneblog.blog.mapper.BlogMapper;
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

    @Test
    public void timeTest(){
        long timeStamp=System.currentTimeMillis();
        System.out.println(timeStamp);
        System.out.println(timeStampTranslate.timeStamp2Time(timeStamp));
    }

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    DataSource dataSource;


    @Test
    public void contextLoads(){
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
