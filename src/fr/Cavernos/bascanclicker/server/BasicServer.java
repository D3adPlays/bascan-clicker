package fr.Cavernos.bascanclicker.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class BasicServer {
	
	public static final String serverKey = "6Ld1iTsdAAAAAIVUgfanoRz_nmCCnez_pWKVcz9n";
	public static final String serverToken = UUID.randomUUID().toString();
	public static SecretKeySpec secretKey;
	public static final String ALGORITHM = "AES";
	public static String encryptKey;


	  public static void main(String[] args) throws Exception {
		    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		    server.createContext("/getToken", new VerifyCaptcha());
		    server.createContext("/count", new Counter());
		    server.createContext("/", new landingpage());
		    server.setExecutor(null); // creates a default executor
		    server.start();
		    System.out.println("The server is running");
		  }
		  static class VerifyCaptcha implements HttpHandler {
			public void handle(HttpExchange httpExchange) throws IOException {
				httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin","*");
				StringBuilder response = new StringBuilder();
				 Map <String,String>parms = BasicServer.queryToMap(httpExchange.getRequestURI().getQuery());
				if(JsonReader.main(parms.get("key"))){
					 String cookie = httpExchange.getRemoteAddress().getAddress().toString();
					 encryptKey = encrypt(cookie + serverToken, serverKey);
					 response.append(encryptKey);
					 //response.append(decrypt(encryptKey, serverKey));
				 } else {
					 response.append("400");
				 }
				BasicServer.writeResponse(httpExchange, response.toString());
			}
			  
		  }
		  
		  static class landingpage implements HttpHandler {
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
		public static String decrypt(String strToDecrypt, String secret) {
			try {
				prepareSecreteKey(secret);
				Cipher cipher = Cipher.getInstance(ALGORITHM);
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
				return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt.getBytes(StandardCharsets.UTF_8))));
			} catch (Exception e) {
				System.out.println("Error while decrypting: " + e);
			}
			return null;
		}

		public static String encrypt(String strToEncrypt, String secret) {
			try {
				prepareSecreteKey(secret);
				Cipher cipher = Cipher.getInstance(ALGORITHM);
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
				return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
			} catch (Exception e) {
				System.out.println("Error while encrypting: " + e);
			}
			return null;
		}

		public static void prepareSecreteKey(String myKey) {
			MessageDigest sha;
			try {
				byte[] key = myKey.getBytes(StandardCharsets.UTF_8);
				sha = MessageDigest.getInstance("SHA-1");
				key = sha.digest(key);
				key = Arrays.copyOf(key, 16);
				secretKey = new SecretKeySpec(key, ALGORITHM);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
}
	
