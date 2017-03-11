package jlsa.sorting.model;

import java.util.ArrayList;
import java.util.Observable;

public class DataManager extends Observable {
	
	private ArrayList<Integer> data;
	
	public DataManager() { }
	
	public void dataChanged() {
		setChanged();
		notifyObservers();
	}
	
	public void setData(ArrayList<Integer> data) {
		this.data = data;
		dataChanged();
	}
	
	public ArrayList<Integer> getData() {
		return data;
	}	
}
