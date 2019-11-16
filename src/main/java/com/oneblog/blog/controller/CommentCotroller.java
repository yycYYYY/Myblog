package com.oneblog.blog.controller;

import com.oneblog.blog.entity.Comment;
import com.oneblog.blog.model.vo.BaseResponseVO;
import com.oneblog.blog.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommentCotroller {

    private static final Logger logger = LoggerFactory.getLogger(CommentCotroller.class);

    @Resource
    private CommentService commentService;


    @PostMapping("/sendComment")
    public BaseResponseVO sendComment(@RequestParam(value = "blogId") Integer blogId,
                           @RequestParam(value = "username") String username,
                           @RequestParam(value = "content")String content){
        commentService.addComment(blogId,username,content);
        return BaseResponseVO.success("评论提交成功");
    }

    @GetMapping("/deleteComment")
    public BaseResponseVO deleteComment(@RequestParam(value = "commentId")Integer commentId){
        commentService.deleteComment(commentId);
        return BaseResponseVO.success("评论删除成功");
    }

    /**
     *
     * @param blogId 文章id
     * @return 单个博客评论
     */
    @GetMapping("/getComments")
    public BaseResponseVO getCommentById(@RequestParam(value = "blogId")Integer blogId){

        Map<String,Object> map = new HashMap<>();
        map.put("comments",commentService.getCommentByBlog(blogId));
        logger.info("获取单个博客评论");
        return BaseResponseVO.success(map);
    }

    /**
     *
     * @return 获取所有评论
     */
    @GetMapping("/getAllComments")
    public BaseResponseVO getAllComment(){
        List<Comment> comments = commentService.getAllComment();
        Map<String,Object> map = new HashMap<>();
        if (!comments.isEmpty()){
            logger.info("获取所有评论成功");
            map.put("comments",comments);
        }else {

            map.put("comments","评论为空");
            logger.info("评论为空");
        }
        return BaseResponseVO.success(map);
    }

}
