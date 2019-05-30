package com.ky.note.zuul.filter;

import com.ky.note.zuul.constant.ZuulFilterConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
public class RequestFilter extends ZuulFilter {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 对应Zuul生命周期的四个阶段: pre、post、route、error
     */
    @Override
    public String filterType() {
        return ZuulFilterConstant.PRE;
    }

    /**
     * 过滤器的优先级，数字越小，优先级越高；
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器的run方法
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.info("请求URI：{}，HTTP Method：{}，请求IP：{}", uri, method, host);
        return null;


    }
}
