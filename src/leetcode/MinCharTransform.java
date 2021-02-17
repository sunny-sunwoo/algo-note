package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MinCharTransform {
	
	public static int findMinMoves(String src, String target) {
		Map<Character, Character> cache = new HashMap<>();
		int res = 0;
		for (int i = 0; i < src.length(); i++) {
			char c1 = src.charAt(i);
			char c2 = target.charAt(i);

			if (c1 == c2 || cache.containsKey(c1) && cache.get(c1).equals(c2)) {
				continue;
			}
			
			if (!cache.containsKey(c1)) {
				res++;
				cache.put(c1, c2);
			} else if (!cache.get(c1).equals(c2)) {
				throw new IllegalArgumentException("Cant");
			}
		}
		 
		// abd -> bae
		int tmp = 0;
		for (char key : cache.keySet()) {
			char value = cache.get(key);
			if (cache.containsKey(value) && cache.get(value) == key) {
				tmp++;
			}
		}

		return res + tmp/2;
	}
	
//	if (cache.keySet().size() == cache.values().size() && cache.keySet().containsAll(cache.values())) {
//	res++;
//}

//if (cache.keySet().equals(cache.values().stream().collect(Collectors.toSet()))) {
//	res++;
//}
	
	public static void main(String[] args) {
//		System.out.println(findMinMoves("aaa", "bbb"));
//		System.out.println(findMinMoves("ababcc", "cccccc"));
		System.out.println(findMinMoves("abd", "bae"));
		
		Set<Integer> hs = new HashSet<>() {{add(1); add(2); add(3); add(4);}};
		List<Integer> list = new ArrayList<>() {{add(1); add(2); }};
		
		System.out.println(hs.containsAll(list));
	}
	

}
