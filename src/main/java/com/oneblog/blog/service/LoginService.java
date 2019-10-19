package com.oneblog.blog.service;

import com.oneblog.blog.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsersMapper usersMapper;

    public void newUser(){}

    public void signUp(){}

    public void login(){}

    public void logout(){}
}
