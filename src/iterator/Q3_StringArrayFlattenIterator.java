package iterator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Q3_StringArrayFlattenIterator implements Iterator<String> {
	Deque<Iterator<String>> itrQueue = new ArrayDeque<>();
	
	public Q3_StringArrayFlattenIterator(Iterable<Iterator<String>> inputs) {
		for (Iterator<String> input : inputs) {
			if (!input.hasNext()) {
				continue;
			}
			itrQueue.addLast(input);
		}
	}
	
	@Override
	public boolean hasNext() {
		return !itrQueue.isEmpty();
	}
	
	@Override
	public String next() {
		if (!this.hasNext()) {
			throw new NoSuchElementException();
		}
		Iterator<String> top = itrQueue.removeFirst();
		String res = top.next();
		
		if (top.hasNext()) {
			itrQueue.addLast(top);
		}
		
		return res;
	}
}
