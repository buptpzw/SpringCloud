package com.sohu;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pzw on 2017/7/9.
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name){
        // RestTemplate向外部发送GWT请求，
        // url: http://SERVICE-HI/hi?name=name   返回类型为String
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }

    public String hiError(String name){
        return "hi,"+name+",sorry,error!";
    }
}
