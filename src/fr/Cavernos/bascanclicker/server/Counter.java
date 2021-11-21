package fr.Cavernos.bascanclicker.server;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Counter implements HttpHandler{
	public void handle(HttpExchange httpExchange) throws IOException {
		StringBuilder response = new StringBuilder();
		Map <String,String>parms = BasicServer.queryToMap(httpExchange.getRequestURI().getQuery());
		if(JsonReader.main(parms.get("count"))){
			String cookie = httpExchange.getRemoteAddress().getAddress().toString();
			String encryptKey = BasicServer.encrypt(cookie + BasicServer.serverToken, BasicServer.serverKey);
			if(BasicServer.decrypt(encryptKey, BasicServer.serverKey).equals(cookie + BasicServer.serverToken)){
				response.append("File is Writed");
				@SuppressWarnings("resource")
				FileWriter test = new FileWriter("counter.txt");
				test.write("1");
				test.flush();
				
			
			} else{
				response.append("200");
			}
				
		 } else {
			 response.append("400");
		 }
		BasicServer.writeResponse(httpExchange, response.toString());
	}

}
