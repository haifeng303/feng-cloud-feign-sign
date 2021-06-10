package com.fengzai.feign.annotation;

import com.fengzai.feign.config.FeignSecretConfig;
import com.fengzai.feign.config.FeignSecurityConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @PACKAGE_NAME: com.fengzai.annotation
 * @author: rhf
 * @description:
 * @DATE: 2020/9/11
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Import(value = {FeignSecurityConfig.class, FeignSecretConfig.class})
public @interface EnableFeignSecurity {

}
