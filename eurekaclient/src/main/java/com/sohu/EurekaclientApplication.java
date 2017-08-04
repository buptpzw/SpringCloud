package com.sohu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class EurekaclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaclientApplication.class, args);
	}

	@Value("${server.port}")
	String port;

	@RequestMapping("/hi")
	public String hi(@RequestParam String appKey){
		return "hello "+appKey+",i am from port:"+port +"/hi";
	}

	@RequestMapping("/hi/hi")
	public String hihi(@RequestParam String appKey){
		return "hello "+appKey+",i am from port:"+port +"/hi/hi";
	}

	@RequestMapping("/print")
	public String print(){
		return "test OK: print";
	}
}
