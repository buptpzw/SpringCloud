package com.sohu.mapper;

import com.sohu.dao.entity.ApiProvider;

/**
 * Created by zhiweipan on 2017/7/14.
 */
public interface ApiProviderMapper {

    /** 根据id 查找 ApiProvider */
    ApiProvider selectProviderById(long id);

    /** 插入 ApiProvider */
    void insertProvider(ApiProvider apiProvider);

    /** 更新 ApiProvider */
    void updateProvider(ApiProvider apiProvider);

    /** 根据apiName 查找 ApiProvider */
    ApiProvider selectProviderByApiName(String apiName);

    /** 根据apiName 查找 ApiProvider 的id */
    long selectIdByApiName(String apiName);
}
