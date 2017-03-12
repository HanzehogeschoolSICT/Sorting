package jlsa.sorting.util;

import java.util.ArrayList;

/*
 * Based on Listing 19.1 GenericStack.java 
 * from the Liang java book.
 */
public class GenericStack<E> {

	private ArrayList<E> list = new ArrayList<>();
	
	public int getSize() {
		return list.size();
	}
	
	public E peek() {
		return list.get(getSize() - 1);
	}
	
	public void push(E o) {
		list.add(o);
	}
	
	public E pop() {
		E o = list.get(getSize() - 1);
		list.remove(getSize() - 1);
		return o;
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	@Override
	public String toString() {
		return "Stack: " + list.toString();
	}
	
	public void clear() {
		list.clear();
	}
}
