package br.edu.unifei.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.apache.http.NameValuePair;

public class MqttHandler implements MqttCallback {

	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.print("Topic: " + topic + " Message: " + message.toString() + "\n");

		/*
		 * Create the POST request
		 */
		System.out.println("Creating http object");
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://127.0.0.1:1028/broker/");
		// Request parameters and other properties.
		System.out.println("Setting parameters");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("", message.toString()));
		try {
			System.out.println("Trying to set the Entity");
		    httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
		    // writing error to Log
			System.out.println("Problem:");
		    e.printStackTrace();
		}
		/*
		 * Execute the HTTP Request
		 */
		try {
			System.out.println("Trying to post");
		    HttpResponse response = httpClient.execute(httpPost);
		    HttpEntity respEntity = response.getEntity();
			System.out.println("Response entity:");
		    System.out.println(respEntity);
		    if (respEntity != null) {
		        // EntityUtils to get the response content
		        String content =  EntityUtils.toString(respEntity);
		    }
		} catch (ClientProtocolException e) {
			System.out.println("Problem"); 
		    e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problem");
		    e.printStackTrace();
		}
		
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

	
}
