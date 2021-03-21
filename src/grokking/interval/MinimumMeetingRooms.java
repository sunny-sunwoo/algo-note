package grokking.interval;

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
 * [approach] pq = kepe all active meetings
 * build pq (= min heap of end value) 
 *   => to see any meeting that has ended earlier
 * iter thru all interval
 *  1. while there is any finished meeting (pq.peek) 
 *      -> not overlapped? (has any meeting ended)
 *      -> poll prev one
 *
 *  2. add new
 *  3. compare with max
 * 
 * @author sunnypark
 *
 */
public class MinimumMeetingRooms {

}
