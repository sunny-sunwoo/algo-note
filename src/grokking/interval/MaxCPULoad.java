package grokking.interval;

/*
 * input: [[1,4,3], [2,5,4], [6,9,6], [7,8,3], [8,10,5]]
 *          ^      
 * output: 7
 * 
 * input: [[1,10,2], [2,4,1], [3,6,5], [5,6,5]]
 *                                       ^ 
 * output: 8
 * 
 * prev (5,6)
 * overlapping (5,6//load)
 * 
 * <----------------->
 *     <--->
 *            <------>
 *                <-->
 *                
 *     <===>      <==>    
 *     
 * 
 * 
 * */
public class MaxCPULoad {

}
