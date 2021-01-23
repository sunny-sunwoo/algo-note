package practice;

import java.util.ArrayList;
import java.util.List;


/**
 * LC 1548. The Most Similar Path in a Graph
 * 
 * [Approach] dfs with memoization
 * @author sunnypark
 *
 */
public class LC1548_TheMostSimilarPathInAGraph {
	public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        List<List<Integer>> adjList = buildAdjList(n, roads);
        List<Integer> path = buildPath(n);
        Pair[][] dp = new Pair[n][targetPath.length];
        Pair distAndList = dfs(dp, adjList, path, names, 0, targetPath);
        return distAndList.second;
    }
    
    private Pair dfs(Pair[][] dp, List<List<Integer>> adjList, List<Integer> path, String[] names, int targetPos, String[] targetPath) {
        if (targetPos >= targetPath.length) {
            return new Pair(0, new ArrayList<>());
        }

        Pair candidate = new Pair(Integer.MAX_VALUE, new ArrayList<>());
        for (int i = 0; i < path.size(); i++) {
            int currIdx = path.get(i);
            int currDist = getDist(targetPath[targetPos], names[currIdx]);
            List<Integer> nextPath = new ArrayList<>(adjList.get(currIdx));
            
            Pair nextDistAndList;
            
            if (dp[currIdx][targetPos] != null) {
                nextDistAndList = dp[currIdx][targetPos];
            } else {
                nextDistAndList = dfs(dp, adjList, nextPath, names, targetPos + 1, targetPath);
                int newDist = currDist + nextDistAndList.first;
                List<Integer> newList = new ArrayList<Integer>(nextDistAndList.second);
                newList.add(0, currIdx);
                nextDistAndList = new Pair(newDist, newList);
            }

            dp[currIdx][targetPos] = nextDistAndList;
            if (nextDistAndList.first < candidate.first) {
                candidate = nextDistAndList;
            }
        }
        return candidate;
    }
    
    private int getDist(String a, String b) {
        return (a.equals(b)) ? 0 : 1;
    }

    private List<List<Integer>> buildAdjList(int n, int[][] roads) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }
        
        return adjList;
    }
    
    private List<Integer> buildPath(int n) {
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            path.add(i);
        }
        return path;
    }
    
    private class Pair {
        int first; // dist
        List<Integer> second; // list
        
        Pair(int first, List<Integer> second) {
            this.first = first;
            this.second = second;
        }
        
        @Override
        public String toString() {
            return first + " " + second;
        }
    }
}
