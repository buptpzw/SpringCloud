package com.sohu.dao.entity;

/**
 * Created by zhiweipan on 2017/7/19.
 */
public class ApiProvider {

    /** 服务 id */
    private long id;

    /** 服务名 */
    private String serviceName;

    /** api名称或者是路径名 */
    private String apiName;

    /** 设计的访问率 */
    private Integer designedRate;

    /** 描述 */
    private String description;

    /** setter and getter */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Integer getDesignedRate() {
        return designedRate;
    }

    public void setDesignedRate(Integer designedRate) {
        this.designedRate = designedRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ApiProvider{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", apiName='" + apiName + '\'' +
                ", designedRate=" + designedRate +
                ", description='" + description + '\'' +
                '}';
    }
}
