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

import br.edu.unifei.model.Viagem;
import br.edu.unifei.service.ViagemService;
import br.edu.unifei.service.UserService;
import br.edu.unifei.util.AuthenticationList;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
@CrossOrigin(origins = "*" )
public class ViagemController {
 
    public static final Logger logger = LoggerFactory.getLogger(ViagemController.class);
    public static final AuthenticationList authenticationList = AuthenticationList.getInstance();
    
    @Autowired
    ViagemService viagemService;
    @Autowired
    UserService userService;

    /********** Criar Viagem **********/    
  
    @RequestMapping(value = "/viagem/", method = RequestMethod.POST)
    public ResponseEntity<String> createViagem(@RequestHeader String authenticationToken,@RequestBody Viagem viagem) throws IOException {
    	
    	logger.info("Recebendo token de autenticação: " + authenticationToken)	;
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Criando viagem");
        viagemService.saveViagem(viagem);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    /********** Buscar todos os Viagems **********/    
    
    @RequestMapping(value = "/viagem/", method = RequestMethod.GET)
    public ResponseEntity<List<Viagem>> listAllViagems(@RequestHeader String authenticationToken) {
    	
    	logger.info("Recebendo token de autenticação: " + authenticationToken);
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
    	List<Viagem> viagems = viagemService.findAllViagems();
        if (viagems.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Viagem>>(viagems, HttpStatus.OK);
    }
    
    /********** Pegar Viagem único **********/
    
	@RequestMapping(value = "/viagem/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getViagem(@RequestHeader String authenticationToken, @PathVariable("id") long id) {
		
		if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Buscando viagem com o id {}", id);
        Viagem viagem = viagemService.findById(id);
        if (viagem == null) {
            logger.error("Viagem com id {} não foi encontrado.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Viagem>(viagem, HttpStatus.OK);
    }
	
	/********** Atualizar Viagem **********/
    
    @RequestMapping(value = "/viagem/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateViagem(@RequestHeader String authenticationToken,@PathVariable("id") long id,@RequestBody Viagem viagemNew) {
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Buscando o viagem com o id {}", id);
        Viagem viagemOld = viagemService.findById(id);
        if (viagemOld == null) {
            logger.error("Não foi possível atualizar. O Viagem com id {} não foi encontrado.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        viagemService.updateViagem(viagemNew);
        return new ResponseEntity<Viagem>(HttpStatus.ACCEPTED);
    }
    
	/********** Deletar Viagem **********/
    
    @RequestMapping(value = "/viagem/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteViagem(@RequestHeader String authenticationToken,@PathVariable("id") long id) {
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Buscando e deletando viagem com o id {}", id);
        Viagem Viagem = viagemService.findById(id);
        if (Viagem == null) {
            logger.error("Não foi possível deletar. O Viagem com id {} não foi encontrado.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        viagemService.deleteViagemById(id);
        return new ResponseEntity<Viagem>(HttpStatus.NO_CONTENT);
    }
    
    public boolean isAuthenticated(String authenticationToken)
    {
    	return authenticationList.checkAuthentication(authenticationToken);
    }
 
}
