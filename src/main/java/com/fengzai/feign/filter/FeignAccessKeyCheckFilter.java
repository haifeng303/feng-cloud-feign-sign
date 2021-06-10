package com.fengzai.feign.filter;

import com.fengzai.feign.config.FeignConfigProperties;
import com.fengzai.feign.constant.FeignConstant;
import com.fengzai.feign.util.Sha256Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @PACKAGE_NAME: com.fengzai.feign.filter
 * @author: rhf
 * @description:
 * @DATE: 2020/9/11
 **/
public class FeignAccessKeyCheckFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(FeignAccessKeyCheckFilter.class);

    private FeignConfigProperties feignConfigProperties;

    public FeignAccessKeyCheckFilter(FeignConfigProperties feignConfigProperties) {
        this.feignConfigProperties = feignConfigProperties;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = ((HttpServletRequest) servletRequest);
        String requestURI = httpServletRequest.getRequestURI();
        logger.info("feign check access key remoteAddr -> :{}, url -> :{}", httpServletRequest.getRemoteAddr(), httpServletRequest.getRequestURL());
        if (checkUri(requestURI)) {
            String secret_key = httpServletRequest.getHeader(FeignConstant.header_key);
            if (StringUtils.isEmpty(secret_key) || !Sha256Util.matches(requestURI, FeignConstant.secret_salt, secret_key)) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                PrintWriter printWriter = httpServletResponse.getWriter();
                printWriter.write("{\n" +
                        "  \"header\": {\n" +
                        "    \"reCode\": \"0005\",\n" +
                        "    \"reMsg\": \"assess key error\"\n" +
                        "  },\n" +
                        "  \"success\": false\n" +
                        "}");
                printWriter.flush();
                printWriter.close();
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    private boolean checkUri(String uri) {
        boolean flag = false;
        List<String> patterns = feignConfigProperties.getPatterns();
        if (CollectionUtils.isEmpty(patterns)) {
            return false;
        }
        for (String pattern : patterns) {
            String x = pattern.replaceAll("\\*", "");
            StringBuffer buffer = new StringBuffer();
            buffer.append("^(");
            buffer.append(x);
            buffer.append(")(.*)");
            if (uri.matches(buffer.toString())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
//        String xx = "^(/api/)(.*)";
        String xx = "/api/*";
        xx.replaceAll("\\*", "");
        StringBuffer buffer = new StringBuffer();
        buffer.append("^(");
        buffer.append(xx);
        buffer.append(")(.*)");

        String xxx = "/api/user/getUserByUserName";
        System.out.println(xxx.matches(buffer.toString()));
    }
}
