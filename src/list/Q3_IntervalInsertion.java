package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * q3. insert new interval
 *  
 * intervals = [[1,3], [4,6], [9,12]], 
 * new interval = [5,8]
 * 
 * output = [[1,5], [6,9]]
 * 
 *  ...left intervals  / NEW INTERVAL /  right intervals... 
 *  
 * intervals = valid, sorted
 * left => .... [1,3]   <=> curr[1] < s
 * right => [9,12]....  <=> curr[0] > e
 *  
 * when any interval skipped ==> build new interval? 
 * left.size() + right.size() != intervals.length
 * 
 *    // left most 
 *    newStart = min (intervals[left.size()][0], s)
 *    
 *    // right most
 *    newEnd = max(intervals[intervals.length - right.size() - 1][1], e)
 *    
 *  => add newS,E to left 
 *  
 *  => rt merge(left, right)
 *    
 * 
 * @author sunnypark
 *
 */
public class Q3_IntervalInsertion {
	public static List<int[]> insertInterval(int[][] intervals, int[] newInterval) {
		int s = newInterval[0];
		int e = newInterval[1];
		
		List<int[]> leftList = new ArrayList<>();
		List<int[]> rightList = new ArrayList<>();

		for (int[] curr : intervals) {
			if (curr[1] < s) {
				leftList.add(curr);
			} else if (e < curr[0]) {
				rightList.add(curr);
			}
		}
		
		// if any interval overlapped?
		if (leftList.size() + rightList.size() != intervals.length) {
			int newStart = Math.min(intervals[leftList.size()][0], s);
			int newEnd = Math.max(intervals[intervals.length - rightList.size() - 1][1], e);
			leftList.add(new int[] {newStart, newEnd});
		}
		
		// merge left & right
		leftList.addAll(rightList);
		return leftList;
	}
	
	public static void main(String[] args) {
		int[][] intervals = {{1,3}, {4,6}, {9,12}};
		int[] newInterval = {5,8};
		
		List<int[]> res = insertInterval(intervals, newInterval);
		res.stream().map(i -> Arrays.toString(i))
					.forEach(each -> System.out.println(each));
	}
}
