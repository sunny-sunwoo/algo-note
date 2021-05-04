package interval;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * Q. For ‘K’ employees, we are given a list of intervals representing each employee’s working hours. 
 * Our goal is to determine if there is a free interval which is common to all employees.
 * 
 * Input: Employee Working Hours=[[[1,3], [9,12]], [[2,4]], [[6,8]]]
 * Output: [4,6], [8,9]
 * 
 * ref: grokking the coding interview
 
 [approach1]
  init and build list of merged intervals 
  determine free time
  
 [approach2]
  use k pointers => O(NlogK)
  
  
 [1,3], [7,12]
              ^    
 
 [2,4] 
       ^
 
 [6,8] 
       ^
  
 [5,7] [8,10]
              ^

	pq 
	[1,4] [5,12]

 1) init size-k pq w/ first intervals
 
 2) while pq is not empty
    => poll
    => has next ? add to the pq
    => if prev and curr overlapped? update prev 
       else? add prev to result, prev = curr
       
 3) post-process: merged working hrs -> free time list
 
 Time: O(NlogK)
 Space: O(K)
 * @author sunnypark
 *
 */
public class EmployeeFreeTime {
	public static List<Interval> findFreeTime(List<List<Interval>> workingHours) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		init(pq, workingHours);
		List<Interval> mergedWorkingHours = new ArrayList<>();		

		Node prev = pq.peek();
		
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (curr.hasNext()) {
				pq.offer(curr.toNext());
			}
			
			if (prev.getEnd() >= curr.getStart()) { // if overlapped, update prev
				prev.setStart(Math.min(prev.getStart(), curr.getStart()));
				prev.setEnd(Math.max(prev.getEnd(), curr.getEnd()));
			} else { // otherwise, add to list
				mergedWorkingHours.add(prev.getInterval());
				prev = curr;
			}
		}
		
		mergedWorkingHours.add(prev.getInterval()); // deal w/ last one
		return postProcess(mergedWorkingHours);
	}
	
	private static void init(PriorityQueue<Node> pq, List<List<Interval>> workingHours) {
		for (List<Interval> currEEHours : workingHours) {
			Iterator<Interval> itr = currEEHours.iterator();
			Interval firstInterval = itr.next();
			pq.add(new Node(firstInterval, itr));
		}
	}
	
	private static List<Interval> postProcess(List<Interval> mergedWorkingHours) {
		List<Interval> freeTimes = new ArrayList<>();
		Interval prev = mergedWorkingHours.get(0);
		for (int i = 1; i < mergedWorkingHours.size(); i++) {
			Interval curr = mergedWorkingHours.get(i);
			freeTimes.add(new Interval(prev.end, curr.start));
			prev = curr;
		}
		return freeTimes;
	}

	private static class Node implements Comparable<Node>{
		Interval interval;
		Iterator<Interval> itr;
		
		// constructor
		public Node(Interval interval, Iterator<Interval> itr) {
			this.interval = interval;
			this.itr = itr;
		}
		
		boolean hasNext() {
			return itr.hasNext();
		}
		
		Node toNext() {
			Interval next = itr.next();
			return new Node(next, itr);
		}
		
		int getStart() {
			return interval.start;
		}
		
		int getEnd() {
			return interval.end;
		}
		
		void setStart(int newStart) {
			interval.start = newStart;
		}
		
		void setEnd(int newEnd) {
			interval.end = newEnd;
		}
		
		Interval getInterval() {
			return interval;
		}

		@Override
		public int compareTo(Node other) {
			// TODO Auto-generated method stub
			return Integer.compare(this.getStart(), other.getStart());
		}
		
		@Override
		public String toString() {
			return interval.toString();
		}
	}
	
	private static class Interval {
		int start;
		int end;
		
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public String toString() {
			return "[" + start + "," + end + "]";
		}
	}
	
	public static void main(String[] args) {
		List<Interval> ee1 = List.of(new Interval(1,3), new Interval(9,12));
		List<Interval> ee2 = List.of(new Interval(2,4));
		List<Interval> ee3 = List.of(new Interval(6,8));
		List<Interval> ee4 = List.of(new Interval(5,7));
		
		System.out.println(findFreeTime(List.of(ee1, ee2, ee3, ee4)));
	}
}
