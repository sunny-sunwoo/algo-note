package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/discuss/interview-question/700965/throttling-gateway
 * @author sunnypark
 *
 */
public class ThrottlingGateway {

	private static final int ONE_SEC_MAX = 3;
	private static final int TEN_SEC_MAX = 20;
	private static final int SIXTY_SEC_MAX = 60;
	public static int droppedRequests(List<Integer> requestTimes){
		int totalDrop = 0;
		Map<Integer, Integer> cache = new HashMap<>();
		
		for (int time : requestTimes) {
			cache.put(time, cache.getOrDefault(time, 0) + 1);
		}
		
		int lastTime = requestTimes.get(requestTimes.size() - 1);
		int[] reqAcc = new int[lastTime + 1];
		
		for (int i = 1; i <= lastTime; i++) {
			reqAcc[i] = cache.getOrDefault(i, 0);
		}
		
		for (int i = 1; i <= lastTime; i++) {
			if (!cache.containsKey(i)) {
				continue;
			}
			
			int currDrop = 0;
			int oneSecondReq = cache.get(i);
			
			// validate one sec rule
			if (oneSecondReq > ONE_SEC_MAX) {
				currDrop = oneSecondReq - ONE_SEC_MAX;
			}
			
			// validate ten sec rule
			int tenSecondReq = reqAcc[i] - reqAcc[Math.max(i - 9, 0)];
			if (tenSecondReq > TEN_SEC_MAX) {
				int exceeded = Math.min(oneSecondReq, tenSecondReq - TEN_SEC_MAX);
				currDrop = Math.max(currDrop, exceeded);
			}
			
			// validate sixty sec rule
			int sixtySecondReq = reqAcc[i] - reqAcc[Math.max(i - 59, 0)];
			if (sixtySecondReq > SIXTY_SEC_MAX) {
				int exceeded = Math.min(sixtySecondReq, sixtySecondReq - SIXTY_SEC_MAX);
				currDrop = Math.max(currDrop, exceeded);
			}
			
			totalDrop += currDrop;
			
		}
		return totalDrop;
	}
	
	public static void main(String[] args) {
		List<Integer> input = List.of(1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 11, 11, 11, 11);
		System.out.println(droppedRequests(input));
	}
}
