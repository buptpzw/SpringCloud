package com.sohu;

import com.netflix.zuul.ZuulFilter;
import com.sohu.filter.ApiAuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ApigatewayApplication {
	@Bean
	public ZuulFilter apiAuthFilter(){
		return new ApiAuthFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}
}
