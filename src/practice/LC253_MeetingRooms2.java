package practice;

import java.util.Arrays;

/**
 * LC 253 Meeting rooms 2
 * 
 * Q. Given an array of meeting time intervals intervals
 * where intervals[i] = [starti, endi],
 * return the minimum number of conference rooms required.
 * 
 * e.g. 
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * 
 
 [[0,30],[5,10],[15,20]]

[Approach1] 2 sorted arrs 
	1) build sorted int[] starts and ends 
	
		starts = 0, 5, 15
		         ^
		ends = 10, 20, 30
		        ^
	
	2) iter thru starts: starts[i]
		compare with end at j pointer
		   - currStart < currEnd? cnt++
		   - otherwise? update end pointer
	   
	   
		<-------------------->
		   <---->  <---->

[Approach2] priority queue
   - minHeap of end time 
   - leave only valid meetings in the pq
   
 * @author sunnypark
 *
 */
public class LC253_MeetingRooms2 {
    public int minMeetingRooms(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        int cnt = 0;
        int j = 0;
        
        for (int i = 0; i < starts.length; i++) {
            int currStart = starts[i];
            int currEnd = ends[j];
            
            if (currStart < currEnd) {
                cnt++;
            } else {
                j++;
            }
        }
        
        return cnt;
    }

}
