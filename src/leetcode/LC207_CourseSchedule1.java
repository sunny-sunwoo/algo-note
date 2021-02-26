package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LC 207 Course Schedule 1
 * 
 * Q. Return true if you can finish all courses. Otherwise, return false.
 *	prerequisites[i] = [ai, bi] (bi -> ai)
 * 
 * [approach] topological sorting
	L<L<I>> adjList
	int[] inDegree
	
	build queue with indegree-0 nodes
	
	while queue is not empty
	 - poll
	 - add to visited
	 - for all next
	     => decrement indegree by 1 
	     => if indegree becomes 0 ? add to queue
	     
	return if visited size == num courses
 * 
 * 
 * @author sunnypark
 *
 */
public class LC207_CourseSchedule1 {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        int[] indegree = new int[numCourses];
        init(adjList, indegree, prerequisites);
        
        
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int cnt = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            cnt++;
            
            for (int nxt : adjList.get(curr)) {
                if (--indegree[nxt] == 0) {
                    queue.offer(nxt);
                }
            }
            adjList.get(curr).clear();
        }
        return cnt == numCourses;
    }
    
    private void init(List<List<Integer>> adjList, int[] indegree, int[][] prerequisites) {
        for (int i = 0; i < indegree.length; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        
        // pre[1] -> pre[0]
        for (int[] pre : prerequisites) {
            int from = pre[1];
            int to = pre[0];
            
            adjList.get(from).add(to);
            indegree[to]++;
        }
    }
}
