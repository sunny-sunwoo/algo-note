package practice.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * Version Snapshot
 * Q. Design a ds that stores a list of nums of size N.
 * 
 * set(idx, val)
 * takeSnapshot()
 * getSnapshot(versionNum)
 * 
 * [brute force]
 * instance vars
 * - version num
 * - Map<I,List<I>> 
 *   => version to list: keep each version list
 * 
 * [optimize]
 * instance vars
 * - Map<I, TM<I,I>> cache
 *   => idx to version-val
 *  
 * - List<I> tmp list (null, null, 9)
 *   => idx to val: keep tmp
 * 
 * - int versionNumber
 *   
 *     v0    v1     v2    v3   
 * 0=(0,1)        (2,6)
 * 1=(0,2) (1,5)   
 * 2=(0,3)               (3,9)
 * 
 * methods
 * - set: keep in tmp list
 * - takeSnapshot: tmp list -> cache
 * - getSnapshot: 
 *    iter thru entrySet & build result list
 *     for each value: floorKey(version num)
 * 
 * @author sunnypark
 *
 */
public class VersionSnapshot {
	private int versionNumber;
	private final Map<Integer, NavigableMap<Integer, Integer>> cache;
	private final Integer[] tmp;
	
	public VersionSnapshot(int[] input) {
		versionNumber = 0;
		cache = new HashMap<>();
		tmp = new Integer[input.length];
		
		for (int i = 0; i < input.length; i++) {
			set(i, input[i]);
			cache.put(i, new TreeMap<>());
		}
		
		takeSnapshot();
	}
	
	public void set(int idx, int val) {
		if (idx < 0 || idx >= tmp.length) {
			throw new IllegalArgumentException("invalid idx");
		}
		tmp[idx] = val;
		
		
	}
	
	public void takeSnapshot() {
		for (int i = 0; i < tmp.length; i++) {
			Integer curr = tmp[i];
			if (curr == null) {
				continue;
			}
			cache.get(i).put(versionNumber, curr);
		}
		
		versionNumber++;
	}
	
	public List<Integer> getSnapshot(int version) {
		if (version < 0 || version > this.versionNumber) {
			throw new IllegalArgumentException("invalid version");
		}
		List<Integer> result = new ArrayList<>();
		for (Map.Entry<Integer, NavigableMap<Integer, Integer>> entry : cache.entrySet()) {
			NavigableMap<Integer, Integer> currVal = entry.getValue();
			result.add(currVal.floorEntry(version).getValue());
		}
		return result;
	}
	
	public static void main(String[] args) {
		VersionSnapshot tester = new VersionSnapshot(new int[] {1, 2, 3});
		tester.set(1, 4);
		tester.set(1, 5);
		
		tester.takeSnapshot();
		System.out.println("version0 " + tester.getSnapshot(0));
		System.out.println("version1 " + tester.getSnapshot(1));
	}
}
