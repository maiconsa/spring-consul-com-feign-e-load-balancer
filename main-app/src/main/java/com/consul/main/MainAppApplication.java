package com.consul.main;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@RestController
public class MainAppApplication {

	
	@Autowired
	private BuscarDetalhesService buscarDetalheService;
	
	public static void main(String[] args) {
		
		SpringApplication.run(MainAppApplication.class, args);
	}
	
	@GetMapping("/details")
	public Map<String,Object> detalhesAplicacao() {
		return buscarDetalheService.buscaDetalhe();
	}

}
