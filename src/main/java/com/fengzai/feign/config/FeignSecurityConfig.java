package com.fengzai.feign.config;

import com.fengzai.feign.constant.FeignConstant;
import com.fengzai.feign.filter.FeignAccessKeyCheckFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @PACKAGE_NAME: com.fengzai.feign.config
 * @author: rhfa
 * @description:
 * @DATE: 2020/9/11
 **/
@Configuration
@EnableConfigurationProperties({FeignConfigProperties.class})
public class FeignSecurityConfig {
    @Resource
    private FeignConfigProperties feignConfigProperties;

    @Bean
    @ConditionalOnClass(FeignConfigProperties.class)
    public FilterRegistrationBean<FeignAccessKeyCheckFilter> FeignAccessKeyCheckFilterBean() {
        FilterRegistrationBean<FeignAccessKeyCheckFilter> bean = new FilterRegistrationBean(new FeignAccessKeyCheckFilter(feignConfigProperties));
        bean.setName(FeignConstant.feign_verify_signature_filter);
        bean.setOrder(1);
        bean.addUrlPatterns("/*");
        return bean;
    }
}
