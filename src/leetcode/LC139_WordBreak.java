package leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * LC 139 Word Break
 * 
 * e.g. 
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * 
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * 
 * 
 * [Approach]

	leetcode
	^   ^   ^
	
	will build tracker => [true, f, f, f, true, f, f, f, true]
		- mark as true 1) for base case (last idx)
					   2) for starting points of each valid word
					   
		- valid at point X for curr word 
			=> tracker[X + 1] is true and wordSet contains curr word
	
	[logic]
	
	wordSet
	boolean arr tracker <= s length + 1
	 w/ true at the end
	
	minLength
	maxLength
	
	for i = length - minLen ~ 0
	  for j = i + minLen ~ min(i + maxLen, length)
	    => word = substring (i..j)
	    => if tracker[j] && wordSet.contains word
	         - tracker[i] = true
	         - break
	rt tracker[0]
	
 * 
 * @author sunnypark
 *
 */
public class LC139_WordBreak {
	public boolean wordBreak(String s, List<String> wordDict) {
        if (s.isEmpty() || wordDict.isEmpty()) {
            return false;
        }

        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] tracker = new boolean[s.length() + 1];
        tracker[s.length()] = true;

        int minLength = wordSet.stream().mapToInt(i -> i.length()).min().getAsInt();
        int maxLength = wordSet.stream().mapToInt(i -> i.length()).max().getAsInt();
        
        for (int i = s.length() - minLength; i >= 0; i--) {
            for (int j = i + minLength; j <= Math.min(s.length(), i + maxLength); j++) {
                String currWord = s.substring(i, j);
                if (tracker[j] && wordSet.contains(currWord)) {
                    tracker[i] = true;
                    break;
                }
            }
        }
        
        return tracker[0];
    }
}
