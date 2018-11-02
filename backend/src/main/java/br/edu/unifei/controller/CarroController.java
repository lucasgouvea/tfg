package br.edu.unifei.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifei.model.Carro;
import br.edu.unifei.service.CarroService;
import br.edu.unifei.service.UserService;
import br.edu.unifei.util.AuthenticationList;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
@CrossOrigin(origins = "*" )
public class CarroController {
 
    public static final Logger logger = LoggerFactory.getLogger(CarroController.class);
    public static final AuthenticationList authenticationList = AuthenticationList.getInstance();
    
    @Autowired
    CarroService carroService;
    @Autowired
    UserService userService;

    /********** Criar Carro **********/    
  
    
    @RequestMapping(value = "/carro/", method = RequestMethod.POST)
    public ResponseEntity<String> createCarro(@RequestHeader String authenticationToken,@RequestBody Carro carro) throws IOException {
    	
    	logger.info("Recebendo token de autenticação: " + authenticationToken)	;
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Criando carro");
        carroService.saveCarro(carro);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    /********** Buscar todos os Carros **********/    
    
    @RequestMapping(value = "/carro/", method = RequestMethod.GET)
    public ResponseEntity<List<Carro>> listAllCarros(@RequestHeader String authenticationToken) {
    	
    	logger.info("Recebendo token de autenticação: " + authenticationToken);
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
    	List<Carro> carros = carroService.findAllCarros();
        if (carros.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Carro>>(carros, HttpStatus.OK);
    }
    
    /********** Pegar Carro único **********/
    
	@RequestMapping(value = "/carro/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCarro(@RequestHeader String authenticationToken, @PathVariable("id") long id) {
		
		if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Buscando carro com o id {}", id);
        Carro carro = carroService.findById(id);
        if (carro == null) {
            logger.error("Carro com id {} não foi encontrado.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Carro>(carro, HttpStatus.OK);
    }
	
	/********** Atualizar Carro **********/
    
    @RequestMapping(value = "/carro/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCarro(@RequestHeader String authenticationToken,@PathVariable("id") long id,@RequestBody Carro carroNew) {
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Buscando o carro com o id {}", id);
        Carro carroOld = carroService.findById(id);
        if (carroOld == null) {
            logger.error("Não foi possível atualizar. O Carro com id {} não foi encontrado.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        carroService.updateCarro(carroNew);
        return new ResponseEntity<Carro>(HttpStatus.ACCEPTED);
    }
    
	/********** Deletar Carro **********/
    
    @RequestMapping(value = "/carro/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCarro(@RequestHeader String authenticationToken,@PathVariable("id") long id) {
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Buscando e deletando carro com o id {}", id);
        Carro Carro = carroService.findById(id);
        if (Carro == null) {
            logger.error("Não foi possível deletar. O Carro com id {} não foi encontrado.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        carroService.deleteCarroById(id);
        logger.info("Carro com id {} foi deletado.",id);
        return new ResponseEntity<Carro>(HttpStatus.OK);
    }
    
    public boolean isAuthenticated(String authenticationToken)
    {
    	return authenticationList.checkAuthentication(authenticationToken);
    }
 
}
