package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LC56. merge intervals
	
	e.g. [[1,3],[2,6],[8,10],[15,18]]
	     => [1,6], [8,10], [15,18]
	  
	
	[public]
	build pq with comp (min heap, sort by starting time)
	
	iter thru the pq
	- poll
	- if overlapped(last interval in res, curr)? start, max(end1, end2)
	    otherwise? add to res
	
	rt res
	
	[overlapped logic] 
	s2 <= e1
	
	  s1 <----> e1
	     s2 <----> e2
	
	
	(Note) think about Interval class implements Comparable<Interval>
 * @author sunnypark
 *
 */
public class LC56_MergeIntervals {
	public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        PriorityQueue<int[]> pq = buildMinHeap(intervals);
        
        int[] first = pq.poll();
        int start = first[0];
        int end = first[1];
        int idx = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (isOverlapped(start, end, curr[0], curr[1])) {
                start = Math.min(start, curr[0]);
                end = Math.max(end, curr[1]);
            } else {
                result.add(new int[]{start, end});
                start = curr[0];
                end = curr[1];
            }
        }
        
        result.add(new int[]{start, end});
        return buildResult(result);
    }
    
    private int[][] buildResult(List<int[]> list) {
        int[][] res = new int[list.size()][2];
        int idx = 0;
        for (int[] each : list) {
            res[idx] = each;
            idx++;
        }
        return res;
    }
    
    // private buildMinHeap
    private PriorityQueue<int[]> buildMinHeap(int[][] intervals) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] interval : intervals) {
            minHeap.offer(interval);
        }
        return minHeap;
    }
    
    //private isOverlapped
    private boolean isOverlapped(int start, int end, int newStart, int newEnd) {
        return newStart <= end;
    }
}
