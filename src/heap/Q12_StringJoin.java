package heap;

/**
 * Strings -> concat
 *  2 + 2 => cost 4
 *  4 + 4 => cost 8  
 *    => total cost: 12
 *    
 *  2 + 4 => cost 6
 *  6 + 4 => cost 10
 *    => total cost: 16
 *  
 *  input: string[]
 *  output: int (min cost)
 *  
 *  [solution]
 *  minHeap => add all length e.g. (2, 2, 3, 3, 4)
 *  
 *  while minHeap is not empty
 *    => poll
 *    => acc to the total 
 *  
 *  rt total
 *  
 *  => tc: O(nlogn)
 *  
 *  
 *  total = 46
 *  pq => (18)
 *         ^ 
 *         
 *   => rt 46
 *  
 * @author sunnypark
 *
 */
public class Q12_StringJoin {

}
