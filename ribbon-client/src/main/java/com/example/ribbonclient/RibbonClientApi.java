package com.example.ribbonclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RibbonClient(value = "library-service", configuration = RibbonClientConfiguration.class)
@RestController
@RequestMapping("/")
public class RibbonClientApi {
	
	@Autowired
    RestTemplate restTemplate;
	
	@GetMapping("/ping")
	public String ping() {
		
		return this.restTemplate.getForObject(
		          "http://library-service/library/ping", String.class);
	}

}
