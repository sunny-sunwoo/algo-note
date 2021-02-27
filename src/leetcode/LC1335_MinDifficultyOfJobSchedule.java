package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 1335 Minimum Difficulty of a Job Schedule
 * 
 * approach1. backtracking 
 * 	- building partitions list

	jobDifficulty = [11,/111,/22/,222,/33,/333,44,444], d = 6
	
	backtracking with building partitions list
	=> base case
	   partition num > d ?
	      - rt
	    
	   reached at last idx?
	       - partition num == d? keep in answer if necessary
	       - rt
	
	=> add curr job to the last partition
	   - add curr job to partition
	   - recursive call 
	   - rm curr job from partition  
	   
	=> create new partition and add + recursive call

 * approach2. backtracking with optimization
 * 	- no need to keep all numbers in partitions. need max number only for the answer
 * 
 * approach3. dp (knapsack style)
 * 
 * @author sunnypark
 *
 */
public class LC1335_MinDifficultyOfJobSchedule {
	int finalDifficulty = Integer.MAX_VALUE;
	
	/**
	 * Approach1. backtracking
	 * @param jobDifficulty
	 * @param d
	 * @return
	 */
	public int minDifficulty_backtracking(int[] jobDifficulty, int d) {
        if (jobDifficulty.length < d) {
            return -1;
        }
        List<List<Integer>> partitions = new ArrayList<>();
        canPartition_backtracking(jobDifficulty, 0, d, partitions);
        return finalDifficulty == Integer.MAX_VALUE ? -1 : finalDifficulty; //TODO
    }
	
    private void canPartition_backtracking(int[] jobDifficulty, int idx, int d, List<List<Integer>> partitions) {
        // base case
        if (partitions.size() > d) {
            return;
        }
        
        if (idx == jobDifficulty.length) {
            if (partitions.size() != d) {
                return;
            }
            int currDifficulty = calcMinDifficulty_backtracking(partitions);
            if (currDifficulty < finalDifficulty) {
                finalDifficulty = currDifficulty;
            }
            return;
        }
        
        // 1) add curr num to last partition
        if (!partitions.isEmpty()) {
            List<Integer> lastPartition = partitions.get(partitions.size() - 1);
            lastPartition.add(jobDifficulty[idx]);
            canPartition_backtracking(jobDifficulty, idx + 1, d, new ArrayList<>(partitions));
            lastPartition.remove(lastPartition.size() - 1);
        }
        
        // 2) OR create new partition
        List<Integer> newPartition = new ArrayList<>();
        newPartition.add(jobDifficulty[idx]);
        partitions.add(newPartition);
        canPartition_backtracking(jobDifficulty, idx + 1, d, partitions);
    }
    
    private int calcMinDifficulty_backtracking(List<List<Integer>> partitions) {
        int res = 0;
        for (List<Integer> partition: partitions) {
            res += partition.stream().mapToInt(i -> i).max().getAsInt();
        }
        
        return res;
    }
	

    /**
     * Approach2. Optimized Backtracking
     * @param jobDifficulty
     * @param d
     * @return
     */
    public int minDifficulty_backtrackingOptimized(int[] jobDifficulty, int d) {
        if (jobDifficulty.length < d) {
            return -1;
        }
        List<Integer> partitions = new ArrayList<>();
        canPartition_backtrackingOptimized(jobDifficulty, 0, d, partitions);
        return finalDifficulty == Integer.MAX_VALUE ? -1 : finalDifficulty; //TODO
    }
    
    private void canPartition_backtrackingOptimized(int[] jobDifficulty, int idx, int d, List<Integer> partitions) {
        // base case
        if (partitions.size() > d) {
            return;
        }
        
        if (idx == jobDifficulty.length) {
            if (partitions.size() != d) {
                return;
            }
            int currDifficulty = calcMinDifficulty(partitions);
            if (currDifficulty < finalDifficulty) {
                finalDifficulty = currDifficulty;
            }
            return;
        }
        
        // 1) add curr num to last partition
        if (!partitions.isEmpty()) {
            int lastPartition = partitions.get(partitions.size() - 1);
            partitions.set(partitions.size() - 1, Math.max(jobDifficulty[idx], lastPartition));
            canPartition_backtrackingOptimized(jobDifficulty, idx + 1, d, new ArrayList<>(partitions));
            partitions.set(partitions.size() - 1, lastPartition);
        }
        
        // 2) OR create new partition
        partitions.add(jobDifficulty[idx]);
        canPartition_backtrackingOptimized(jobDifficulty, idx + 1, d, new ArrayList<>(partitions));
    }
    
    private int calcMinDifficulty(List<Integer> partitions) {
        return partitions.stream().mapToInt(i->i).sum();
    }
    
    /**
     * Approach3. DP (knapsack style)
     * 
     * dp[i][j] => answer with i numbers, j partitions
     *     			m = i; m >= j; m--
     *              min (dp[i][j], dp[m - 1][j - 1] + getMax(jobDifficulty, m, i))
     *              
     *  
     *   [11,/111,/22/,222,/33,/333,44, 444 ], d = 6
     *   
     *  dp[n][k] <= min (dp[n][k], dp[X][k-1] + max(X+1, n))
     *                              ^ 
     *                   second last partition +  last partition
     *                             
     *                       partition = 3 
     *                       1, 2, / 3, 4, 5 
     * @param jobDifficulty
     * @param d
     * @return
     */
//    public int minDifficulty_dp(int[] jobDifficulty, int d) {
//        
//    }
    
}
