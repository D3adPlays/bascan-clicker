package fr.Cavernos.bascanclicker.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class BasicServer {

	  public static void main(String[] args) throws Exception {
		    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		    server.createContext("/info", new InfoHandler());
		    server.createContext("/get", new GetHandler());
		    server.setExecutor(null); // creates a default executor
		    server.start();
		    System.out.println("The server is running");
		  }

		  // http://localhost:8000/info
		  static class InfoHandler implements HttpHandler {
		    public void handle(HttpExchange httpExchange) throws IOException {
		      String response = "Use /get?hello=word&foo=bar to see how to handle url parameters";
		      BasicServer.writeResponse(httpExchange, response.toString());
		    }
		  }

		  static class GetHandler implements HttpHandler {
		    public void handle(HttpExchange httpExchange) throws IOException {
		      StringBuilder response = new StringBuilder();
		      Map <String,String>parms = BasicServer.queryToMap(httpExchange.getRequestURI().getQuery());
		      response.append("<html><body>");
		      response.append("hello : " + parms.get("hello") + "<br/>");
		      response.append("foo : " + parms.get("foo") + "<br/>");
		      response.append("</body></html>");
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
	
