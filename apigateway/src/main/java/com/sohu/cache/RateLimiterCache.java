package com.sohu.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 限流器缓存
 * 记录 AppKey api 调用频率
 * Created by zhiweipan on 2017/7/27.
 */
@Service
public class RateLimiterCache {

    /**
     * redis缓存
     *
     * key ：
     *       appKey+apiName
     * value：
     *      （String   ：  Long）
     *       rate     ：  1
     *       timeStamp ： 542692590
     * */
    @Resource
    private RedisTemplate redisTemplate;

    //  时间范围，在该时间范围内不能超过 设计的访问频率 ，设为 300s
    private static final long TIME_LIMIT = 300000 ;

    public boolean checkHasKey(String appKey,long apiId){
        String key = generateKey(appKey,apiId);
        if(redisTemplate.hasKey(key))
            return true;
        else{
            long now = System.currentTimeMillis();
            setInit(appKey,apiId,now);
            return false;
        }
    }

    public long getRate(String appKey,long apiId){
        String key = generateKey(appKey,apiId);
        HashOperations<String,String,Long> operations = redisTemplate.opsForHash();
        //Map<String,Long> entries = operations.entries(key);
        long rate = operations.get(key,"rate");
        return rate;
    }

    public long getTimeStamp(String appKey,long apiId){
        HashOperations<String,String,Long> operations = redisTemplate.opsForHash();
        String key = generateKey(appKey,apiId);
        Map<String,Long> map = operations.entries(key);
        return map.get("timeStamp");
    }

    public void setInit(String appKey,long apiId,long timeStamp){
        HashOperations<String,String,Long> operations = redisTemplate.opsForHash();
        String key = generateKey(appKey,apiId);
        operations.put(key,"rate",1L);
        operations.put(key,"timeStamp",timeStamp);
    }

    public void updateRate(String appKey,long apiId){
        HashOperations<String,String,Long> operations = redisTemplate.opsForHash();
        String key = generateKey(appKey,apiId);
        Map<String,Long> map = operations.entries(key);
        long rate = map.get("rate");
        rate ++;
        operations.put(key,"rate",rate);
    }

//    public void updateRate(String appKey,String apiName){
//        String key = generateKey(appKey,apiName);
//        Map<String,Long> map = operations.entries(key);
//        long now = System.currentTimeMillis();
//        //  缓存中存在这个key
//        if(map != null ){
//            // 对时间进行判断，如果是在限定时间范围内，则进行更新；超出时间范围，则设为1
//            long timestamp = map.get("timeStamp");
//            if( (now-timestamp)>TIME_LIMIT ){
//                operations.put(key,"rate",1L);
//                operations.put(key,"timeStamp",now);
//            }else{
//                long rate = map.get("rate");
//                rate ++;
//                operations.put(key,"rate",rate);
//            }
//        } // 缓存中不存在这个 key
//        else{
//            operations.put(key,"rate",1L);
//            operations.put(key,"timeStamp",now);
//            // 将 key 缓存时间设置为 5 minutes
//            redisTemplate.expire(key,5L,TimeUnit.MINUTES);
//        }
//    }

    public String generateKey(String appKey,long apiId){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(appKey);
        stringBuilder.append(String.valueOf(apiId));
        return stringBuilder.toString();
    }

}
