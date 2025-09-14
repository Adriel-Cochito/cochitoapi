package br.edu.infnet.cochitoapi.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.cochitoapi.model.domain.DistanciaQueryResult;
import br.edu.infnet.cochitoapi.model.service.DistanciaService;

@RestController
@RequestMapping("/api/distancia")
public class DistanciaController {

	
	private final DistanciaService distanciaService;
	
	public DistanciaController(DistanciaService distanciaService) {
        this.distanciaService = distanciaService;
    }
	
	@GetMapping("/consulta/{cepOrigem}/{cepCliente}")
    public ResponseEntity<DistanciaQueryResult> consultarDistancia(@PathVariable String cepOrigem,
    		@PathVariable String cepCliente) {
        DistanciaQueryResult resultado = distanciaService.consultarDistancia(cepOrigem, cepCliente);
        
        System.out.println("cepOrigem: " + cepOrigem);
        System.out.println("cepCliente: " + cepCliente);
        
        if (resultado == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(resultado);
    }
}
