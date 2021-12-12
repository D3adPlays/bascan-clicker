package fr.Cavernos.bascanclicker.server;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Counter implements HttpHandler{
    Integer count = 0;
    FileWriter counter;

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
		BasicServer.writeResponse(httpExchange, response.toString());
	}

}
