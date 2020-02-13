package com.javatechie.spring.insurance.clinet.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RefreshScope
public class InsuranceClientApplication {

	@Autowired
	@Lazy
	private RestTemplate template;

	@Value("${insurnace.provider.url}")
	private String url;
	
	@Value("${title}")
	private String title;

	
	@Value("${microservice4.url}")
	private String newUrl;

	
	@GetMapping("/getPlan")
	public List<String> getPlans() {
		System.out.println(title);
		System.out.println("===>"+url+"===>");
		List<String> plans = template.getForObject(url, List.class);
		return plans;
	}

	@GetMapping("/getTest")
	public String getTest() {
		System.out.println("===>"+newUrl+"===>");
		String str = template.getForObject(newUrl, String.class);
		return str;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(InsuranceClientApplication.class, args);
	}

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}

}
