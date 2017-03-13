package jlsa.sorting.model;

import java.util.ArrayList;
import jlsa.sorting.util.GenericStack;
/*
 * Iterative Quicksort code based on code from geeksforgeeks.org
 * http://www.geeksforgeeks.org/iterative-quick-sort/
 *
 */
public class QuickSort extends AbstractSort {
	
	private int left;
	private int right;
	private int pivotIndex;
	private GenericStack<Integer> stack2;

	public QuickSort(ArrayList<Integer> data) {
		super(data);
		this.setName("Quick Sort");
		stack2 = new GenericStack<Integer>();
		reset(data);
	}

	public ArrayList<Integer> reset(ArrayList<Integer> data) {
		this.data = data;
		currentIndex = 0;
		left = 0;
		right = data.size() - 1;
		stack2.clear();

		// push initial values in the stack
		stack2.push(left);
		stack2.push(right);
		return data;
	}

	@Override
	public boolean isDone() {
		return stack2.getSize() == 0;
	}

	int partition(ArrayList<Integer> data, int left, int right) {
		pivotIndex = right;
		int pivot = data.get(right);
		int i = (left - 1);

		for (int j = left; j <= right - 1; j++) {
			if (data.get(j) <= pivot) {
				i++;

				swap(i, j);
			}
		}

		swap(i + 1, right);
		return i + 1;
	}

	@Override
	public ArrayList<Integer> step() {
		if (!isDone()) {
			// pop right and left
			right = stack2.pop();
			left = stack2.pop();

			// set pivot element at it's proper position
			int p = partition(data, left, right);

			// if there are elements on the left side of pivot,
			// then push left side to stack
			if (p - 1 > left) {
				stack2.push(left);
				stack2.push(p-1);
			}

			// if there are elements on the right side of pivot,
			// then push right side to stack
			if (p + 1 < right) {
				stack2.push(p+1);
				stack2.push(right);
			}
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
	public int getCurrentIndex() {
		return left;
	}

	@Override
	public int getSecondIndex() {
		return right;
	}

}
