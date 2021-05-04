package interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Minimum Meeting Rooms
 * Q. Given a list of intervals representing the start and end time of ‘N’ meetings,
 * find the minimum number of rooms required to hold all the meetings.
 * <=> find MAXIMUM overlapping at any point
 *  
 *  e.g.
 *  
 *   <---> <---> : 1
 *   
 *   <--->
 *      <-->  <--> : 2
 *      
 *   <------>
 *     <------->
 *         <------> : 3
 *         
 * [approach1] pq = keep all active meetings at any points
 * build pq (= min heap of end value) 
 *   => to see any meeting that has ended earlier
 *   
 * iter thru all interval
 *  1. while there is any finished meeting (pq.peek) 
 *     = not overlapped? (has any meeting ended)
 *     => poll top from pq
 *  2. add new
 *  3. compare pq size with max
 *
 * Time complexity: O(NlogN)
 *
 * [approach2] starts, ends
 *  <--->		<-->
 *     <---->  
 *              <---->
 *  
 *  starts = 1,2,5,6
 *         i       ^
 *           
 *  ends   = 3,4,7,8
 *         j     ^
 * 
 *  1. build starts and ends
 *  
 *  2. iter thru starts
 *     i) curr start < curr end ? 
 *        cnt++
 *        compare w/max
 *        
 *     ii) otherwise?
 *        update curr end
 *     
 * @author sunnypark
 *
 */
public class MinimumMeetingRooms {
	public static int getMinMeetingRooms(List<Meeting> input) {
		int total = 0;
		Collections.sort(input, (a,b) -> Integer.compare(a.start, b.start)); // start ascending
		PriorityQueue<Meeting> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.end, b.end)); // NlogN
		
		for (Meeting meet : input) {
			// remove completed meetings
			while (!pq.isEmpty() && !isOverlapped(pq.peek(), meet)) { // logN
				pq.poll();
			}
			
			// add new
			pq.offer(meet);
			
			// keep max size
			total = Math.max(total, pq.size());
		}
		return total;
	}
	
	private static boolean isOverlapped(Meeting prev, Meeting curr) {
		return prev.end > curr.start;
	}
	
	private static class Meeting {
		int start;
		int end;
		
		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	public static void main(String[] args) {
		Meeting m1 = new Meeting(1,3);
		Meeting m2 = new Meeting(2,4);
		Meeting m3 = new Meeting(5,8);
		Meeting m4 = new Meeting(6,7);
		
		List<Meeting> input = new ArrayList<>();
		input.add(m2);
		input.add(m1);
		input.add(m4);
		input.add(m3);
		System.out.println(getMinMeetingRooms(input));
	}
}
