# spring-consul-com-feign-e-load-balancer
Exemplo de uso do service discovery consul do ecossistemas utilizando Feign e Load Balancer.

## O que é Spring Consul?
Consiste de uma biblioteca presente no ecossistema Spring responsável por Service Discorevy (Descoberta de Serviços) de aplicações registradas em um servidor Consul através dos nomes dos serviços registrados.

## Por que utilizar Service Discovery?
 Através do uso de service discovery é possível identificar o endereço e porta de uma aplicação  simplesmente sabendo o nome do serviço dela registrado no servidor Consul. Logo, simplifica o processo de descoberta e requisição de um recurso  em um ambiente de microserviços complexo e com muitas aplicação  sendo executadas.Dessa maneira, quando se pretende trabalhar com LoadBalancer se torna essencial para a fácil identificação e balanceamento das requisições.

##  O será que utilizado de exemplo?

O servidor de registros de aplicações utilizado é o Consul, para nosso exemplo foi utilizado uma imagem docker Consul, mais detalhes em https://hub.docker.com/_/consul, rodando na porta 8500.

No nosso exemplo iremos ter duas aplicações,a primeira é  a app-service que está  registrada no servidor Consul como "app-service" e  possui um endpoint /details que responde com :
```json
{
    "porta": "XXXX",
    "nomeAplicacao": "app-service",
    "instanceId": "xxxx-xxx-xx-xx-xxx-xx-xx-xx-"
}
```
Abaixo temos 3 aplicações com serviceName app-service registradas no consul nas portas 9091,9092 e 9093.
![](https://github.com/maiconsa/spring-consul-com-feign-e-load-balancer/blob/main/imagens/lista-app-service-registrados-no-consul.png)

Enquanto que a main-app também possui um endpoint /details que faz uma requisição para o endpoint /details  da aplicação app-service utilizando o nome app-service na interface de requisição (Feign Client) BuscarDetalhesService.java (https://github.com/maiconsa/spring-consul-com-feign-e-load-balancer/blob/main/main-app/src/main/java/com/consul/main/BuscarDetalhesService.java). 
Abaixo temos a aplicação main-app registrada no servidor Consul.
![](https://github.com/maiconsa/spring-consul-com-feign-e-load-balancer/blob/main/imagens/main-app-no-consul.png)

## Pré requisitos
- Docker Instalado
- Java 11
- Apache Maven

## Como executar
- Execute o Servidor Consul  com docker conforme https://hub.docker.com/_/consul; 
- Gere o pacote do app-service com ```bsh mvn  [CAMINHO-ATE-APP-SERVICE]/ clean package ```.Execute o artefato  com o comando ```bsh java  -Dserver.port=XXXX  -jar [CAMINHO-ATE-APP-SERVICE]/target/app-service-0.0.1-SNAPSHOT.jar ``` execute quantas aplicações desejar alterando apenas a porta XXXX.
- Gere o pacote do main-app com ```bsh mvn  [CAMINHO-ATE-MAIN-APP]/ clean package ```
      - Execute o artefato   com o comando ```bsh java   -jar [CAMINHO-ATE-MAIN-APP]/target/app-service-0.0.1-SNAPSHOT.jar ``` por padrão a aplicação utilizará a porta 8080.

## Evidências de Funcionamento  Service Discovery e LoadBalancer 

Para realizar os testes de requisição foi utilizado o curl em ambiente linux. Na raiz no repositório tem um script em shell de nome script-requisicao-curl.sh. Para executar utilizando o comando ```bsh bash ./script-requisicao-curl.sh ```. 

Abaixo temos um exemplo de retorno das múltiplas requisições realizadas para http://localhost:8080/details .Portanto, é possível notar a partir da rota /details da  aplicação main-app estamos acessando o recurso /details da aplicação app-service, e  como temos vários app-service rodando em diferentes portas (9091,9092,9093) as requisições foram balanceadas pelo Spring LoadBalancer através do service discorvery Consul.Isso é notado pois as portas presentes na saída das requisições estão alternando entre 9091,9092 e 9093.

![](https://github.com/maiconsa/spring-consul-com-feign-e-load-balancer/blob/main/imagens/evidencia-load-balancer.png)

