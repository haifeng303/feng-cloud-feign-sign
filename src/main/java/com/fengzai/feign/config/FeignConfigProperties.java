package com.fengzai.feign.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @PACKAGE_NAME: com.fengzai.feign.config
 * @author: rhf
 * @description:
 * @DATE: 2020/9/11
 **/
@ConfigurationProperties(
        prefix = "config.feign")
public class FeignConfigProperties {
    private List<String> patterns;

    public List<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<String> patterns) {
        this.patterns = patterns;
    }
}
