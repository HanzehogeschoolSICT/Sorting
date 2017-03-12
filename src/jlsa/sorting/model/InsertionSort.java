package jlsa.sorting.model;

import java.util.ArrayList;

public class InsertionSort extends AbstractSort {

	private int currentElement = 0;
	
	public InsertionSort(ArrayList<Integer> data) {
		super(data);
		this.setName("Insertion Sort");
		this.data = reset(data);
	}

	@Override
	public ArrayList<Integer> reset(ArrayList<Integer> data) {
		currentIndex = 0;
		currentElement = 0;
		this.data = data;
		return data;
	}

	@Override
	public boolean isDone() {
		return currentIndex == data.size();
	}

	@Override
	public ArrayList<Integer> step() {
		if (!isDone()) {
			currentElement = data.get(currentIndex);
			int lastSortedIndex;
			for (lastSortedIndex = currentIndex - 1; lastSortedIndex >= 0
					&& data.get(lastSortedIndex) > currentElement; lastSortedIndex--) {
				data.set(lastSortedIndex + 1, data.get(lastSortedIndex));
			}
			
			data.set(lastSortedIndex + 1, currentElement);
			currentIndex++;
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
	public int getSecondIndex() {
		return currentIndex - 1;//secondIndex;
	}
}
