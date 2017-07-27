eurekaserver:
   服务注册中心,端口设为8761
   输入url： localhost:8761/   可以进入注册中心看到信息

eurekaclient：
    一个服务，注册到 注册中心中，服务逻辑是打印一条信息显示来源
    输入url： localhost:8763/hi?name=xxxx   可以输入来源

serviceribbon：
    实现了负载均衡和容错机制

apigateway：
    实现网关的功能 ，加入zuul依赖，并加入eureka依赖把自身注册到服务注册中心
    实现了路由映射：/hi/**  ->   serivce-hi
    输入url: localhost:8765/hi/hi?name=xxxx  即调用了 service-hi服务的 /hi?name=xxx接口
    （localhost:8763/hi?name=xxxx）