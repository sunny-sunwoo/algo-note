package iterator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Q3_StringArrayFlattenIterator implements Iterator<String> {
	Deque<Iterator<String>> itrQueue = new ArrayDeque<>();
	
	public Q3_StringArrayFlattenIterator(Iterable<Iterator<String>> input) {
		for (Iterator<String> each : input) {
			if (!each.hasNext()) {
				continue;
			}
			itrQueue.addLast(each);
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

	public static void main(String[] args) {
		List<List<String>> inputList = List.of(List.of("a", "b", "c", "d"), List.of(), List.of("k", "r", "l"),
				List.of("t"));

		List<Iterator<String>> input = new ArrayList<>();
		for (List<String> each : inputList) {
			input.add(each.iterator());
		}

		Q3_StringArrayFlattenIterator flattenIterator = new Q3_StringArrayFlattenIterator(input);
		for (int i = 0; i < inputList.size(); i++) {
			System.out.println(flattenIterator.next());
		}
	}
}
