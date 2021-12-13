package fr.Cavernos.bascanclicker.server;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Counter implements HttpHandler{
    Integer count = 0;
    Integer countSeconde = 0;
    Integer countPremière = 0;
    Integer countTerminale = 0;
    FileWriter counter;
    FileWriter counterSeconde;
    FileWriter counterPremière;
    FileWriter counterTerminale;
	public void handle(HttpExchange httpExchange) throws IOException {
		httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin","*");
		StringBuilder response = new StringBuilder();
		Map <String,String>parms = BasicServer.queryToMap(httpExchange.getRequestURI().getQuery());
		if(JsonReader.main(parms.get("count"))+ BasicServer.encryptKey != null){
			String cookie = httpExchange.getRemoteAddress().getAddress().toString();
			if(BasicServer.decrypt(BasicServer.encryptKey, BasicServer.serverKey).equals(cookie + BasicServer.serverToken)){
				count++; 
				response.append("File is Writed");
				counter = new FileWriter("counter.txt");
				counter.write(count.toString());
				counter.flush();
				
			
			} else{
				response.append("400");
			}
				
		 } 
		if (JsonReader.main(parms.get("seconde"))+ BasicServer.encryptKey != null){
			 String cookie = httpExchange.getRemoteAddress().getAddress().toString();
				if(BasicServer.decrypt(BasicServer.encryptKey, BasicServer.serverKey).equals(cookie + BasicServer.serverToken)){
					countSeconde++; 
					response.append("File is Writed");
					counterSeconde = new FileWriter("counterSeconde.txt");
					counterSeconde.write(count.toString());
					counterSeconde.flush();
					
				
				} else{
					response.append("400");
				}
		 }
		if(JsonReader.main(parms.get("première"))+ BasicServer.encryptKey != null){
			 String cookie = httpExchange.getRemoteAddress().getAddress().toString();
				if(BasicServer.decrypt(BasicServer.encryptKey, BasicServer.serverKey).equals(cookie + BasicServer.serverToken)){
					countPremière++; 
					response.append("File is Writed");
					counterPremière = new FileWriter("counterPremière.txt");
					counterPremière.write(count.toString());
					counterPremière.flush();
					
				
				} else{
					response.append("400");
				}
			 
		 }
		if(JsonReader.main(parms.get("terminale"))+ BasicServer.encryptKey != null){
			 String cookie = httpExchange.getRemoteAddress().getAddress().toString();
				if(BasicServer.decrypt(BasicServer.encryptKey, BasicServer.serverKey).equals(cookie + BasicServer.serverToken)){
					countTerminale++; 
					response.append("File is Writed");
					counterTerminale = new FileWriter("counterTerminale.txt");
					counterTerminale.write(count.toString());
					counterTerminale.flush();
					
				
				} else{
					response.append("400");
				}
		 }
		BasicServer.writeResponse(httpExchange, response.toString());
	}

}
