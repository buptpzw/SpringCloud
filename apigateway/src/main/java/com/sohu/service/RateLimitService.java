package com.sohu.service;

/**
 * Created by zhiweipan on 2017/7/27.
 */
public interface RateLimitService {

    /** 检查是否缓存中存在 key*/
    public boolean checkHashKey(String appKey,long apiId);

    /** 获取 该apiName的已访问次数 rate */
    public long getCount(String appKey,long apiId);

    /** 获取该appKey 、apiName 的 timeStamp */
    public long getTimeStamp(String appKey,long apiId);

    /** 设置为初始状态 */
    public void setInit(String appKey,long apiId,long timeStamp);

    /** 更新 */
    public void updateRate(String appKey,long apiId);

    /** 根据 apiName 查询出 api_id */
    public long selectApiIdbyApiName(String apiName);
}
