package com.oneblog.blog.controller;

import com.oneblog.blog.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 页面跳转的handler
 */
@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    public String showInfoDemo(Model model){
        Users user = new Users("aa","aa");
        logger.info("demo");
        String message = "hello themeleaf!";
        model.addAttribute("user",user);
        model.addAttribute("message",message);
        return "html/index";
    }

    // 后台新建博客文章,判断是否登陆，我用  ajax 请求无法跳转页面，
    // 使用了window.location='${APP_PATH }/islogin'; 请求跳转
    @RequestMapping(value="/islogin")
    public String newarticle(HttpServletRequest request) {
        if(request.getSession().getAttribute("username") != null) {
            System.out.println("username = " + request.getSession().getAttribute("username"));
            return "redirect:/newarticle";
        }
        return "admin/error";
    }



    // 新建文章
    @RequestMapping(value="/newarticle")
    public String editarticle(HttpServletRequest request)
    {
        //得到 session 的值，判断是否已经登陆
        if(request.getSession().getAttribute("username") != null) {
            String username = request.getSession().getAttribute("username").toString();
            if(username != null) {
                return "admin/newblog";
            }
        }
        return "admin/error";
    }



    // 编辑文章
    @RequestMapping("/edit")
    public String edit(@RequestParam(value="id", defaultValue="1") Integer id,
                       HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("username")  != null) {
            model.addAttribute("blogid", id);
            return "admin/edit";
        }
        return "admin/error";
    }

    // 没有登陆的时候，就不能删除文章
    @RequestMapping("/deleteNoLogin")
    public String deleteNoLogin() {
        return "admin/error";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }


    // 分类的后台管理
    @RequestMapping(value="/tagAdmin")
    public String tagadmin(HttpServletRequest request, HttpServletResponse response) {
        String username = null;
        if(request.getSession().getAttribute("username") != null) {
            username = request.getSession().getAttribute("username").toString();
        }

        if(username == null) {
            return "admin/error";
        }
        return "admin/tagadmin";
    }

    // 评论后台管理
    @RequestMapping("/commentback")
    public String commentback(HttpServletRequest request,HttpServletResponse response) {
        //得到 session 的值，判断是否已经登陆
        if(request.getSession().getAttribute("username") != null) {
            String username = request.getSession().getAttribute("username").toString();
            if(username != null) {
                return "admin/commentadmin";
            }
        }
        return "admin/error";
    }
}
