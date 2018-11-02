package br.edu.unifei.controller;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;





@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
@CrossOrigin(origins = "*" )
public class BrokerController {

    public static final Logger logger = LoggerFactory.getLogger(BrokerController.class);
    public String _message = null;
    
    @RequestMapping(value = "/broker/", method = RequestMethod.POST)
    public ResponseEntity<String> brokerListener(@RequestBody String message) throws IOException {
    	/*
    	logger.info("Recebendo token de autenticação: " + authenticationToken)	;
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	*/
    	
        logger.info(message);
        _message = message;
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/broker/", method = RequestMethod.GET)
    public ResponseEntity<String> brokerGetMessage() throws IOException {
    	/*
    	logger.info("Recebendo token de autenticação: " + authenticationToken)	;
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	*/
    	
    	String message = "{ \"message:\" : " + _message + " }";
        return new ResponseEntity(message, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/broker-web/", method = RequestMethod.POST)
    public ResponseEntity<String> webPost(@RequestBody String webMessage) throws IOException, MqttException {
    	/*
    	logger.info("Recebendo token de autenticação: " + authenticationToken)	;
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	*/
        logger.info(webMessage);
    	MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
    	client.connect();
    	MqttMessage mqttMessage = new MqttMessage();
    	mqttMessage.setPayload(webMessage.getBytes());
    	client.publish("iot/interruptor", mqttMessage);
    	client.disconnect();
    	return new ResponseEntity(HttpStatus.OK);
    }
}
