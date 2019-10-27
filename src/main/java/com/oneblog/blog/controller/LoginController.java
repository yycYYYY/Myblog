package com.oneblog.blog.controller;

import com.oneblog.blog.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.security.sasl.SaslException;
import javax.servlet.http.HttpServletRequest;

//@Controller
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private LoginService loginService;

    /**
     * 跳转至登录页
     * @return 是否登录
     */
    @GetMapping(value = "/signIn")
    public String signIn(){
        return "admin/signIn";
    }

    /**
     * 跳转至注册页
     */
    @GetMapping(value = "/signUp")
    public String signUp(){
        return "admin/signUp";
    }


    @PostMapping(value = "/login")
    public String login(@RequestParam(value = "username",required = false) String username,
                        @RequestParam(value = "password",required = false) String password,
                        HttpServletRequest request) throws SaslException {
        if (request.getSession().getAttribute("username")!=null){
            return "admin/backAdmin";
        }

        if (loginService.login(username,password)){
            return "admin/backAdmin";
        }else {
            return "admin/loginFail";
        }
    }

    @GetMapping(value = "/isLogin")
    public String isLogin(HttpServletRequest request){

        if (request.getSession().getAttribute("username") == null) {
            logger.info("用户未登陆");
            return "admin/error";
        }
        return "redirect:/newArticle";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        return "redirect:/signIn";
    }

    @PostMapping(value = "/newUser")
    public String newUser(@RequestParam(value = "username",required = false) String username,
                        @RequestParam(value = "password",required = false) String password) throws SaslException {
        loginService.newUser(username,password);
        return "redirect:/signIn";

    }
}
