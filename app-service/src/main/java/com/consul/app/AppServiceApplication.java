package com.consul.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AppServiceApplication {

	@Value("${spring.application.name}")
	private String appName;

	
	@Autowired
	private ConsulDiscoveryProperties consulProperties;

	public static void main(String[] args) {
		SpringApplication.run(AppServiceApplication.class, args);
	}
	
	@GetMapping("/details")
	public Map<String,Object> detalhesAplicacao() {
		String instanceId = consulProperties.getInstanceId();
		fakeDelayRequest();
		return Map.of("nome-aplicacao",appName , "instanceId" ,instanceId);
	}

	private void fakeDelayRequest() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
