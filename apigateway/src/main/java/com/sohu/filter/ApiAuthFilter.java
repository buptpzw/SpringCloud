package com.sohu.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhiweipan on 2017/7/27.
 */
public class ApiAuthFilter extends ZuulFilter{

    @Override
    public String filterType() {
        return Constants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURI();
        System.out.println(url);
        if(url.equals("/hi/print")){
            // 拒绝这个 rest api ， 其他 api 通过
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(Constants.UNAUTHORIZED);
        }
        return null;
    }
}
