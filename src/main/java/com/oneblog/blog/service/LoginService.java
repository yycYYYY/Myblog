package com.oneblog.blog.service;

import com.oneblog.blog.entity.Users;
import com.oneblog.blog.entity.UsersExample;
import com.oneblog.blog.mapper.UsersMapper;
import com.oneblog.blog.tools.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.SaslException;
import java.util.List;

@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private UsersMapper usersMapper;

    public void newUser(String username,String password) throws SaslException {
        UsersExample example = new UsersExample();
        example.createCriteria().andUsernameEqualTo(username);
        //如果该账号已存在则不能创建
        if (usersMapper.selectByExample(example).isEmpty()){
            Users user = new Users(username,MD5.getMD5(password));
            usersMapper.insert(user);
            logger.info("新建账号{}",username);
        }else {
            logger.info("该账号已存在{}",username);
        }

    }

    public void deleteUser(){}

    public boolean login(String username,String password) throws SaslException {
        UsersExample example = new UsersExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Users> users = usersMapper.selectByExample(example);
        if (users.isEmpty()){
            logger.info("此user账号不存在{}",username);
            //return Msg.fail.setContent("词username不存在");
            return false;
        }
        if (users.get(0).getPassword().equals(MD5.getMD5(password))){
            logger.info("{}账号密码校验成功",username);
            return true;

        }else {
            logger.info("{}账号密码校验失败",username);
            return false;

        }


    }

    public void logout(){}
}
