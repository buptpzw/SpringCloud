package com.sohu.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.sohu.dao.HardCodeDao;
import com.sohu.service.RateLimitService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

/**
 * Created by zhiweipan on 2017/7/27.
 */
public class ApiRateLimitFilter extends ZuulFilter {

    @Autowired
    private RateLimitService rateLimitService;

    @Override
    public String filterType() {
        return Constants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        long now = System.currentTimeMillis();
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String appKey = request.getParameter("appKey");
        String apiName = request.getRequestURI();
        // 使用appKey+apiId 作为redis 的 key
        long apiId = rateLimitService.selectApiIdbyApiName(apiName);
        if(!rateLimitService.checkHashKey(appKey,apiId))
            return null;
        long timeStamp = rateLimitService.getTimeStamp(appKey,apiId);
        long rate = rateLimitService.getCount(appKey,apiId);
        // 如果访问频率超出限制 ，拒绝服务
        // 如果时间间隔在 规定范围内，就去判断 访问频率有没有 超过限制
        if( (now-timeStamp)< Constants.DEFAULT_TIME_DIFFERENCE ){
            // 访问频率超过限制
            if(rate > HardCodeDao.DESIGNED_RATE ){
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(Constants.UNAUTHORIZED);
            }
            rateLimitService.updateRate(appKey,apiId);
        }
        else
            rateLimitService.setInit(appKey,apiId,timeStamp);
        return null;
    }
}
