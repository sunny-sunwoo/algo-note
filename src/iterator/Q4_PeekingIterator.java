package iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Q4_PeekingIterator implements Iterator<Integer> {
	Iterator<Integer> iterator;
	boolean hasPeeked;
	Integer peekedItem;

	public Q4_PeekingIterator(List<Integer> input) {
		iterator = input.iterator();
	}
	
	public Integer peek() {
		if (hasPeeked) {
			return peekedItem;
		}
		
		if (!iterator.hasNext()) {
			return null;
		}
		// need to peek next if possible
		hasPeeked = true;
		peekedItem = iterator.next();
		return peekedItem;
	}

	@Override
	public boolean hasNext() {
		if (hasPeeked) {
			return true;
		}
		
		return iterator.hasNext();
	}

	@Override
	public Integer next() {
		if (!hasPeeked) {
			throw new NoSuchElementException();
		}
		
		if (hasPeeked) {
			Integer next = peekedItem;
			hasPeeked = false;
			peekedItem = null;
			return next;
		}
		
		return iterator.next();
	}
	
	public static void main(String[] args) {
		List<Integer> input = List.of(1,2,3);
		Q4_PeekingIterator peekingIterator = new Q4_PeekingIterator(input);
		
		System.out.println(peekingIterator.peek()); // expected: 1
		System.out.println(peekingIterator.hasNext()); // expected: true 
		System.out.println(peekingIterator.next()); // expected: 1
		System.out.println(peekingIterator.next()); // expected: 2
		System.out.println(peekingIterator.peek()); // expected: 3
		System.out.println(peekingIterator.next()); // expected: 3
		System.out.println(peekingIterator.hasNext()); // expected: false 
	}
}
