package practice;

public class RootOfNumber {
	static double root(double x, int n) {
		double lo = 0;
		double hi = x/n;

		while (lo <= hi) {
			double mid = lo + (hi - lo) / 2;
			double midPower = Math.pow(mid, n);
			if (Math.abs(x - midPower) <= 0.001) {
				return Double.parseDouble(String.format("%.3f", mid));
			}

			if (midPower > x) {
				hi = mid;
			} else {
				lo = mid;
			}
		}

		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println(root(7,3)); // 1.913
	}
}
