package com.qinming.open.fastdfs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * Created by QinMing on 2017/11/22.
 */
@SpringBootApplication
public class FastdfsClientApplication {
    @Value("${MAX_FILE_SIZE}")
    private String MAX_FILE_SIZE;

    public static void main(String[] args) {
        SpringApplication.run(FastdfsClientApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(MAX_FILE_SIZE);
        factory.setMaxRequestSize(MAX_FILE_SIZE);
        return factory.createMultipartConfig();
    }

}
