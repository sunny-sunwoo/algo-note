package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q13_Knapsack {
	public static int knapsack_dp(List<Item> input, int restriction) {
        
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
        System.out.println(knapsack_dp(input, 6));
    }
}
