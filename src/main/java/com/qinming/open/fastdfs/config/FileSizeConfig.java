package com.qinming.open.fastdfs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Created by QinMing on 2017/12/06.
 */
@Configuration
public class FileSizeConfig {

    @Value("${MAX_FILE_SIZE}")
    private String MAX_FILE_SIZE;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(MAX_FILE_SIZE);
        factory.setMaxRequestSize(MAX_FILE_SIZE);
        return factory.createMultipartConfig();
    }

}
