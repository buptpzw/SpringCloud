package com.sohu.filter;

/**
 * Created by zhiweipan on 2017/7/27.
 */
public class Constants {

    /**
     * Zuul Filter Types
     */
    static final String PRE_TYPE = "pre";
    static final String ROUTING_TYPE = "routing";
    static final String POST_TYPE = "post";
    static final String ERROR_TYPE = "error";
    /**
     * Http Status Codes
     */
    static final int UNAUTHORIZED = 401;

    /**
     * 接口调用时间与当前系统时间的默认最大时间差
     * 默认150s（150000 ms）
     */
    static final long DEFAULT_TIME_DIFFERENCE = 1500000;
}
