package dynamicProgramming;

/**
 * Sum Combination
 * Q. find num of ways to make sum with n numbers.
 * 
 * e.g. sum = 5, n = 2
 * 
 * (0,4) (1,3) (2,2) (3,1) (4,0)
 * (0,5) (1,4) (2,3) (3,2) (4,1) (5,0)
 * 
 *      ways(sum, n) = ways(sum-1, n) + ways(sum, n-1)
 *      
 *      0 1 2 3 4 5
 * n=1  1 1 1 1 1 1
 * n=2  1 2 3 4 5 6   
 *        ^
 *      (1,0)
 *      (0,1)
 *      
 * n=3  1 3 6
 *          ^
 *         (2,0)   => (2,0,0)
 *         (1,1)   => (1,1,0)
 *         (0,2)   => (0,2,0)
 *         (1,0,0) => (1,0,1)
 *         (0,1,0) => (0,1,1)
 *         (0,0,1) => (0,0,2)
 * 
 *  ways[i][j] = ways[i - 1][j] + ways[i][j - 1]    
 * @author sunnypark
 *
 */
public class Q29_SumCombination {

}
