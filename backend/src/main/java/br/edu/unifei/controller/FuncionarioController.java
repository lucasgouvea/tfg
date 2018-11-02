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

import br.edu.unifei.model.Funcionario;
import br.edu.unifei.service.FuncionarioService;
import br.edu.unifei.service.UserService;
import br.edu.unifei.util.AuthenticationList;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
@CrossOrigin(origins = "*" )
public class FuncionarioController {
 
    public static final Logger logger = LoggerFactory.getLogger(FuncionarioController.class);
    public static final AuthenticationList authenticationList = AuthenticationList.getInstance();
    
    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    UserService userService;

    /********** Criar Funcionario **********/    
  
    @RequestMapping(value = "/funcionario/", method = RequestMethod.POST)
    public ResponseEntity<String> createFuncionario(@RequestHeader String authenticationToken,@RequestBody Funcionario funcionario) throws IOException {
    	
    	logger.info("Recebendo token de autenticação: " + authenticationToken)	;
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Criando funcionario");
        funcionarioService.saveFuncionario(funcionario);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    /********** Buscar todos os Funcionarios **********/    
    
    @RequestMapping(value = "/funcionario/", method = RequestMethod.GET)
    public ResponseEntity<List<Funcionario>> listAllFuncionarios(@RequestHeader String authenticationToken) {
    	
    	logger.info("Recebendo token de autenticação: " + authenticationToken);
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
    	List<Funcionario> funcionarios = funcionarioService.findAllFuncionarios();
        if (funcionarios.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Funcionario>>(funcionarios, HttpStatus.OK);
    }
    
    /********** Pegar Funcionario único **********/
    
	@RequestMapping(value = "/funcionario/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getFuncionario(@RequestHeader String authenticationToken, @PathVariable("id") long id) {
		
		if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Buscando funcionario com o id {}", id);
        Funcionario funcionario = funcionarioService.findById(id);
        if (funcionario == null) {
            logger.error("Funcionario com id {} não foi encontrado.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Funcionario>(funcionario, HttpStatus.OK);
    }
	
	/********** Atualizar Funcionario **********/
    
    @RequestMapping(value = "/funcionario/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateFuncionario(@RequestHeader String authenticationToken,@PathVariable("id") long id,@RequestBody Funcionario funcionarioNew) {
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Buscando o funcionario com o id {}", id);
        Funcionario funcionarioOld = funcionarioService.findById(id);
        if (funcionarioOld == null) {
            logger.error("Não foi possível atualizar. O Funcionario com id {} não foi encontrado.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        funcionarioService.updateFuncionario(funcionarioNew);
        return new ResponseEntity<Funcionario>(HttpStatus.ACCEPTED);
    }
    
	/********** Deletar Funcionario **********/
    
    @RequestMapping(value = "/funcionario/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFuncionario(@RequestHeader String authenticationToken,@PathVariable("id") long id) {
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Buscando e deletando funcionario com o id {}", id);
        Funcionario Funcionario = funcionarioService.findById(id);
        if (Funcionario == null) {
            logger.error("Não foi possível deletar. O Funcionario com id {} não foi encontrado.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        funcionarioService.deleteFuncionarioById(id);
        return new ResponseEntity<Funcionario>(HttpStatus.NO_CONTENT);
    }
    
    public boolean isAuthenticated(String authenticationToken)
    {
    	return authenticationList.checkAuthentication(authenticationToken);
    }
 
}
