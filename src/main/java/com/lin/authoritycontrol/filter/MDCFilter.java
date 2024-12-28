package com.lin.authoritycontrol.filter;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * mdc过滤器
 *
 * @author 林维家
 * @since 2024/12/28 下午3:24
 */
@Component
public class MDCFilter implements Filter {

    public static final String TraceId = "TraceId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String traceId = httpServletRequest.getHeader(TraceId);
            if (StrUtil.isBlank(traceId)) {
                // 如果没有就手动设置
                traceId = IdUtil.fastSimpleUUID();
            }
            MDC.put(TraceId, traceId);
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
