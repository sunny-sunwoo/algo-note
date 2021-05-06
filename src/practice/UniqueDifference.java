package practice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 

Given two numbers a, b. Print all possible outputs and their paths where each path contains a unique difference of any two numbers in the set.

Ex.

Possible path given a = 7, b = 2:
[7, 2]
[7, 2, 5]
[7, 2, 5, 3]
[7, 2, 5, 3, 4]
[7, 2, 5, 3, 4, 1]
[7, 2, 5, 3, 4, 1, 6]

print all possible outputs:
[[7, 2], [7, 2, 5], [7, 2, 5, 3], [7, 2, 5, 3, 4], [7, 2, 5, 3, 4, 1], [7, 2, 5, 3, 4, 1, 6]]
[[7, 2], [7, 2, 5], [7, 2, 5, 3], [7, 2, 5, 3, 1], [7, 2, 5, 3, 1, 4], [7, 2, 5, 3, 1, 4, 6]]
[[7, 2], [7, 2, 5], [7, 2, 5, 3], [7, 2, 5, 3, 1], [7, 2, 5, 3, 1, 6], [7, 2, 5, 3, 1, 6, 4]]

BFS
queue<Node>
Node => List<List<Integer>> path, Set<Integer> visited

assume (a > b)

[7,2]
 -> [7,2,5]

 	-> [7,2,5,3]
 
		 -> [7,2,5,3,4]       
		 		=> [7, 2, 5, 3, 4, 1]
		   		=> [7, 2, 5, 3, 4, 1, 6]
		  
		 -> [7,2,5,3,1]
		 		=> [7, 2, 5, 3, 1, 4] -> [7, 2, 5, 3, 1, 4, 6]
		 		=> [7, 2, 5, 3, 1, 6] -> [7, 2, 5, 3, 1, 6, 4]
		 		
 		
 pop 
 if path size == a ? continue
  
  getNextDiffs
  for each diff 
   -> add new path at the end
   -> new visited 
   
 * @author sunnypark
 *
 */
public class UniqueDifference {
	public static List<List<List<Integer>>> uniqDiffPath(int a, int b) {
		List<List<List<Integer>>> result = new ArrayList<>();
		List<Integer> input = List.of(a,b);
		
		List<List<Integer>> firstPath = new ArrayList<>();
		firstPath.add(input);
		
		Set<Integer> firstVisited = new HashSet<Integer>() {{ add(a); add(b); }};
		
		Deque<Node> queue = new ArrayDeque<>();
		queue.addLast(new Node(firstPath, firstVisited));

		while (!queue.isEmpty()) {
			int size = queue.size();
			
			while (size-- > 0) {
				Node top = queue.removeFirst();
				List<List<Integer>> currPath = top.path;
				Set<Integer> visited = top.visited;
				List<Integer> last = currPath.get(currPath.size() - 1);
				
				Set<Integer> diffs = getNextDiffs(last, visited);
				if (diffs.size() == 0) {
					result.add(new ArrayList<>(currPath));
					continue;
				}
				
				for (int diff : diffs) {
					List<Integer> nextItem = new ArrayList<>(last);
					nextItem.add(diff);
					
					Set<Integer> nextVisited = new HashSet<>(visited);
					nextVisited.add(diff);
					
					List<List<Integer>> nextPath = new ArrayList<>(currPath);
					nextPath.add(nextItem);
					queue.addLast(new Node(nextPath, nextVisited));
				}
			}
			
		}
		return result;
	}
	
	private static Set<Integer> getNextDiffs(List<Integer> list, Set<Integer> visited) {
		Set<Integer> diffs = new HashSet<>();
		
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				int newDiff = Math.abs(list.get(i) - list.get(j));
				if (visited.contains(newDiff)) {
					continue;
				}
				diffs.add(newDiff);
			}
		}
		return diffs;
	}
	
	private static class Node {
		List<List<Integer>> path;
		Set<Integer> visited;
		
		Node(List<List<Integer>> path, Set<Integer> visited) {
			this.path = path;
			this.visited = visited;
		}
	}
	
	public static void main(String[] args) {
		System.out.print(uniqDiffPath(7,2));
		System.out.print(uniqDiffPath(6,3));
	}
}
