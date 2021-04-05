package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Q. word break 2 
 * 
 
 catsanddog -> catsand -> cats -> ""  // [cats, and, dog]
  					   -> cat  -> ""  // [cat, sand, dog]

base case:
 empty string? => add path to result (' ' separated)

i = s.length() - minLen, i--
  j = s.length()
  
  => if !wordDict.contains(substring i..j) ? continue
  
  => otherwise, recursive call
   1. add substring to path
   2. recursive call w/path, substring 0..i
   3. rm from path
 
 * @author sunnypark
 *
 */
public class LC140_WordBreak2 {
    private static int minLen;
    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordDict);
        minLen = wordSet.stream().mapToInt(i -> i.length()).min().getAsInt();
        wordBreak(result, new ArrayDeque<>(), wordSet, s);
        return result;
    }
    
    private static void wordBreak(List<String> result, Deque<String> path, Set<String> wordSet, String s) {
        if (s.equals("")) {
            result.add(buildResult(new ArrayDeque<>(path))); // space separated
        }
        
        for (int i = s.length() - minLen; i >= 0; i--) {
            int j = s.length();
            
            String curr = s.substring(i,j);
            if (!wordSet.contains(curr)) {
                continue;
            }
            
            path.addFirst(curr);
            wordBreak(result, path, wordSet, s.substring(0,i));
            path.removeFirst();
        }
    }
    
    private static String buildResult(Deque<String> path) {
        StringBuilder sb = new StringBuilder();
        while (!path.isEmpty()) {
            sb.append(path.removeFirst()).append(" ");
        }
        
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    
    public static void main(String[] args) {
    	List<String> wordDict1 = List.of("cat","cats","and","sand","dog");
    	String s1 = "catsanddog";
    	System.out.println(wordBreak(s1, wordDict1));
    	
    	List<String> wordDict2 = List.of("apple","pen","applepen","pine","pineapple");
    	String s2 = "pineapplepenapple";
    	System.out.println(wordBreak(s2, wordDict2));
    	
    	List<String> wordDict3 = List.of("cats","dog","sand","and","cat");
    	String s3 = "catsandog";
    	System.out.println(wordBreak(s3, wordDict3));
    	
    } 
}
