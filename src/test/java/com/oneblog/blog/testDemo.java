package com.oneblog.blog;

import com.oneblog.blog.tools.Msg;

public class testDemo {
    public static void main(String[] args) {
        Msg msg=Msg.success();
        msg.setMsg("aaa");
        System.out.println(msg);
    }
}
