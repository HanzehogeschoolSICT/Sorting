package jlsa.sorting.model;

import java.util.ArrayList;

public class BubbleSort extends AbstractSort {

	private int noSwaps;
	private boolean needToSwap;
	
	public BubbleSort(ArrayList<Integer> data) {
		super(data);
		this.setName("bubble-sort");
		this.data = reset(data);
	}
	
	public ArrayList<Integer> reset(ArrayList<Integer> data) {
		noSwaps = 0;
		currentIndex = 0;
		this.data = data;
		needToSwap = false;
		
		return data;
	}
	
	@Override
	public boolean isDone() {
		return noSwaps == data.size() - 1;
	}
	
	@Override
	public ArrayList<Integer> step() {	
		if (!isDone()) {
			if (data.get(currentIndex) > data.get(currentIndex + 1)) {
				needToSwap = false;
				noSwaps = 0;
				data = swap(currentIndex, currentIndex + 1);
			} else {
				if (!needToSwap && currentIndex == 0) {
					needToSwap = true;
				}
				
				if (needToSwap) {
					noSwaps++;
				}
			}
			
			currentIndex++;
			if (currentIndex == data.size() - 1) {
				currentIndex = 0;
			}
		} else {
			currentIndex = 0;
		}
		
		return data;
	}
	
	@Override
	public ArrayList<Integer> getSorted() {
		while (!isDone()) {
			data = step();
		}
		
		return data;
	}
	
	@Override
	protected ArrayList<Integer> swap(int index, int nextIndex) {
		int temp = data.get(index);
		data.set(index, data.get(nextIndex));
		data.set(nextIndex, temp);
		
		return data;
	}

	@Override
	public int getSecondIndex() {
		return currentIndex + noSwaps;
	}
	
}
