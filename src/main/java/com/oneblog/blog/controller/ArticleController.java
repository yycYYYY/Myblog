package com.oneblog.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oneblog.blog.entity.Blog;
import com.oneblog.blog.model.vo.BaseResponseVO;
import com.oneblog.blog.service.ArticleService;
import com.oneblog.blog.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Resource
    private ArticleService articleService;

    @Resource
    private CategoryService categoryService;

    /**
     *新建文章
     */
    @PostMapping(value = "/newArticle",produces="application/json")
    public ModelAndView newArticle(HttpServletRequest request,
                                   @RequestParam(value = "editormd-markdown") String md,
                                   @RequestParam(value = "editorhtml",required = false) String content){
        Map<String, Object> map = new HashMap<>();
        ModelAndView view = new ModelAndView();

        Blog blog = new Blog();
        blog.setCreatedtime(System.currentTimeMillis());
        blog.setTitleintro(request.getParameter("title"));


        // 获取文章对应的 分类Id
        Integer categoryId = categoryService.getIdByTagname(request
                .getParameter("categoryName"));

        blog.setCategoryid(categoryId);
        blog.setContent(content);
        blog.setMd(md);
        blog.setTitle(request.getParameter("title"));
        blog.setTitleintro(request.getParameter("titleIntro"));

        articleService.newArticle(blog);
        // 获得文章的标题，标题介绍，日期，分类等信息
        map.put("message", "新建文章成功！");
        view.setViewName("admin/newsuccess");
        view.addObject("map", map);
        return view;
    }

    /**
     *
     * 根据文章id删除文章
     */
    @GetMapping(value = "/deleteArticle")
    public BaseResponseVO deleteArticleById(HttpServletRequest request,
                                  @RequestParam(value = "blogId") Integer blogId){
        if (request.getSession().getAttribute("username")!=null){
            articleService.deleteArticle(blogId);
            return BaseResponseVO.success("删除成功");
        }else {
            logger.info("未登录，无法删除文章");
            return BaseResponseVO.fail("请登录后删除文章");

        }
    }

    /**
     * 更新文章
     */
    @PostMapping(value = "/updateArticle",produces = "application/json")
    public ModelAndView updateArticle(HttpServletRequest request,
                              @RequestParam(value = "editormd-markdown",required = false) String md,
                              @RequestParam(value = "editorhtml",required = false) String content){
        ModelAndView view = new ModelAndView();
        Map<String, Object> map = new HashMap<>();

        // 获取隐藏域文章的 id
        int id = Integer.parseInt(request.getParameter("id"));
        // 获取文章对应的 分类Id
        Integer categoryId = categoryService.getIdByTagname(request
                .getParameter("categoryName"));

        Blog blog = new Blog();
        blog.setId(id);
        blog.setCategoryid(categoryId);
        blog.setContent(content);
        blog.setMd(md);
        blog.setTitle(request.getParameter("title"));
        blog.setTitleintro(request.getParameter("titleIntro"));
        blog.setCreatedtime(System.currentTimeMillis());
        articleService.updateArticle(blog);
        // 获得文章的标题，标题介绍，日期，分类等信息
        map.put("message", "修改文章成功！");
        view.setViewName("admin/editsuccess");
        view.addObject("map", map);
        return view;
    }

    /**
     * 分页，并返回文章列表
     * @param pageNumber 页数
     * @return 文章列表的vo
     */
    @GetMapping(value = "/getArticles")
    public BaseResponseVO getAllArticles(@RequestParam(value = "pn",defaultValue = "1") Integer pageNumber){
        //分页
        PageHelper.startPage(pageNumber,5);
        List<Blog> blogs = articleService.getAllArticles();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs,5);
        return BaseResponseVO.success(pageInfo);
    }


    //是否可以和老项目中的/showArticle公用
    @GetMapping(value = "/getArticleById")
    public BaseResponseVO getArticleById(@RequestParam(value = "blogId",defaultValue = "1") Integer blogId){
        return BaseResponseVO.success(articleService.getArticleById(blogId));
    }

    //获取某分类下所有文章并分页，老项目在Category的controller中
    @GetMapping(value = "/getArticlesByTag")
    public BaseResponseVO getArticlesdByTag(@RequestParam(value = "tagId") Integer tagId,
                                            @RequestParam(value = "pn",defaultValue = "1") Integer pageNumber){
        PageHelper.startPage(pageNumber, 5);
        List<Blog> list = articleService.getArticlesByTag(tagId);
        PageInfo<Blog> pageInfo = new PageInfo<>(list, 5);
        return BaseResponseVO.success(pageInfo);
    }



}
