package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LC 269 Alien Dictionary
 * 
e.g.
Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"

Input: words = ["z","x","z"]
Output: ""

[topological sort]
	1) build graph
	w -> e
	e -> r
	t -> f
	r -> t
	
	compare 2 chars until min len
	 if ch1 != ch2 ? put(ch1, ch2)
	 else ptr ++ 
	 
	if invalid ? (short one comes first) 
	  -> rt ""
	  
	2) queue w/0 indegree character
	  queue = [w]
	 
	3) iter while queue is not empty
	  poll and build output
	  
	  for each next in values
	    dec indegree by 1
	    if indegree 0 ? offer to q
	    
	
	4) if output size doesnt equal to indegree size? rt ""
	   otherwise, rt output
	   
 * @author sunnypark
 *
 */
public class LC269_AlienDictionary {
	public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        
        // init
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                if (!graph.containsKey(ch)) {
                    graph.put(ch, new HashSet<>());
                    indegree.put(ch, 0);
                }
            }
        }
        
        // build graph
        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            
            boolean found = false;
            for (int idx = 0; idx < Math.min(first.length(), second.length()); idx++) {
                char ch1 = first.charAt(idx);
                char ch2 = second.charAt(idx);

                if (ch1 != ch2) {
                    if (!graph.get(ch1).contains(ch2)) {
                        graph.get(ch1).add(ch2);
                        indegree.put(ch2, indegree.get(ch2) + 1);
                    }
                    found = true;
                    break;
                }
            }
            
            if (!found) { // means invalid input e.g. abc, ab
                if (first.length() > second.length()) {
                    return "";
                }
            }
        }
        
        // build queue w/ starting points
        Deque<Character> queue = new ArrayDeque<>();
        for (Map.Entry<Character, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.addLast(entry.getKey());
            }
        }

        // iter thru queue
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char top = queue.poll();
            sb.append(top);
            
            for (char next : graph.get(top)) {
                int updatedIndegree = indegree.get(next) - 1;
                indegree.put(next, updatedIndegree);
                if (updatedIndegree == 0) {
                    queue.offer(next);
                }
            }
        }

        // return
        // cyclic dependency btw characters? rt ""
        return sb.length() == indegree.size() ? sb.toString() : "";
    }
}
