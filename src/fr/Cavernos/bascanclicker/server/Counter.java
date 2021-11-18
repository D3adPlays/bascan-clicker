package fr.Cavernos.bascanclicker.server;

import java.io.IOException;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Counter implements HttpHandler{
	public void handle(HttpExchange httpExchange) throws IOException {
		StringBuilder response = new StringBuilder();
		Map <String,String>parms = BasicServer.queryToMap(httpExchange.getRequestURI().getQuery());
		String cookie = httpExchange.getRemoteAddress().getAddress().toString();
		String encryptKey = BasicServer.encrypt(cookie + BasicServer.serverToken, BasicServer.serverKey);
		if (parms.get("count") + BasicServer.decrypt(encryptKey, BasicServer.serverKey).equals(cookie + BasicServer.serverKey) != null) {
			response.append("yes");
		}
		
	}

}
