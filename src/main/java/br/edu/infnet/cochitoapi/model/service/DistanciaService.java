package br.edu.infnet.cochitoapi.model.service;

import org.springframework.stereotype.Service;

import br.edu.infnet.cochitoapi.model.clients.DistanciaFeignClient;
import br.edu.infnet.cochitoapi.model.domain.DistanciaQueryResult;


import java.math.BigDecimal;


@Service
public class DistanciaService {

    private final DistanciaFeignClient distanciaFeignClient;
    
    // Taxa de frete por km em reais
    private static final BigDecimal TAXA_KM = new BigDecimal("2.50");
    
    public DistanciaService(DistanciaFeignClient distanciaFeignClient) {
        this.distanciaFeignClient = distanciaFeignClient;
    }
    
    /**
     * Calcula a distância entre o CEP do cliente e o CEP da loja
     * @param cepCliente CEP do cliente
     * @param cepLoja CEP da loja (padrão se não informado)
     * @return resultado da consulta de distância
     */
    public DistanciaQueryResult consultarDistancia(String cepOrigem, String cepCliente) {
        if (cepOrigem == null || cepOrigem.trim().isEmpty()) {
        	cepOrigem = "38065065"; // CEP padrão da loja
        }
        
        try {
            return distanciaFeignClient.calcularDistancia(cepOrigem, cepCliente);
        } catch (Exception e) {
            // Log do erro
            System.err.println("Erro ao consultar distância: " + e.getMessage());
            return null;
        }
    }
    


}
