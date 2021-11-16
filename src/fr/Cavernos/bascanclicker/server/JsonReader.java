package fr.Cavernos.bascanclicker.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {
	
	
	
	  private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }

	  public static Boolean main(String url) throws IOException, JSONException {
	    JSONObject json = readJsonFromUrl("https://www.google.com/recaptcha/api/siteverify?secret=6Ld1iTsdAAAAAIVUgfanoRz_nmCCnez_pWKVcz9n&response=" + url);
	    if (json.get("success").equals("true")){
	    	return true;
	    }
	    else {
	    	return false;
	    }
	  }
	}
