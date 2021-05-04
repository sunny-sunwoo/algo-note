package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC621_TaskScheduler {
	public int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }
        
        if (tasks.length == 0) {
            return 0;
        }
        
        PriorityQueue<Node> pq = buildMaxHeap(tasks);
        Deque<Node> tmpQ = new ArrayDeque<>();
        int totalTime = 0;
        
        while (!pq.isEmpty()) {
            for(int i = 0; i <= n; i++) {
                totalTime++;
                if (!pq.isEmpty()) {
                    Node top = pq.poll();
                    if (top.hasFreq()) {
                        tmpQ.addLast(top.toNext());
                    }
                }
                
                if (pq.isEmpty() && tmpQ.isEmpty()) {
                    return totalTime;
                }
            }

            while(!tmpQ.isEmpty()) {
                pq.offer(tmpQ.removeFirst());
            }
        }
        return totalTime;
    }
    
    private PriorityQueue<Node> buildMaxHeap(char[] tasks) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for (Map.Entry<Character, Integer> entry: toFreqMap(tasks).entrySet()) {
            pq.offer(new Node(entry.getKey(), entry.getValue()));
        }
        return pq;
    }
    
    private Map<Character, Integer> toFreqMap(char[] tasks) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : tasks) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        return freqMap;
    }
    
    private static class Node implements Comparable<Node> {
        char c;
        int freq;
        
        Node(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
        
        int getFreq() {
            return freq;
        }
        
        boolean hasFreq() {
            return this.freq > 1;
        }
        
        Node toNext() {
            freq--;
            return this;
        }

		@Override
		public int compareTo(Node other) {
			// TODO Auto-generated method stub
			return other.getFreq() - this.getFreq();
		}
    }
}
