package practice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * LC 1153. String Transforms Into Another String
 * Given two strings str1 and str2 of the same length,
 * determine whether you can transform str1 into str2 by doing zero or more conversions.
 * 
 * e.g. 
 * Input: str1 = "aabcc", str2 = "ccdee"
 * Output: true
 * 
 * 
 * [approach]
 * 0. if equals? rt true
 * 1. iter over str1 to build hm(char from -> char to)
 *    contained? 
 *     -> value is diff with target? rt false
 *     -> skip otherwise
 *     
 *    else? 
 *     -> put new entry
 *     
 * 2. if value has the full size 26 ? cant convert (due to the CYCLE)
 * 3. rt true
 *     
 * @author sunnypark
 *
 */
public class LC1153_StrIntoAnotherStr {
	public static boolean canConvert(String str1, String str2) {
		if (str1.equals(str2)) {
            return true;
        }
        
        // validate str1, str2 if necessary
        Map<Character, Character> cache = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            char from = str1.charAt(i);
            char to = str2.charAt(i);
            
            if (cache.containsKey(from)) {
                if (!cache.get(from).equals(to)) {
                    return false;
                }
                continue; // mapping already done
            }
            cache.put(from, to);
        }
        
        return cache.values().stream().collect(Collectors.toSet()).size() < 26;
	}

	public static void main(String[] args) {
		String str1 = "abcdefghijklmnopqrstuvwxyz";
		String str2 = "bcdefghijklmnopqrstuvwxyza";

		System.out.println(canConvert(str1, str2));
	}

}
