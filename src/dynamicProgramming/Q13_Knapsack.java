package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Knapsack
 * 
dp table => input.size() + 1, restriction + 1
dp[i][j] => result w/ i items, j weight
         => max value ( not pick OR pick )
            i) not pick: dp[i - 1][j]
            ii) pick: only if j >= currWeight
            	dp[i - 1][j - currWeight] + currVal

   | 0 1 2 3 4 5  6  7
 --+--------------------
 0 | 0 0 0 0 0 0  0  0 
 1 | 0 0 5 5 5 5  5  5
 2 | 0 0 5 7 7 12 12 12
 3 | 0
 4 | 0
 
 // new Item(2, 5), new Item(3, 7), new Item(5, 2), new Item(6, 3)
 
 * @author sunnypark
 *
 */
public class Q13_Knapsack {
	public static int knapsack_dp(List<Item> input, int restriction) {
		int[][] dp = new int[input.size() + 1][restriction + 1];
		for (int i = 1; i <= input.size(); i++) {
			Item currItem = input.get(i - 1);
			int currWeight = currItem.weight;
			int currValue = currItem.value;
			
			for (int j = 1; j <= restriction; j++) {
				int pick = dp[i - 1][j];
				int notPick = currWeight <= j ? dp[i - 1][j - currWeight] + currValue : 0;
				dp[i][j] = Math.max(pick, notPick);
			}
		}
		
		return dp[input.size()][restriction];
    }
	
	private static class Item {
        private final int weight;
        private final int value;
        private final int id;
        private static int number;
        
        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.id = number;
            number++;
        }
        
        @Override
        public String toString() {
            return "Item" + String.valueOf(id) + "(w" + weight + "-v" + value + ")";
        }
    }
    
    public static void main(String[] args) {
        List<Item> input = new ArrayList<>();
//        input.addAll(Arrays.asList(new Item(10, 7), new Item(3, 9), new Item(6, 5), new Item(8, 2)));
        input.addAll(Arrays.asList(new Item(2, 5), new Item(3, 7), new Item(5, 2), new Item(6, 3)));
        System.out.println(knapsack_dp(input, 7));
    }
}
