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

import br.edu.unifei.model.Empresa;
import br.edu.unifei.service.EmpresaService;
import br.edu.unifei.service.UserService;
import br.edu.unifei.util.AuthenticationList;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
@CrossOrigin(origins = "*" )
public class EmpresaController {
 
    public static final Logger logger = LoggerFactory.getLogger(EmpresaController.class);
    public static final AuthenticationList authenticationList = AuthenticationList.getInstance();
    
    @Autowired
    EmpresaService empresaService;
    @Autowired
    UserService userService;

    /********** Criar Empresa **********/    
  
    
    @RequestMapping(value = "/empresa/", method = RequestMethod.POST)
    public ResponseEntity<String> createEmpresa(@RequestHeader String authenticationToken,@RequestBody Empresa empresa) throws IOException {
    	
    	logger.info("Recebendo token de autenticação: " + authenticationToken)	;
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Criando empresa");
        empresaService.saveEmpresa(empresa);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    /********** Buscar todos os Empresas **********/    
    
    @RequestMapping(value = "/empresa/", method = RequestMethod.GET)
    public ResponseEntity<List<Empresa>> listAllEmpresas(@RequestHeader String authenticationToken) {
    	
    	logger.info("Recebendo token de autenticação: " + authenticationToken);
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
    	List<Empresa> empresas = empresaService.findAllEmpresas();
        if (empresas.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.info(empresas.toString());
        return new ResponseEntity<List<Empresa>>(empresas, HttpStatus.OK);
    }
    
    /********** Pegar Empresa único **********/
    
	@RequestMapping(value = "/empresa/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmpresa(@RequestHeader String authenticationToken, @PathVariable("id") long id) {
		
		if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Buscando empresa com o id {}", id);
        Empresa empresa = empresaService.findById(id);
        if (empresa == null) {
            logger.error("Empresa com id {} não foi encontrado.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
    }
	
	/********** Atualizar Empresa **********/
    
    @RequestMapping(value = "/empresa/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEmpresa(@RequestHeader String authenticationToken,@PathVariable("id") long id,@RequestBody Empresa empresaNew) {
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Buscando o empresa com o id {}", id);
        Empresa empresaOld = empresaService.findById(id);
        if (empresaOld == null) {
            logger.error("Não foi possível atualizar. O Empresa com id {} não foi encontrado.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        empresaService.updateEmpresa(empresaNew);
        return new ResponseEntity<Empresa>(HttpStatus.ACCEPTED);
    }
    
	/********** Deletar Empresa **********/
    
    @RequestMapping(value = "/empresa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmpresa(@RequestHeader String authenticationToken,@PathVariable("id") long id) {
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Buscando e deletando empresa com o id {}", id);
        Empresa Empresa = empresaService.findById(id);
        if (Empresa == null) {
            logger.error("Não foi possível deletar. O Empresa com id {} não foi encontrado.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        empresaService.deleteEmpresaById(id);
        return new ResponseEntity<Empresa>(HttpStatus.NO_CONTENT);
    }
    
    public boolean isAuthenticated(String authenticationToken)
    {
    	return authenticationList.checkAuthentication(authenticationToken);
    }
 
}
