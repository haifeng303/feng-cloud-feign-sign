package com.fengzai.feign.config;

import com.fengzai.feign.interceptor.FeignClientInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @PACKAGE_NAME: com.fengzai.feign.config
 * @author: rhf
 * @description: feign配置
 * @DATE: 2020/9/11
 **/
@Configuration
public class FeignSecretConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignClientInterceptor();
    }
}
