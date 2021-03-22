package grokking.slidingWindow;

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
		for (right = 0; right < str.length(); right++) {
			
		}
		return "";
	}
}
