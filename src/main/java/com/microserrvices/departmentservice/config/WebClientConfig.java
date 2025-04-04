package com.microserrvices.departmentservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.microserrvices.departmentservice.client.EmployeeClient;

@Configuration
public class WebClientConfig {

	@Autowired
	private LoadBalancedExchangeFilterFunction filterFunction;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplateBean() {
		return new RestTemplate();
	}

	@Bean
	public WebClient empWebClient() {
		return WebClient.builder().baseUrl("http://EMPLOYEE-SERVICE").filter(filterFunction).build();

	}

	@Bean
	public EmployeeClient employeeClient() {
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(empWebClient()))
				.build();
		return factory.createClient(EmployeeClient.class);
	}
}
