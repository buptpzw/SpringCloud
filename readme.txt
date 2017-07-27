eurekaserver:
   服务注册中心,端口设为8761
   输入url： localhost:8761/   可以进入注册中心看到信息

eurekaclient：
    一个服务，注册到 注册中心中，服务逻辑是打印一条信息显示来源
    输入url： localhost:8763/hi?name=xxxx   可以输入来源

serviceribbon：
    实现了负载均衡和容错机制

apigateway：
    实现网关的功能