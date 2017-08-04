package com.sohu.dao;

import com.sohu.mapper.ApiProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zhiweipan on 2017/7/27.
 */
@Service
public class HardCodeDao {
    public static final long DESIGNED_RATE = 3 ;

    @Autowired
    private ApiProviderMapper apiProviderMapper;

    public long selectApiIdByApiName(String apiName){
        return apiProviderMapper.selectIdByApiName(apiName);
    }

}
