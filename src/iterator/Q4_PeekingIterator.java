package iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Q4_PeekingIterator<E> implements Iterator<E> {
	Iterator<? extends E> iterator;
	boolean hasPeeked;
	E peekedItem;

	public Q4_PeekingIterator(List<? extends E> input) {
		iterator = input.iterator();
	}
	
	public E peek() {
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
	public E next() {
		if (!hasPeeked) {
			if (!iterator.hasNext()) {
				throw new NoSuchElementException();
			}
			return iterator.next(); 
		}
		
		E next = peekedItem;
		hasPeeked = false;
		peekedItem = null;
		return next;
	}
	
	public static void main(String[] args) {
		List<Integer> input = List.of(1,2,3);
		Q4_PeekingIterator<Integer> peekingIterator = new Q4_PeekingIterator<Integer>(input);
		
		System.out.println(peekingIterator.peek()); // expected: 1
		System.out.println(peekingIterator.hasNext()); // expected: true 
		System.out.println(peekingIterator.next()); // expected: 1
		System.out.println(peekingIterator.next()); // expected: 2
		System.out.println(peekingIterator.peek()); // expected: 3
		System.out.println(peekingIterator.next()); // expected: 3
		System.out.println(peekingIterator.hasNext()); // expected: false 
	}
}
