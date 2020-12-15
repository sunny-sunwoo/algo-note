package lastMin_2;

import java.util.Arrays;

public class CountPrimes {
	public static int countPrimes(int n) {
        boolean[] nonPrimeChecker = new boolean[n];
        
        for (int i = 2; i < n; i++) {
        	if (nonPrimeChecker[i]) continue;
        	checkNonPrimes(i, n, nonPrimeChecker);
        }
        
        int cnt = 0;
        for (int i = 2; i < n; i++) {
        	if (!nonPrimeChecker[i]) {
        		cnt++;
        	}
        }
        return cnt;
    }
	
	private static void checkNonPrimes(int start, int n, boolean[] cache) {
		for (int i = start * start; i < n; i += start) {
			cache[i] = true;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(countPrimes(10));
	}
}
