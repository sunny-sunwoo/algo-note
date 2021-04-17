package practice;

import java.util.HashMap;
import java.util.Map;

/**
 * 
You're given a list of tasks, and a cool down time between the same types of tasks. 
The only difference is that you cannot change the order of the tasks, 
so you need to calculate the time to complete the tasks based on the string given.

i.e. "ABACCA", cooldown = 2 would return a total time of 9
           ^
0 1 2 3 4 5 6 7 8 9
A B _ A C _ _ C A

linear scan

cnt = 1
iter thru all chars
 while hm contains key && cnt - value <= cool down
   cnt++
 
 map.put(curr char, cnt)
 
rt cnt 

no space solution: 
=> n^2 approach
=> O(26) + sliding window approach

 * @author sunnypark
 *
 */
public class TaskScheduler {
	public static int taskScheduler(String input, int coolDown) {
		int cnt = 0;
		Map<Character, Integer> hm = new HashMap<>();
		for (char c : input.toCharArray()) {
			while (hm.containsKey(c) && (cnt - hm.get(c)) <= coolDown) {
				cnt++;
			}
			hm.put(c, cnt++);
		}
		
		return cnt;
	}
	
	public static void main(String[] args) {
		System.out.println(taskScheduler("ABACCA", 2)); // A B _ A C _ _ C A (9)
		System.out.println(taskScheduler("ABACCA", 3)); // A B _ _ A C _ _ _ C A (11)
	}
}
