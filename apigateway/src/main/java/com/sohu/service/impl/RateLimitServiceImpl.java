package com.sohu.service.impl;

import com.sohu.cache.RateLimiterCache;
import com.sohu.dao.HardCodeDao;
import com.sohu.mapper.ApiProviderMapper;
import com.sohu.service.RateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhiweipan on 2017/7/27.
 */
@Service
public class RateLimitServiceImpl implements RateLimitService{

    @Autowired
    private RateLimiterCache rateLimiterCache;

    @Autowired
    private HardCodeDao hardCodeDao;

    @Override
    public boolean checkHashKey(String appKey, long apiId) {
        return rateLimiterCache.checkHasKey(appKey, apiId);
    }

    @Override
    public long getCount(String appKey,long apiId) {
        return rateLimiterCache.getRate(appKey,apiId);
    }

    @Override
    public long getTimeStamp(String appKey, long apiId) {
        return rateLimiterCache.getTimeStamp(appKey, apiId);
    }

    @Override
    public void setInit(String appKey, long apiId, long timeStamp) {
        rateLimiterCache.setInit(appKey, apiId, timeStamp);
    }

    @Override
    public void updateRate(String appKey, long apiId) {
        rateLimiterCache.updateRate(appKey, apiId);
    }

    @Override
    public long selectApiIdbyApiName(String apiName) {
        long apiId =  hardCodeDao.selectApiIdByApiName(apiName);
        return apiId;
    }


}
