package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LC 290 Word Pattern
 * 
abba
[dog, cat, cat, dog]

1. s -> s[]
   if pattern length != s[] size ? rt false

2. iter thru pattern // Map => (a, dog)
  if map !containsKey ? 
    i) check if word is used ? rt false
    ii) add to map, set(curr pattern char, word)
  
  otherwise? if word != curr value in the map
     => rt false
    
3. rt true

[Note] think about edge case! 
   => we need used to keep track of values too.
Input: pattern = "abba", s = "dog dog dog dog"
Output: false

 * @author sunnypark
 *
 */
public class LC290_WordPattern {
	public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) {
            return false; // invalid input
        }
        
        Map<Character, String> cache = new HashMap<>();
        Set<String> used = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);

            if (!cache.containsKey(ch)) {
                if (used.contains(words[i])) {
                    return false;
                }
                cache.put(ch, words[i]);
                used.add(words[i]);
                continue;
            } 
            
            if (!cache.get(ch).equals(words[i])) {
                return false;
            }
        }
        return true;
    }
}
