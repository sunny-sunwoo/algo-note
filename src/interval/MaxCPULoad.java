package interval;

import java.util.List;
import java.util.PriorityQueue;

/*
 * Q. Maximum CPU Load 
 * We are given a list of Jobs. Each job has a Start time, an End time, and a CPU load when it is running.
 * Our goal is to find the maximum CPU load at any time if all the jobs are running on the same machine.
 * 
 * input: [[1,4,3], [2,5,4], [6,9,6], ]
 *          ^      
 * output: 7
 * 
 * input: [[1,10,2], [2,4,1], [3,6,5]]
 *           ^ 
 * output: 8
 * 
 * prev (5,6)
 * overlapping (5,6//load)
 * 
 * <----------------->
 *     <--->
 *            <------>
 *                <-->
 *     <===>      <==>    
 *    
 *     
 * pq = minHeap of end time
 *    => keeps valid jobs only
 * 
 * iter thru jobs
 *  1. rm all finished job // while pq.peek().end <= curr start
 *     - poll
 *     - decrement from currLoad 
 *       
 *  2. offer to curr job to pq
 *  3. max load check
 * 
 * maxLoad = max (maxLoad, currLoad)
 * 
 * Time: O(NlogN)
 * Space: O(N)
 * */
public class MaxCPULoad {
	public static int maxCpuLoad(List<Job> jobs) {
		PriorityQueue<Job> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.end, b.end));
		int maxLoad = 0;
		int currLoad = 0;
		for (Job job : jobs) {
			// remove all jobs that have ended
			while (!pq.isEmpty() && pq.peek().end <= job.start) {
				currLoad -=  pq.poll().load;
			}

			// add the current job into the minHeap
			pq.offer(job);
			currLoad += job.load;
			maxLoad = Math.max(maxLoad, currLoad);
		}
		return maxLoad;
	}
	
	private static class Job {
		int start;
		int end;
		int load;
	}
}
