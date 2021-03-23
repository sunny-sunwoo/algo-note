package practice;

import java.util.Arrays;

/**
 * Json Parsing
 * string -> Map<String, Object>
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
 * "key" : {...}
 * "key" : [{"key" : "value"}]
 * 
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
		System.out.println(Arrays.toString(splitted));
		System.out.println(splitted.length);
	}
}
