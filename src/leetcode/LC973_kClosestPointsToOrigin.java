package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * LC 973 k closest points to origin (0,0)
 
	build max heap 
	- sort by distance (hypot = sqrt of x^2 + y^2 in double)

	iter thru points 
	 - offer to heap
	 - if heap size > k? poll 
	 
	build result and rt

 * @author sunnypark
 *
 */
public class LC973_kClosestPointsToOrigin {
	public static List<int[]> kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> getDistance(b) - getDistance(a));
        
        for (int[] point : points) {
            maxHeap.offer(point);
            
            if (maxHeap.size() > K) {
                maxHeap.poll();
            }
        }
        
        return maxHeap.stream().collect(Collectors.toList());
    }

    private static int getDistance(int[] point) {
    	// return point[0]*point[0] + point[1]*point[1] 
        return (int) Math.hypot(point[0], point[1]);
    }
    
    private static int[][] buildResult(PriorityQueue<int[]> maxHeap) {
        int[][] result = new int[maxHeap.size()][2];
        int i = 0;
        int size = maxHeap.size();
        while (i < size) {
            result[i] = maxHeap.poll();
            i++;
        }
        return result;
    }
    
    public static void main(String[] args) {
    	int[][] points = {{3,3},{5,-1},{2,4}};
    	kClosest(points, 2).forEach(point -> System.out.println(Arrays.toString(point)));
    }
}
