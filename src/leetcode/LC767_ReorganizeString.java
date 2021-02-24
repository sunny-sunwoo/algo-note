package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/*
aaaaabbcc
a

abacabaca
max heap
 c1
 => abacababc
 
 - build freq map -> max heap of Node e.g. (a, 2)
 - while mh size > 1 
     poll first and second
     append to the sb
     offer the next if remaining

- if last node freq != 1 ? 
   -> rt ""
 - else append the last 

*/

public class LC767_ReorganizeString  {
    public String reorganizeString(String S) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Node> maxHeap = init(S);
        
        while (maxHeap.size() > 1) {
            Node first = maxHeap.poll();
            Node second = maxHeap.poll();
            
            sb.append(String.valueOf(first.getChar()));
            sb.append(String.valueOf(second.getChar()));
            
            if (first.hasNext()) {
                maxHeap.offer(first.getNext());
            }
            
            if (second.hasNext()) {
                maxHeap.offer(second.getNext());
            }
        }
        
        if (maxHeap.size() == 1) {
            Node last = maxHeap.poll();
            if (last.getFreq() > 1) {
                return "";
            } else {
                sb.append(String.valueOf(last.getChar()));
            }
        }
        
        return sb.toString();
    }
    
    private PriorityQueue<Node> init(String s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            int freq = freqMap.getOrDefault(curr, 0) + 1;
            freqMap.put(curr, freq);
        }

        for (Map.Entry<Character, Integer> entry: freqMap.entrySet()) {
            pq.offer(new Node(entry.getKey(), entry.getValue()));
        }
    
        return pq;
    }
    
    private class Node implements Comparable<Node>{
        char val;
        int freq;
        
        Node(char val, int freq) {
            this.val = val;
            this.freq = freq;
        }
        
        int getFreq() {
            return freq;
        }
        
        char getChar() {
            return val;
        }
        
        boolean hasNext() {
            return freq >= 2;
        }
        
        Node getNext() {
            return new Node(val, freq - 1);
        }
        
        @Override
        public int compareTo(Node other) {
            return other.freq - this.freq;
        }
    }
}

