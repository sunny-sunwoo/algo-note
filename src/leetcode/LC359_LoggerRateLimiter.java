package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * LC 359. Logger Rate Limiter
 *
 [Solution 1] use Queue! 
  - queue of pair(time, message)
  - hashset of message for fast search
  - LIMIT = 10
 
 1. while q is not empty AND peekFirst is <= ts - LIMIT 
 							(E.G, remove (1,foo) when (11,foo) in)
    - invalidate obsolete timestamps
    - rm from cache for sync
 
 2. cache contains curr msg ? rt false
    otherwise? *UPDATE* cache, rt true
    
    
 [Solution 2] map
 HM message -> timestamp
 
	e.g. input (foo, 1)
	=> if hm doesn't contain the message? 
	    put (foo, 11)  // message, next allowed time
	    
	=> if not, (contained) 
	    check if the next allowed time <= new timestamp
	    1) if not valid: rt false
	    2) if valid: update the entry. rt true
 
 * @author sunnypark
 *
 */

public class LC359_LoggerRateLimiter {
	private static final int LIMIT = 10;
    private Deque<Pair<Integer, String>> q;
    private Set<String> cache;
    
    public LC359_LoggerRateLimiter() {
        q = new ArrayDeque<>();
        cache = new HashSet<>();
    }
    
    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
       If this method returns false, the message will not be printed.
       The timestamp is in seconds granularity.
     * @param timestamp
     * @param message
     * @return
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        while (!q.isEmpty() && hasObsoleteEntry(q.peekFirst().first, timestamp)) {
            Pair<Integer, String> obsoleteEntry = q.removeFirst();
            cache.remove(obsoleteEntry.second);
        }
        
        if (cache.contains(message)) {
            return false;
        }
        
        q.addLast(new Pair(timestamp, message));
        cache.add(message);
        return true;
    }
    
    private boolean hasObsoleteEntry(int prevTimestamp, int newTimestamp) {
        return prevTimestamp <= (newTimestamp - LIMIT);
    }
     
    private class Pair<U, V> {
        U first;
        V second;
        
        Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
        
        @Override
        public String toString() {
            return first + " " + second;
        }
    }
}


// Hashmap solution
/*
class Logger {
    private static final int LIMIT = 10;
    private Map<String, Integer> cache;

    public Logger() {
        cache = new HashMap<>();
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!cache.containsKey(message)) {
            cache.put(message, timestamp + LIMIT);
            return true;
        }
        
        // if contained? determine if valid.
        int nextAllowedTime = cache.get(message);
        if (timestamp < nextAllowedTime) { // came too early
            return false;
        } 
        
        // possible
        cache.put(message, timestamp + LIMIT);
        return true;
    }
}
*/
