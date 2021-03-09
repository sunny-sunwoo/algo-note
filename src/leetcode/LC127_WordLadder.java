package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LC 127 Word Ladder
 * 
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog" with 5 words.
 * 
 * [Approach]
	queue
	used
	cnt = 1
	
	while queue is not empty,
	    cnt ++,
	    check layer size
	    for each layer
	    - poll top
	    - if top <=> endword ? rt cnt
	    - for 26 alphabet
	        - for each char
	           => switch 
	           => if not visited and contained in wordList
	               -> add to queue
	               -> visited
 *
 * @author sunnypark
 *
 */
public class LC127_WordLadder {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 1;
        }
        
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        Deque<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        visited.add(beginWord);
        queue.offer(beginWord);
        int cnt = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            cnt++;
            while (size-- > 0) {
                String currWord = queue.poll();
                if (currWord.equals(endWord)) {
                    return cnt;
                }
                char[] charArr = currWord.toCharArray();
                for (int c = 0; c < 26; c++) {
                    char currChar = ALPHABET.charAt(c);
                    for (int i = 0; i < currWord.length(); i++) {
                        char originalChar = charArr[i];
                        charArr[i] = currChar;
                        String newWord = new String(charArr);
                        if (!visited.contains(newWord) && wordSet.contains(newWord)) {
                            visited.add(newWord);
                            queue.offer(newWord);
                        }
                        charArr[i] = originalChar;
                    }
                }
            }
            
        }
        return 0;
    }
}
