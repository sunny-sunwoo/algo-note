package lastMin_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 	hs => ""
    
    for each str in arr:
        create a new tmpList
        
        => for each uniqStr in hs: 
            build candidate (str + uniqStr) 
            keep in tmpList if candidate is uniq
                
        => add tmpList to hs
    
    find maxLen in hs
    
 * @author sunnypark
 *
 */
public class MaxLenOfConcatStrWithUniqChars {
	public static int maxLength(List<String> arr) {
        Set<String> res = new HashSet<>();
        res.add("");
        
        for (String s : arr) {
            List<String> tmp = new ArrayList<>();
            for (String uniqStr : res) {
                String candidate = new StringBuilder(s).append(uniqStr).toString();
                if (!isUnique(candidate)) {
                    continue;
                }
                tmp.add(candidate);
            }
            res.addAll(tmp);
        }
        return res.stream().mapToInt(s -> s.length()).max().getAsInt();
    }
    
    private static boolean isUnique(String s) {
        Set<Character> set = new HashSet<>();
        for (char c: s.toCharArray()) {
            set.add(c);
        }
        return set.size() == s.length();
    }
}
