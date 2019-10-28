package com.oneblog.blog.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

//    private static final Logger logger=LoggerFactory.getLogger(DruidConfig.class);

    //配置druid监控

    /**
     * 配置一个管理后台的servlet
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean =
                new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        Map<String,String> initParams=new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","admin");
        initParams.put("deny","15.56.45.48");
        initParams.put("allow","127.0.0.1");//不写时默认允许所有，不能填写localhost只能填写地址

        bean.setInitParameters(initParams);
        return bean;
    }


    /**
     * web监控的filter
     *
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams=new HashMap<>();
        initParams.put("exclusions","*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        bean.setInitParameters(initParams);

        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;

    }

}
