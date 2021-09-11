package com.consul.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AppServiceApplication {

	@Value("${spring.application.name}")
	private String appName;

	
	@Autowired
	private Environment environment;

	
	@Autowired
	private ConsulDiscoveryProperties consulProperties;

	public static void main(String[] args) {
		SpringApplication.run(AppServiceApplication.class, args);
	}
	
	@GetMapping("/details")
	public Detalhe detalhesAplicacao() {
		String instanceId = consulProperties.getInstanceId();
		
		return new Detalhe(this.appName, instanceId, this.environment.getProperty("server.port").toString());
	}
	
	private static class Detalhe{
		private String nomeAplicacao;
		private String instanceId;
		private String porta;
		
		
		
		
		public Detalhe(String nomeAplicacao, String instanceId, String porta) {
			super();
			this.nomeAplicacao = nomeAplicacao;
			this.instanceId = instanceId;
			this.porta = porta;
		}
		public String getNomeAplicacao() {
			return nomeAplicacao;
		}
		public void setNomeAplicacao(String nomeAplicacao) {
			this.nomeAplicacao = nomeAplicacao;
		}
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
		public String getPorta() {
			return porta;
		}
		public void setPorta(String porta) {
			this.porta = porta;
		}
	}

}
