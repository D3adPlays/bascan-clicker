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
		    server.createContext("/set", new VerifyCaptcha());
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
		  
		  static class VerifyCaptcha implements HttpHandler {
			public void handle(HttpExchange httpExchange) throws IOException {
				StringBuilder response = new StringBuilder();
				 Map <String,String>parms = BasicServer.queryToMap(httpExchange.getRequestURI().getQuery());
				response.append("https://www.google.com/recaptcha/api/siteverify?secret=" + parms.get("6Ld1iTsdAAAAAIVUgfanoRz_nmCCnez_pWKVcz9n" + "response=" + parms.get("03AGdBq25jVcrRziEKsV2hH6Mx3amSiJxFAh-Qc87teIYt9g6suuo6Pj3u77J2FlsG54eIjYgM68BdfA4k-M56flegPztuNSaG8oTB44Fl6YbViXvY72PQR8OJ3CAAt-kTSthi-HAeeeyNLBjQdI49Zi7Y_kkvCPdSYQWj9V1hKLeec06gtSlG3SPoR-zvUiFl9PIchytF5FkY6nI7Q9_Oxc1TFdpPvcIKG8tvYWdiPukwa2Sx5juyALkdiaLwOl85215Jpa0tLCqS8UOVxOKFNcQSMPzzubjHAANpuT8M9eAJpKHY4aamjg7kuLFAVGKIs5xjC9C5_wURbzZQS3rKD3_-VI7VHz-9lFbX8gJXmVsGslTakNV7sSYDvKpjYtmiqGkLruzl9g5iCPZmla3KIHUp1bTIROWUW4O8wSLziB2jg-slxGJp4pFW5H2DVlo-i0MLkSWYksVfxZ0LjRMtQII32oxqvm9r2h7Lb6r_rEUHIFxCfm3dvSqsJc1OLzHSC4KLOUN6YXIa")));
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
	
