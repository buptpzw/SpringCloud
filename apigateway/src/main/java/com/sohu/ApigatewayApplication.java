package com.sohu;

//import com.netflix.discovery.DiscoveryClient;
import com.netflix.zuul.ZuulFilter;
import com.sohu.filter.ApiAuthFilter;
import com.sohu.filter.ApiRateLimitFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.sohu.mapper")
public class ApigatewayApplication {
	@Bean
	public ZuulFilter apiAuthFilter(){
		return new ApiAuthFilter();
	}

	@Bean
	public ZuulFilter apiRateLimitFilter(){
		return new ApiRateLimitFilter();
	}

	@Bean
	CommandLineRunner runner(@Qualifier("discoveryClient")DiscoveryClient dc) {
		return args -> {
			dc.getInstances("service-hi")
					.forEach(si -> System.out.println(String.format(
							"Found %s %s:%s", si.getServiceId(), si.getHost(), si.getPort())));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}
}
