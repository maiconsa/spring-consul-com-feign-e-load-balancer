package com.consul.main;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "app-service"  )
public interface BuscarDetalhesService {
	
	@GetMapping(path = "/details"  )
	public Map<String, Object> buscaDetalhe();
}
