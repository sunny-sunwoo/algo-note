package interval;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

/**
 * LC 715 Range Module
 * 
 <--->  <------>  <-->   <-->  <->
         <=================>
    
[addRange]
 tailSet(Range.of(0, left), true).iterator(): ranges from closest left .. end
 
 while itr has next
   prevRange
   prevRange.left > right ? => break
   
   keep min, max for new left, right
   rm prevRange
   
 add new range
  
[removeRange]
tailSet(Range.of(0, left), false).iterator(): ranges from closest left .. end

while itr has next
  prev range
  prev range.left => right? break
  
  // how to rm & add sides
  if prev right > left ?  // left end
     => add range of prev left, left
  
  if prev left < right // right end
     => add range of right, prev right
     
  rm prev range (itr.remove)   
  
[queryRange]
  ceiling w/right
  check left
  
 [private class] Range impls comparable
  TODO: compareTo, equals, hashCode
 compare w/right
 
 * @author sunnypark
 *
 */
public class RangeModule {
	TreeSet<Range> cache;

	// constructor
	public RangeModule() {
		cache = new TreeSet<>();
	}

	public void addRange(int left, int right) {
		Iterator<Range> itr = cache.tailSet(Range.of(0, left), false).iterator();

		while (itr.hasNext()) {
			// prev range setting
			Range prev = itr.next();

			// range out? break
			if (prev.left > right) {
				break;
			}

			// update min,max and rm prev one
			left = Math.min(left, prev.left);
			right = Math.max(right, prev.right);
			itr.remove();
		}

		cache.add(Range.of(left, right));
	}

	public boolean queryRange(int left, int right) {
		Range candidate = cache.higher(Range.of(0, left));
		return candidate != null && candidate.left <= left && right <= candidate.right;
	} 

	public void removeRange(int left, int right) {
		Iterator<Range> itr = cache.tailSet(Range.of(0, left), true).iterator();
		List<Range> toAdd = new ArrayList<>();

		while (itr.hasNext()) {
			// prev range setting
			Range prev = itr.next();

			// range out? break
			if (prev.left > right) {
				break;
			}

			// rm prev one, add sides accordingly
			if (prev.left < left) { // add left side
				toAdd.add(Range.of(prev.left, left));
			}

			if (right < prev.right) { // add right side
				toAdd.add(Range.of(right, prev.right));
			}

			itr.remove();
		}

		for (Range r : toAdd) {
			cache.add(r);
		}
	}

	private static class Range implements Comparable<Range>{
		int left;
		int right;

		private Range(int left, int right) {
			this.left = left;
			this.right = right;
		}

		public static Range of(int left, int right) {
			return new Range(left, right);
		}

		@Override
		public int compareTo(Range other) {
			if (other.right != this.right) {
				return Integer.compare(right, other.right);
			}

			return Integer.compare(left, other.left);
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Range)) {
				return false;
			}
			Range other = (Range) o;
			return this.left == other.left && this.right == other.right;
		}

		@Override
		public int hashCode() {
			return Objects.hash(left, right);
		}
	}
}
