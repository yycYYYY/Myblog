package com.oneblog.blog.controller;


import com.oneblog.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 页面跳转的handler
 */
@Controller
public class MainController {

    @Resource
    private ArticleService articleService;

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String showInfoDemo(Model model){
        return "html/index";
    }

    @GetMapping("/about")
    public String showAbout(){
        return "html/about";
    }

    @GetMapping("/tags")
    public String showContact(){
        return "html/tags";
    }

    @GetMapping("/tagPage")
    public String tagArticlePage(@RequestParam(value = "blogId")Integer blogId,Model model){
        model.addAttribute("blogId", blogId);
        return "html/tagpage";
    }

    //新建文章是判断是否登录
    @GetMapping(value="/islogin")
    public String newarticle(HttpServletRequest request) {
        if(request.getSession().getAttribute("username") != null) {
            System.out.println("username = " + request.getSession().getAttribute("username"));
            return "redirect:/newarticle";
        }
        return "html/admin/error";
    }



    // 新建文章
    @GetMapping(value = "/newarticle")
    public String editarticle(HttpServletRequest request)
    {
        //得到 session 的值，判断是否已经登陆
        if(request.getSession().getAttribute("username") != null) {
            String username = request.getSession().getAttribute("username").toString();
            if(username != null) {
                return "html/admin/newblog";
            }
        }
        return "html/admin/error";
    }

    //显示文章
    @GetMapping(value = "/showArticle")
    public String showArticle(@RequestParam(value="id", defaultValue="1")Integer id, Model model) {
        String mdString = articleService.getMdByArticleId(id);
        model.addAttribute("mdString", mdString);
        model.addAttribute("id", id);
        return "html/showarticle";
    }


    // 编辑文章
    @GetMapping("/edit")
    public String edit(@RequestParam(value="id", defaultValue="1") Integer id,
                       HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("username")  != null) {
            model.addAttribute("blogid", id);
            return "html/admin/edit";
        }
        return "html/admin/error";
    }

    // 没有登陆的时候，就不能删除文章
    @GetMapping("/deleteNoLogin")
    public String deleteNoLogin() {
        return "html/admin/error";
    }

    @GetMapping(value ="/articleAdmin")
    public String articleAdmin(HttpServletRequest request, HttpServletResponse response){
        String username = null;
        if(request.getSession().getAttribute("username") != null) {
            username = request.getSession().getAttribute("username").toString();
        }

        if(username == null) {
            return "html/admin/error";
        }
        return "html/admin/backAdmin";
    }
    
    // 分类的后台管理
    @GetMapping(value="/tagAdmin")
    public String tagadmin(HttpServletRequest request, HttpServletResponse response) {
        String username = null;
        if(request.getSession().getAttribute("username") != null) {
            username = request.getSession().getAttribute("username").toString();
        }

        if(username == null) {
            return "html/admin/error";
        }
        return "html/admin/tagadmin";
    }

    // 评论后台管理
    @GetMapping("/commentback")
    public String commentback(HttpServletRequest request,HttpServletResponse response) {
        //得到 session 的值，判断是否已经登陆
        if(request.getSession().getAttribute("username") != null) {
            String username = request.getSession().getAttribute("username").toString();
            if(username != null) {
                return "html/admin/commentadmin";
            }
        }
        logger.info("未登录，无法进入评论管理页面/n");
        return "html/admin/error";
    }
}
