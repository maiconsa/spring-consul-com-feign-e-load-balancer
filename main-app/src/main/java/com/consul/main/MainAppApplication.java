package com.consul.main;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@RestController
public class MainAppApplication {

	private static final Logger logger = LoggerFactory.getLogger(MainAppApplication.class);
	
	@Autowired
	private BuscarDetalhesService buscarDetalheService;
	
	public static void main(String[] args) {
		
		SpringApplication.run(MainAppApplication.class, args);
	}
	
	@GetMapping("/details")
	public Map<String,Object> detalhesAplicacao() {
		
		Map<String,Object>  response = buscarDetalheService.buscaDetalhe();
		logger.debug("Response requisição app-service/details : " + response.toString());
		return  response;
	}

}
