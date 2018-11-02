package br.edu.unifei.controller;

import com.github.sarxos.webcam.Webcam;

import java.awt.Dimension;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
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



import br.edu.unifei.model.Screenshot;
import br.edu.unifei.model.User;
import br.edu.unifei.service.ScreenshotService;
import br.edu.unifei.service.UserService;
import br.edu.unifei.util.AuthenticationList;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public class RestApiController {
 
    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
    public static final AuthenticationList authenticationList = AuthenticationList.getInstance();
    @Autowired
    ScreenshotService screenshotService;
    @Autowired
    UserService userService;
    
    /********** Login 
     * @throws NoSuchAlgorithmException **********/
    @CrossOrigin(origins = "*" )
    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody User user) throws IOException, NoSuchAlgorithmException {
        List<User> usersFromDb = userService.findAllUsers();
        String token = authenticationList.authenticate(usersFromDb, user); 
    	switch(token) {
    		case "404":
    			return new ResponseEntity(HttpStatus.NOT_FOUND);
    		case "400":
    			return new ResponseEntity(HttpStatus.BAD_REQUEST);
    		default:
    			return new ResponseEntity<String>(authenticationList.tokenToJson(token),HttpStatus.ACCEPTED);
    	}
    }
    
    /********** Logout
     * @throws NoSuchAlgorithmException **********/
    @CrossOrigin(origins = "*" )
    @RequestMapping(value = "/logout/", method = RequestMethod.GET)
    public ResponseEntity logout(@RequestHeader String authenticationToken) throws IOException, NoSuchAlgorithmException {
    	
		logger.info("Procurando autenticação na lista...");
		Iterator<String> itr = AuthenticationList.getList().iterator();
		while(itr.hasNext())
		{
			if (itr.next().compareTo(authenticationToken) == 0)
			{
				logger.info("Autenticação encontrada, deletando...");
				itr.remove();
				return new ResponseEntity(HttpStatus.OK);
			}
		}
		logger.info("Token de autenticação inválido.");
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
    /********** Register **********/
    @CrossOrigin(origins = "*" )
    @RequestMapping(value = "/register/", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody User user) throws IOException {
        logger.info("Buscando usuário: " + user.getName() + " "+ user.getPassword());
        
        List<User> users = userService.findAllUsers();
        Iterator itr = users.iterator();
        User userFromDb = new User();
        while(itr.hasNext()) {
        	userFromDb = (User) itr.next();
        	if (userFromDb.getName().compareTo(user.getName()) == 0)
        	{
        		logger.info("Usuário já existente, tente outro");
        		return new ResponseEntity(HttpStatus.BAD_REQUEST);
        	}
        }
    	logger.info("Usuário não encontrado, criando...");
		userService.saveUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    /********** Take Screenshot **********/    
  
    @CrossOrigin(origins = "*" )
    @RequestMapping(value = "/screenshot/", method = RequestMethod.POST)
    public ResponseEntity<String> takeSs(@RequestHeader String authenticationToken) throws IOException {
    	
    	logger.info("Recebendo token de autenticação: " + authenticationToken)	;
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Tirando SS");
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));
        webcam.open();
        Screenshot screenshot = new Screenshot(webcam.getImage());
        webcam.close();
        screenshotService.saveScreenshot(screenshot);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    /********** Get All Screenshots **********/    
    
    @CrossOrigin(origins = "*" )
    @RequestMapping(value = "/screenshot/", method = RequestMethod.GET)
    public ResponseEntity<List<Screenshot>> listAllScreenshots(@RequestHeader String authenticationToken) {
    	
    	logger.info("Receiving authentication token: " + authenticationToken);
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
    	List<Screenshot> screenshots = screenshotService.findAllScreenshots();
        if (screenshots.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Screenshot>>(screenshots, HttpStatus.OK);
    }
    
    /********** Get Single Screenshot **********/
    
	@RequestMapping(value = "/screenshot/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getScreenshot(@RequestHeader String authenticationToken, @PathVariable("id") long id) {
		
		if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Fetching Screenshot with id {}", id);
        Screenshot screenshot = screenshotService.findById(id);
        if (screenshot == null) {
            logger.error("Album with id {} not found.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Screenshot>(screenshot, HttpStatus.OK);
    }
    
	/********** Delete Single Screenshot **********/
    
    @RequestMapping(value = "/screenshot/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteScreenshot(@RequestHeader String authenticationToken,@PathVariable("id") long id) {
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	
        logger.info("Fetching & Deleting Screenshot with id {}", id);
        Screenshot screenshot = screenshotService.findById(id);
        if (screenshot == null) {
            logger.error("Unable to delete. Screenshot with id {} not found.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        screenshotService.deleteScreenshotById(id);
        return new ResponseEntity<Screenshot>(HttpStatus.NO_CONTENT);
    }
    public boolean isAuthenticated(String authenticationToken)
    {
    	return authenticationList.checkAuthentication(authenticationToken);
    }

}
