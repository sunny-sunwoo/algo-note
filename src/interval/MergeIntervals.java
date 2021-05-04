package interval;

import java.util.*;

/**
 * Merge Intervals
 * 
 * intervals: 6 cases
 * <--> <===>
 * 
 * <-->
 *   <===>
 *   
 * <----->
 *   <==>
 *   
 * <==> <-->
 * 
 * <==>
 *   <-->
 *   
 * <======>
 *   <-->  
 *    
 * [approach] interval class
 * 1. sort by starting time (OR use minHeap)
 * 2. assign first one to curr
 * 3. iter thru list
 *    - overlapped? update curr
 *    - otherwise? add curr to res, move to next one
 * 4. deal with last one
 * 
 * isOverlapped? 
 *  curr.end >= next.start
 * e.g.
 * (1,3) (2,4) (5,6)
 *                   ^
 *  curr= 5,6
 *  res = 1,4 / 5,6       
 * 
 *  Time complexity: O(NlogN), N = num of intervals
 *  Space complexity: O(N)
 *
 * @author sunnypark
 *
 */
public class MergeIntervals {
  public static List<Interval> merge(List<Interval> intervals) {
    List<Interval> mergedIntervals = new ArrayList<Interval>();
    Collections.sort(intervals);
    
    Interval curr = intervals.get(0);
    for (int i = 1; i < intervals.size(); i++) {
    	Interval next = intervals.get(i);
    	if (curr.end >= next.start) {
    		curr.end = Math.max(curr.end, next.end);
    	} else {
    		mergedIntervals.add(curr);
    		curr = next;
    	}
    }
    
    mergedIntervals.add(curr);
    return mergedIntervals;
  }
  
  private static class Interval implements Comparable<Interval>{
	  int start;
	  int end;

	  public Interval(int start, int end) {
		  this.start = start;
		  this.end = end;
	  }
	  
	  @Override
	  public int compareTo(Interval other) {
		  if (start != other.start) {
			  return start - other.start;
		  }
		  return end - other.end;
	  }
  }

  public static void main(String[] args) {
    List<Interval> input = new ArrayList<Interval>();
    input.add(new Interval(1, 4));
    input.add(new Interval(2, 5));
    input.add(new Interval(7, 9));
    System.out.print("Merged intervals: ");
    for (Interval interval : MergeIntervals.merge(input))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input = new ArrayList<Interval>();
    input.add(new Interval(6, 7));
    input.add(new Interval(2, 4));
    input.add(new Interval(5, 9));
    System.out.print("Merged intervals: ");
    for (Interval interval : MergeIntervals.merge(input))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input = new ArrayList<Interval>();
    input.add(new Interval(1, 4));
    input.add(new Interval(2, 6));
    input.add(new Interval(3, 5));
    System.out.print("Merged intervals: ");
    for (Interval interval : MergeIntervals.merge(input))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();
  }
}