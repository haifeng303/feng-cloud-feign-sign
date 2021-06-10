package com.fengzai.feign.interceptor;

import com.fengzai.feign.constant.FeignConstant;
import com.fengzai.feign.util.Sha256Util;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @PACKAGE_NAME: com.fengzai.feign.interceptor
 * @author: rhf
 * @description:
 * @DATE: 2020/9/11
 **/
public class FeignClientInterceptor implements RequestInterceptor {

    private final static Logger log = LoggerFactory.getLogger(FeignClientInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String url = requestTemplate.request().url().split("\\?")[0];
        try {
            requestTemplate.header(FeignConstant.header_key, Sha256Util.encode(url, FeignConstant.secret_salt));
        } catch (Exception e) {
            log.error("remote feign add header error", e);
        }
    }

}

