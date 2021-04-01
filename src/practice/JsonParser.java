package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Json Parsing
 * string -> Map<Object, Object>
 *  find pair {----}
 *  find key 
 *  find value
 *    i) string => map.put(key, value)
 *    
 *    ii) object 
 *    	=> recursive call of {...}
 *      => map.put(key, object)
 * 
 *    iii) arr of objects
 *      => [{obj1}, {obj2}, ...]
 *      => for each object, recursive call
 *    
 *    
 * "key" : "value"
 * "key" : {"key" : "value"}
 * "key" : [{"key" : "value"}, {"key" : "value"}, {"key" : "value"}]
 * {"status":"ok", "status_message":"Query was successful", "data":[{"movie_count":28778, "limit":10, "page_number":1}, {"movie_count":28778, "limit":10, "page_number":1}]}
 * 
 * "status":"ok"
 * "status_message":"Query was successful"
 * "data": List<Map> 
 * 
 * { "key1":"value1", "key2":"{"key1":"value1"}" }
 * @author sunnypark
 *
 */
public class JsonParser {
	private String data;
	public JsonParser(String jsonData) {
		this.data = jsonData;
	}
	
	public void deserializeJson() {
		String[] splitted = data.split(",");
//		System.out.println(Arrays.toString(splitted));
//		System.out.println(splitted.length);
		
		Map<String, Object> cache = new HashMap<>();
		for (String pair: splitted) {
			if (isStringPair(pair)) {
				String[] keyValue = pair.split(":");
				cache.put(keyValue[0], keyValue[1]);
			}
		}
	}
}
