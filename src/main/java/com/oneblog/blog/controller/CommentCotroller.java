package com.oneblog.blog.controller;

import com.oneblog.blog.entity.Comment;
import com.oneblog.blog.service.CommentService;
import com.oneblog.blog.tools.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CommentCotroller {

    private static final Logger logger = LoggerFactory.getLogger(CommentCotroller.class);

    @Autowired
    private CommentService commentService;

    @PostMapping("/sendComment")
    public Msg sendComment(@RequestParam(value = "blogId") Integer blogId,
                           @RequestParam(value = "username") String username,
                           @RequestParam(value = "content")String content){
        commentService.addComment(blogId,username,content);
        Msg msg=Msg.success();
        return msg;
    }

    @GetMapping("/deleteComment")
    public Msg deleteComment(@RequestParam(value = "commentId")Integer commentId){
        commentService.deleteComment(commentId);
        Msg msg = Msg.success();
        return msg;
    }

    @GetMapping("/getComment")
    public Msg getCommentById(@RequestParam(value = "blogId")Integer blogId){

        Msg msg = Msg.success();
        msg.add("comments",commentService.getCommentByBlog(blogId));
        logger.info("获取单个博客评论");
        return msg;
    }

    /**
     *
     * @return 获取所有评论
     */
    @ResponseBody
    @GetMapping("/getAllComments")
    public Msg getAllComment(){
        List<Comment> comments = commentService.getAllComment();
        Msg msg = Msg.success();
        if (!comments.isEmpty()){
            logger.info("获取所有评论成功/n");
            msg.add("comments",comments);
        }else {

            msg.add("comments","评论为空");
            logger.info("评论为空");
        }
        return msg;
    }

    /**
     *
     * @param request 请求
     * @param response 响应
     * @return 地址
     */
    @GetMapping(value = "/commentManage")
    public String commentManage(HttpServletRequest request,
                             HttpServletResponse response){
    if (request.getSession().getAttribute("username").equals("")
            ||request.getSession().getAttribute("username")==null){
        logger.info("未登录，无法进入评论管理页面/n");
        return "admin/error";
    }
        return "admin";
    }
}
