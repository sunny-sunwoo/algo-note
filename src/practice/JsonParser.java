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
 *    
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
	private static int i = 0;
	public static boolean isValidJson(String input) {
		Deque<Character> stack = new ArrayDeque<>();
		if (i >= input.length() || input.charAt(i) != '{') {
			return false;
		}
		stack.addLast('{');
		i++;

		while (i < input.length() && input.charAt(i) != '}') {
			if (!validateJsonString(input)) {
				return false;
			}

			if (input.charAt(i++) != ':') {
				return false;
			}

			if (!validateJsonValue(input)) {
				return false;
			}
			if (input.charAt(i) == ',') {
				i++;
			}
		}
		if (i >= input.length() || stack.isEmpty()) {
			return false;
		}
		stack.removeLast();
		return stack.isEmpty() ? true : false;
	}

	private static boolean validateJsonString(String s) {
		if (s.charAt(i++) != '\"') {
			return false;
		}
		while (i < s.length() && Character.isLetter(s.charAt(i))) {
			i++;
		}
		if (s.charAt(i++) != '\"') {
			return false;
		}
		return true;
	}

	private static boolean validateJsonValue(String s) {
		// case1: string
		if(s.charAt(i) == '\"') {
			return validateJsonString(s);
		}

		// case2: object
		if(s.charAt(i) == '{') {
			return isValidJson(s); // recursive call
		}

		// case3: array of object
		if(s.charAt(i) == '[') {
			i++;

			while(true) {
				if(!validateJsonValue(s)) { // recursive call
					return false;
				}
				if(s.charAt(i) == ',') {
					i++;
				}
				else {
					break;
				}
			}
			if(s.charAt(i) == ']') {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		String input = "{\"key\":\"value\",\"key\":{\"key\":\"value\"},\"key\":[{\"key\":\"value\"},{\"key\":\"value\"},{\"key\":\"value\"}]}";
		String input2 = "{\"key\":\"value\",\"key\":{\"key\":\"value\"},\"key\":[{\"key\":\"value\"},{\"key\":\"value\"},{\"key\":\"value\"}}";
		String input3 = "{\"key\"\"value\",}";
		System.out.println(isValidJson(input)); // valid
		System.out.println(isValidJson(input2)); // invalid
		System.out.println(isValidJson(input3)); // invalid
	}
	
}
