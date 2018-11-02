package br.edu.unifei.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.unifei.controller.RestApiController;
import br.edu.unifei.model.User;

//Singleton
public final class AuthenticationList {
	private static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
	private static final AuthenticationList INSTANCE = new AuthenticationList();
	private static final List<String> list = new ArrayList<>();
	private AuthenticationList() {
		
	}
	public static AuthenticationList getInstance() {
		return INSTANCE;
	}
	public static List<String> getList(){
		return list;
	}
	
	//Autenticação
	public String authenticate(List<User> usersFromDb,User user) throws NoSuchAlgorithmException {

        logger.info("Buscando usuário: " + user.getName() + " "+ user.getPassword());
        Iterator<User> itr = usersFromDb.iterator();
        User userFromDb = new User(); 
        boolean userFound = false;
        while(itr.hasNext()) {
        	userFromDb = (User) itr.next();
        	if (userFromDb.getName().compareTo(user.getName()) == 0)
        	{
        		logger.info("Usuário encontrado.");
        		if (user.getPassword().compareTo(userFromDb.getPassword()) == 0)
        		{
        			userFound = true;
        			logger.info("Password coressponde!");
        			String token = generateToken();
        			list.add(token);
        			return token;
        		}
        		else
        		{
        			logger.info("Password não corresponde.");
        			return "400";
        		}
        	}
        }
        if(!userFound)
        {
        	logger.info("Usuário não encontrado");
            return "404";
        }
        return "400";
	}
	
	//Checa se autenticação é válida
	public boolean checkAuthentication(String authenticationToken)
	{
		logger.info("Checando token de autenticação...");
		Iterator<String> itr = list.iterator();
		while(itr.hasNext())
		{
			if (itr.next().compareTo(authenticationToken) == 0)
			{
				logger.info("Token de autenticação válido.");
				return true;
			}
		}
		logger.info("Token de autenticação inválido.");
		return false;	
	}
	
	//Gera token usando SHA-1
	public String generateToken() throws NoSuchAlgorithmException
	{
	    Random random = new Random();
	    String toBeDigested = String.valueOf(random);
	    MessageDigest messageDigest;
	    messageDigest = MessageDigest.getInstance("SHA-1");
	    messageDigest.update(toBeDigested.getBytes());
	    byte[] authCodeMD5 = messageDigest.digest();	
	    StringBuffer stringBuffer = new StringBuffer();
	    for (byte bytes : authCodeMD5)
	    {
	      stringBuffer.append(String.format("%02x", bytes & 0xff));
	    }
	    logger.info("Gerado token de autenticação:" + stringBuffer.toString());
	    return stringBuffer.toString();		
	}
	
	public String tokenToJson(String token) {
		return "{ \"token\" : "+"\""+ token +"\" }";
	}
}
