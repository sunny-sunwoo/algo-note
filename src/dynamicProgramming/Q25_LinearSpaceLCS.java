package dynamicProgramming;

/**
 * lcs w/o(n)
 * 
 *    x a b c a
 *  - 0 0 0 0 0
 *  a 0 1 1 1 1
 *  c 0 1 1 2 2
 *  d 0 1 1 2 2
 *  a 0 1 1 2 3
 *  
 *  prev => 0 1 1 1 1
 *  curr =>
 *  
 *  curr[i] ? 
 *  	not same? max(prev[i], curr[i - 1])
 *  	same?  	  prev[i - 1]
 *  
 * @author sunnypark
 *
 */
public class Q25_LinearSpaceLCS {

}
