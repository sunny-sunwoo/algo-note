package practice;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
 *    Map<String, Object>
 * "key" : "value"
 * "key" : {"key" : "value"}
 * "key" : [{"key" : "value"}, {"key" : "value"}, {"key" : "value"}]
 * 
 * e.g. 
 * {"key1" : "value1", "key2" : {"key3" : "value3"}, "key4": [{"key" : "value"}, {"key" : "value"}, {"key" : "value"}]}
 * "status":"ok"
 * "status_message":"Query was successful"
 * "data": List<Map> 
 * 
 * { "key1":"value1", "key2":"{"key1":"value1"}" }
 * @author sunnypark
 *
 */
public class JsonParser {
	public static boolean isValidJson(String input) {
//		System.out.println(input);
		if (input.length() == 0) {
			return true;
		}
		
		int i = 0;
		int j = input.length() - 1;

		if (input.charAt(i) != '{' || input.charAt(j) != '}') {
			return false;
		}
		
		i++;
		while (i < j) {
			// find ':' 
			int separator = input.indexOf(":", i);
			if (separator == -1) {
				return false;
			}

			// 1. validate key
			if (!isValidJsonString(input.substring(i, separator))) {
				return false;
			}

			// 2. validate value - str, obj, arr
			separator++;
			int commaIdx = input.indexOf(",", separator);
			if (commaIdx == -1) {
				commaIdx = j;
			}
			if (input.charAt(separator) == '\"') { // CASE1: string
				if (!isValidJsonString(input.substring(separator, commaIdx))) {
					return false;
				}
				i = commaIdx + 1;
				
			} else if(input.charAt(separator) == '{') { // CASE2: object
				if (!isValidJson(input.substring(separator, commaIdx))) {
					return false;
				}
				i = commaIdx + 1;
			} else { // CASE3: arr of objects
				int closing = input.lastIndexOf(']');
				// array check
				if (input.charAt(separator++) != '[' || closing == -1) {
					return false;
				}
				String[] pairs = input.substring(separator, closing).split(",");
				for (String pair: pairs) {
					if (!isValidJson(pair)) {
						return false;
					}
				}
				i = closing + 1;
			}
		}
		return true;
	}

	
	private static boolean isValidJsonString(String s) {
		int i = 0, j = s.length() - 1;
		if (s.charAt(i) != '\"' || s.charAt(j) != '\"') {
			return false;
		}
		i++;

		while (i < j && (Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i)))) {
			i++;
		}
		return i == j;
	}
	
	
	public static void main(String[] args) {
//		String input = "{\"key\":\"value\"}";
//		String input = "{\"key\":{\"key\":\"value\"}}";
//		String input = "{\"key\":[{\"key\":\"value\"},{\"key\":\"value\"},{\"key\":\"value\"}]}";
		String input = "{\"key1\":\"value1\",\"key2\":{\"key3\":\"value3\"},\"key4\":[{\"key5\":\"value5\"},{\"key6\":\"value6\"},{\"key7\":\"value7\"}]}";
		String input2 = "{\"key\":\"value\",\"key\":{\"key\":\"value\"},\"key\":[{\"key\":\"value\"},{\"key\":\"value\"},{\"key\":\"value\"}}";
		String input3 = "{\"key\"\"value\",}";

		System.out.println(isValidJson(input)); // valid
		System.out.println(isValidJson(input2)); // invalid
		System.out.println(isValidJson(input3)); // invalid
	}
	
}
