package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * LC 380 Insert Delete GetRandom O(1)
 * 
	random access using index => ARRAY
	quick search if contained & no shifting => HASHSET

	      0 1 2 3
	list  a b c d 

	hashmap (a, 0) (b, 1) (c, 2), (d, 3)

[logic]
	add
	 - already contained in hm? rt false
	 - add to list and hm
	 - rt true
	 
	remove
	 - not contained in hm? rt false
	 - idxToRemove from hm
	    - if idxToRemove != last index ? swap with last, rm last AND update hm
	    - otherwise, rm last from list and map
	 - rt true
	
	getRandom
	 - find random idx from 0 ~ size
	 - rt the value @ random idx
	 
	 101, 110, 111, 000
	   ^
	 001
	 111
	 100
	  
	 n * k
 * @author sunnypark
 *
 */
public class LC380_RandomizedSet {
    List<Integer> list;
    Map<Integer/* val */, Integer /* idx */> hm;
    Random rand;

    /** Initialize your data structure here. */
    public LC380_RandomizedSet () {
        list = new ArrayList<>();
        hm = new HashMap<>();
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (hm.containsKey(val)) {
            return false;
        }
        
        list.add(val);
        hm.put(val, list.size() - 1);
        
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!hm.containsKey(val)) {
            return false;
        }
        int idxToRemove = hm.get(val);
        int lastIdx = list.size() - 1;
        if (idxToRemove != lastIdx) {
            int currLastVal = list.get(lastIdx);
            list.set(idxToRemove, currLastVal);
            hm.put(currLastVal, idxToRemove);
        } 
        
        list.remove(lastIdx);
        hm.remove(val);
        
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int randomIdx = rand.nextInt(list.size());
        return list.get(randomIdx);
    }
}
