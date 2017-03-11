package jlsa.sorting.model;

import java.util.ArrayList;

public class AbstractSort {
	
	protected int currentIndex;
	protected ArrayList<Integer> data;
	
	public AbstractSort(ArrayList<Integer> data) {
		this.data = data;
	}
	
	public ArrayList<Integer> reset(ArrayList<Integer> data) {
		return data;
	}
	
	public boolean isDone() {
		return false;
	}
	
	public ArrayList<Integer> step() {
		return data;
	}
	
	public ArrayList<Integer> getSorted() {
		return data;
	}
		
	protected ArrayList<Integer> swap(int index, int nextIndex) {
		return data;
	}
	
	protected ArrayList<Integer> swap() {
		return data;
	}
	
	public int getCurrentIndex() {
		return currentIndex;
	}
}
