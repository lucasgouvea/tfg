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

import br.edu.unifei.model.Interruptor;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
@CrossOrigin(origins = "*" )
public class BrokerController {

    public static final Logger logger = LoggerFactory.getLogger(BrokerController.class);
    public Interruptor _interruptor = new Interruptor(1, false);
    
    @RequestMapping(value = "/broker/", method = RequestMethod.POST)
    public ResponseEntity<String> brokerListener(@RequestBody String message) throws IOException {

    	if(message.compareTo("off=") == 0) {
    		logger.info("Off");
    		_interruptor.setLigado(false);
    	} else {
    		logger.info("On");
    		_interruptor.setLigado(true);
    	}
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/broker/", method = RequestMethod.GET)
    public ResponseEntity<String> brokerGetMessage() throws IOException {
    	/*
    	logger.info("Recebendo token de autenticação: " + authenticationToken)	;
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	*/
    	
    	String message = String.valueOf(_interruptor.isLigado());
        return new ResponseEntity(message, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/broker-web/", method = RequestMethod.POST)
    public ResponseEntity<String> webPost(@RequestBody Interruptor interruptor) throws IOException, MqttException {
    	/*
    	logger.info("Recebendo token de autenticação: " + authenticationToken)	;
    	
    	if(!isAuthenticated(authenticationToken)) { return new ResponseEntity(HttpStatus.FORBIDDEN); }
    	*/
    	String payload;
    	if(interruptor.isLigado()) {
    		payload = "on";
    	} else {
    		payload = "off";
    	}
    	MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
    	client.connect();
    	MqttMessage mqttMessage = new MqttMessage();
    	mqttMessage.setPayload(payload.getBytes());
    	client.publish("iot/interruptor", mqttMessage);
    	client.disconnect();
    	return new ResponseEntity(HttpStatus.OK);
    }
}
