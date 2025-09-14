package br.edu.infnet.cochitoapi.model.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.infnet.cochitoapi.model.domain.DistanciaQueryResult;

@FeignClient(name = "distanciaClient", url = "${cochitoservicoapi.url}")
public interface DistanciaFeignClient {
    
    @GetMapping("/api/servicos/distancia")
    DistanciaQueryResult calcularDistancia(
        @RequestParam("cepOrigem") String cepOrigem,
        @RequestParam("cepDestino") String cepDestino
    );
}