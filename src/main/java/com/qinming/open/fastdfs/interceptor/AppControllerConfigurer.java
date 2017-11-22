package com.qinming.open.fastdfs.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by QinMing on 2017/10/26.
 */
@Configuration
public class AppControllerConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppControllerInterceptor()).addPathPatterns("/api/**", "/fdfs/**", "/user/**");
        super.addInterceptors(registry);
    }
}
