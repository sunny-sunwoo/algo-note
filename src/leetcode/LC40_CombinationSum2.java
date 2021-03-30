package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC40 Combination sum 2
 * Q.Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
	
	Input: candidates = [10,1,2,7,6,1,5], target = 8
	Output: 
	[
	[1,1,6],
	[1,2,5],
	[1,7],
	[2,6]
	]
	

approach1. use set for result

approach2. use for loop with checking prev num
 => for loop will have starting points of each recursive call
 
 1-7
 x
 2-6

 * @author sunnypark
 *
 */
public class LC40_CombinationSum2 {
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(res, path, candidates, 0, target);
        return res;
    }
	
	private void backtrack(List<List<Integer>> result, List<Integer> path, int[] candidates, int pos, int target) {
        // base case
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = pos; i < candidates.length; i++) {
            if (i > pos && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (candidates[i] > target) {
                break;
            }
            path.add(candidates[i]);
            backtrack(result, path, candidates, i + 1, target - candidates[i]);
            path.remove(path.size() - 1);
        }
    }
}
