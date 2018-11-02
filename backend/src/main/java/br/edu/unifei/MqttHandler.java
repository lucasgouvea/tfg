package br.edu.unifei;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import br.edu.unifei.util.MqttHandlerCallback;

public class MqttHandler {

	
	public static void main(String args[]) throws MqttException {
		MqttClient client = new MqttClient( "tcp://127.0.0.1:1883", MqttClient.generateClientId());
		client.connect();
		client.setCallback(new MqttHandlerCallback());
		client.subscribe("iot/interruptor");
	}
}
