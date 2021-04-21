package leetcode;

import java.util.NavigableMap;
import java.util.TreeMap;

public class LC729_MyCalendar {
    NavigableMap<Integer, Integer> calendar;
    
    public LC729_MyCalendar() {
        calendar = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
    	NavigableMap.Entry<Integer, Integer> leftEntry = calendar.floorEntry(start);
    	NavigableMap.Entry<Integer, Integer> rightEntry = calendar.higherEntry(start);
        
        // if overlapped ? rt false
        if (leftEntry != null && isOverlapped(leftEntry.getKey(), leftEntry.getValue(), start, end)) {
            return false;
        }
        
        
        if (rightEntry != null && isOverlapped(start, end, rightEntry.getKey(), rightEntry.getValue())) {
            return false;
        }
        
        // otherwise? add to tree map, rt true
        calendar.put(start, end);
        return true;
    }

    private static boolean isOverlapped(int s1, int e1, int s2, int e2) {
        return e1 > s2;
    }
}
