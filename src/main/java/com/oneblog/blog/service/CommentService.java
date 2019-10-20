package com.oneblog.blog.service;

import com.oneblog.blog.entity.Comment;
import com.oneblog.blog.entity.CommentExample;
import com.oneblog.blog.mapper.CommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    CommentMapper commentMapper;

    /**
     * 增加评论
     * @param blogId 博客id
     * @param username 留言用户姓名
     * @param content 留言内容
     */
    public void addComment(Integer blogId,String username,String content){

        username=username.equals("")?"匿名":username;

        content=content.equals("")?"未留下任何消息":content;


        Comment comment = new Comment();
        Long time = System.currentTimeMillis();
        comment.setBlogId(blogId);
        comment.setUsername(username);
        comment.setContent(content);
        comment.setCreatedtime(time);
        commentMapper.insert(comment);
        logger.info("{}在{}文章上增加留言",username,blogId);
    }

    /**
     * 删除评论
     * @param commentId 评论id
     */
    public void deleteComment(Integer commentId){
        commentMapper.deleteByPrimaryKey(commentId);
        logger.info("删除留言{}",commentId);
    }


    /**
     * 获取某一个文章的评论
     * @param blogId 文章id
     * @return 某文章所有评论
     */
    public List<Comment> getCommentByBlog(Integer blogId){
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andBlogIdEqualTo(blogId);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        comments= comments==null?new ArrayList<>():comments;
        return comments;
    }

    /**
     * 获取所有评论
     * @return 所有评论
     */
    public List<Comment> getAllComment(){
        CommentExample example = new CommentExample();
        example.createCriteria().andBlogIdIsNotNull();
        List<Comment> comments = commentMapper.selectByExample(example);
        comments=comments==null?new ArrayList<>():comments;
        return comments;
    }



}
