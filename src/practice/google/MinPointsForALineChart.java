package practice.google;

/**
 * input: list of pairs(timestamp, rate)
 * output: list of significant points
 * 
 * significant points = where slopes are getting changed
 * 
 * if input size <= 2 ? rt input
 * 
 * prev slope = getSlope(first, second)
 * 
 * res <- add first
 * 
 * iter thru input list (i = 1..len - 1)
 *   update candidate (= curr point)
 *   curr slope = getSlope(i val, i+1 val)
 *   
 *   if slope changed? 
 *      add candidate to res
 *      update prev slope w/ curr slope
 *      
 *  
 * res <- add last one
 * return res
 * 
 * e.g. (0,0) (1,1) (2,2) (3,2) (4,2)
 *                          ^
 *  
 *  res = (0,0), (2,2), (4,2)
 *  candidate = (3,2)
 *  prev slope = 0
 *  curr slope = 
 *  
 *  
 * @author sunnypark
 *
 */
public class MinPointsForALineChart {

}
