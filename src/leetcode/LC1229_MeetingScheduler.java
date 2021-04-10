package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 1229
 * 
	slots1 [[10,50],[60,120],[140,210]],
	         ^
	slots2 [[0,15],[40,70]], duration = 8
	                 ^                       

	- use 2 ptrs i,j
	- while i, j is within valid
	    - if overlapped? 
	          max(s1, s2), min(e1, e2)
	          valid answer? return [start, start + 8]
	           
	    - proceed with one of the 2 ptrs (where end is earlier)
	       if e1 < e2 ? i++
	       else ? j++
	    
	- rt empty arr
	
	
  overlapped logic
	
	<-->       | <-->    |     <-->  |   <----->   |    <->     |        <-->
	     <==>  |   <==>  |   <==>    |     <=>     |   <=====>  |  <==>
	
	    
 * @author sunnypark
 *
 */
public class LC1229_MeetingScheduler {
	public static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        // 1) find overlapped intervals
        int i = 0, j = 0;
        
        Arrays.sort(slots1, (a,b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a,b) -> a[0] - b[0]);

        while (i < slots1.length && j < slots2.length) {
            int[] interval1 = slots1[i];
            int[] interval2 = slots2[j];
            
            if (isOverlapped(interval1, interval2)) {
                int start = Math.max(interval1[0], interval2[0]);
                int end = Math.min(interval1[1], interval2[1]);
                if (end - start >= duration) {
                    return List.of(start, start + duration);
                }
            }
            
            if (interval1[1] < interval2[1]) {
                i++;
            } else {
                j++;
            }
        }
        
        return List.of();
    }

    private static boolean isOverlapped(int[] interval1, int[] interval2) {
        return !((interval2[0] > interval1[1]) || (interval1[0] > interval2[1]));
    }
}
