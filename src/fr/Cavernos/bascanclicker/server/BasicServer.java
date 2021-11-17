package fr.Cavernos.bascanclicker.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class BasicServer {
	
	public final static String serverKey = "6Ld1iTsdAAAAAIVUgfanoRz_nmCCnez_pWKVcz9n";
	public final static String serverToken = UUID.randomUUID().toString();


	  public static void main(String[] args) throws Exception {
		    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		    server.createContext("/getToken", new VerifyCaptcha());
		    server.createContext("/", new landingpane());
		    server.setExecutor(null); // creates a default executor
		    server.start();
		    System.out.println("The server is running");
		  }

		  static class VerifyCaptcha implements HttpHandler {
			public void handle(HttpExchange httpExchange) throws IOException {
				StringBuilder response = new StringBuilder();
				 Map <String,String>parms = BasicServer.queryToMap(httpExchange.getRequestURI().getQuery());
				 System.out.println(JsonReader.main(parms.get("key")));
				 JsonReader.main(parms.get("key"));
				if(Boolean.TRUE){
					 String cookie = httpExchange.getRemoteAddress().getAddress().toString();
					 Key aesKey = new SecretKeySpec(serverKey.getBytes(), "AES");
			         Cipher cipher;
					try {
						cipher = Cipher.getInstance("AES");
						cipher.init(Cipher.ENCRYPT_MODE, aesKey);
						 byte[] encrypted;
						 encrypted = cipher.doFinal(cookie.getBytes());
						 System.err.println(new String(encrypted));
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				 } else {
					 response.append("400");
				 }
				BasicServer.writeResponse(httpExchange, response.toString());
			}
			  
		  }
		  
		  static class landingpane implements HttpHandler {
			    public void handle(HttpExchange httpExchange) throws IOException {
			      String response = "Arrete de regarder notre code petit coquin ;)" ;
			      BasicServer.writeResponse(httpExchange, response.toString());
			    }
			  }

		public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
			 httpExchange.sendResponseHeaders(200, response.length());
			 OutputStream os = httpExchange.getResponseBody();
			 os.write(response.getBytes());
			    os.close();
			
		}

		public static Map<String, String> queryToMap(String query) {
		    Map<String, String> result = new HashMap<String, String>();
		    for (String param : query.split("&")) {
		        String pair[] = param.split("=");
		        if (pair.length>1) {
		            result.put(pair[0], pair[1]);
		        }else{
		            result.put(pair[0], "");
		        }
		    }
		    return result;
		}
}
	
