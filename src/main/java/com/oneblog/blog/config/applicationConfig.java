package com.oneblog.blog.config;

import com.oneblog.blog.tools.TimeStampTranslate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class applicationConfig {

    @Bean
    public TimeStampTranslate timeStampTranslate(){
        return new TimeStampTranslate();
    }
}
