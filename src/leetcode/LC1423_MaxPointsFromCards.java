package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * LC 1423 Maximum Points You Can Obtain from Cards
 * Q. Given the integer array cardPoints and the integer k, return the maximum score you can obtain. 
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * 
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * 
	- edge case: 
	   i. card length <= k: take all
	   ii. card has negative number? maybe not
	   
	=> recursive: keep path. 
	terminated condition
	    path size == k? rt
	 
	logic
	    take max from beginning or end
	    add to path
	    recursive call: path


 * @author sunnypark
 *
 */
public class LC1423_MaxPointsFromCards {
	public int maxScore(int[] cardPoints, int k) {
        if (cardPoints.length <= k) {
            return IntStream.of(cardPoints).sum();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        buildPath(cardPoints, result, path, k, 0, cardPoints.length - 1);
        return pickMaxScore(result);
    }
    
    private int pickMaxScore(List<List<Integer>> result) {
        int maxScore = 0;
        for (List<Integer> path : result) {
            int curr = path.stream().mapToInt(Integer::intValue).sum();
            maxScore = Math.max(maxScore, curr);
        }
        return maxScore;
    }
    
    private void buildPath(int[] cardPoints, List<List<Integer>> result, List<Integer> path, int k, int lo, int hi) {
        if (path.size() == k) { 
            // if max score? => add to result 
            result.add(new ArrayList<>(path));
            return;
        }
        
        // pick lo
        if (lo <= cardPoints.length - 1) {
            path.add(cardPoints[lo]);
            buildPath(cardPoints, result, path, k, lo + 1, hi);
            path.remove(path.size() - 1);
        }
        
        // pick hi
        if (hi >= 0) {
            path.add(cardPoints[hi]);
            buildPath(cardPoints, result, path, k, lo, hi - 1);
            path.remove(path.size() - 1);
        }
    }
}
