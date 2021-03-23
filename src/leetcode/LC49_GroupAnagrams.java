package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * LC49 group anagram
	input: strs = ["eat","tea","tan","ate","nat","bat"]
	                                        ^
	output: [["bat"],["nat","tan"],["ate","eat","tea"]]
	
	aet = ["ate","eat","tea"]
	ant = ["nat","tan"]
	abt = ["bat"]
	
	[public]
	Map<S, L<S>> map
	iter thru strings O(N)
	 currKey => str sorted by char #
	 curr list = get or default(currKey, new list)
	 add to curr list and put in the map
	build result with values
	
	[private] build key O(kLogK)
	 eat => to char array ['e', 'a', 't']
	     => sort array  ['a', 'e', 't']
	     => build string new String(chars)
     
     [complexity] N = length of strs, K = max length of a str
     time: O(N*KLogK)
     space: O(NK) 
 * @author sunnypark
 *
 */
public class LC49_GroupAnagrams {
	public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> cache = new HashMap<>();
        for (String str : strs) {
            String currKey = buildKey(str);
            cache.computeIfAbsent(currKey, (unused) -> new ArrayList<String>()).add(str);
        }
        return cache.values().stream().collect(Collectors.toList());
    }
    
    private static String buildKey(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}
