package slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Smallest Window containing Substring
 * Q. Given a string and a pattern, find the smallest substring in the given string
 * which has all the characters of the given pattern.
 * 
 * Input: String="aabdec", Pattern="abc"
 * Output: "abdec"
 * 
 * test case?
 * 1. null/empty str or pattern
 * 2. str = "ab", pattern = "abc"
 * 3. String="aabdec", Pattern="abc"
 * 4. str = "cccba" pattern = "abc"
 * 5. str = "aakbc" pattern = "abca"
 *  
 * [approach]
 * pattern map => a=1, b=1, c=1
 * 
 *   |     |||  |
 *   akkkkkbabdecabc
 *                ^
 * proceed with right pointer
 *    if the specific char found all freq? found++
 *    if found == pattern map size?
 *       => while curr char has all freq: 
 *          proceed with left ptr 
 * keep min length
 * 
 * @author sunnypark
 *
 */
public class SmallestWindowContainingSubstr {
	public static String findSmallestWindow(String str, String pattern) {
		Map<Character, Integer> patternMap = buildMap(pattern);
		Map<Character, Integer> foundMap = new HashMap<>();
		
		int left = 0, right = 0;
		int found = 0;
		String smallestWindow = "";
		int minLen = Integer.MAX_VALUE;

		for (right = 0; right < str.length(); right++) {
			char currChar = str.charAt(right);
			if (!patternMap.containsKey(currChar)) {
				continue;
			}
			foundMap.put(currChar, foundMap.getOrDefault(currChar, 0) + 1);
			if (foundMap.get(currChar) == patternMap.get(currChar)) {
				found++;
			}
			
			while (found == patternMap.size()) {
				if (right - left + 1 < minLen) {
					smallestWindow = str.substring(left, right + 1);
				}
				char leftChar = str.charAt(left);
				if (patternMap.containsKey(leftChar)) {
					foundMap.put(leftChar, foundMap.get(leftChar) - 1);
					if (foundMap.get(leftChar) == 0) {
						foundMap.remove(leftChar, 0);
						found--;
					}
				}
				left++;
			}
		}

		return smallestWindow;
	}
	
	private static Map<Character, Integer> buildMap(String pattern) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < pattern.length(); i++) {
			char currChar = pattern.charAt(i);
			map.put(currChar, map.getOrDefault(currChar, 0) + 1);
		}
		return map;
	}
	
	public static void main(String[] args) {
		System.out.println(findSmallestWindow("aabdec", "abc")); // expected: abdec
		System.out.println(findSmallestWindow("cccba", "abc")); // expected: cba
	}
}
