package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Storage Optimization
 * 
 * [approach] visualize using unit number!
 * https://aonecode.com/aplusplus/interviewctrl/getInterview/90812391
 * 
 * - build arraylist for horizontal (1 ~ n+1) and vertical (1 ~ m + 1)
 * 		=> should exclude units from input h/v
 * 
 * - iter thru list
 * 		=> pick max width, height 
 * 
 * @author sunnypark
 *
 */
public class StorageOptimization {
	public static int findMaxArea(int n, int m, int[] h, int[] v) {
		List<Integer> hList = new ArrayList<>();
		List<Integer> vList = new ArrayList<>();
		
		Set<Integer> hSetToRemove = IntStream.of(h).boxed().collect(Collectors.toSet());
		Set<Integer> vSetToRemove = IntStream.of(v).boxed().collect(Collectors.toSet());
		
		init(hList, hSetToRemove, n);
		init(vList, vSetToRemove, m);
		
		int maxH = getMaxUnit(hList);
		int maxV = getMaxUnit(vList);

		return maxH * maxV;
	}
	
	private static void init(List<Integer> list, Set<Integer> setToRemove, int last) {
		for (int i = 1; i <= last + 1; i++) {
			if (setToRemove.contains(i)) {
				continue;
			}
			list.add(i);
		}
	}
	
	private static int getMaxUnit(List<Integer> list) {
		int prev = 0;
		int max = 0;
		for (int curUnit : list) {
			max = Math.max(max, curUnit - prev);
			prev = curUnit;
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[] h1 = {4};
		int[] v1 = {2};
		System.out.println(findMaxArea(6, 6, h1, v1)); // 4
		
		int[] h2 = {1,2,3};
		int[] v2 = {1,2};
		System.out.println(findMaxArea(3, 2, h2, v2)); // 12
	}
}
