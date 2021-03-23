package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIReader {
	private static void build(String urlString) throws IOException {
        URL url = new java.net.URL(urlString);
    	InputStream is = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<String> jsonResponse = readData(reader);
        is.close();
        
        JsonParser parser = new JsonParser(jsonResponse.get(0));
        parser.deserializeJson();
	}
	
	private static List<String> readData(BufferedReader reader) throws IOException {
		List<String> parsedData = new ArrayList<>();
		boolean eof = false;
        while (!eof) {
        	String line = reader.readLine();
        	if (line == null) {
        		eof = true;
        	} else {
        		parsedData.add(line);
        	}
        }
        return parsedData;
        
	}
	
    public static void main(String[] args) {
    	String movieApi = "https://yts.mx/api/v2/list_movies.json?limit=10";
    	// String urlString = "https://sunny-sunwoo.github.io/sunnypark";
    	try {
    		build(movieApi);
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
        
    }
}
