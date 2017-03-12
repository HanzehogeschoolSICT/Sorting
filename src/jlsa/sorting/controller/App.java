package jlsa.sorting.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import jlsa.sorting.model.QuickSort;
import jlsa.sorting.model.BubbleSort;
import jlsa.sorting.model.DataManager;
import javafx.application.Application;
import jlsa.sorting.model.AbstractSort;
import jlsa.sorting.model.InsertionSort;
import jlsa.sorting.view.ApplicationView;
import jlsa.sorting.model.RandomDataGenerator;

public class App extends Application {
	
	private int dataSize = 20;
	
	private ApplicationView appView;
	private AbstractSort sort;
	private DataManager dataManager;
	
	@Override
	public void start(Stage stage) throws Exception {
		dataManager = new DataManager();
		dataManager.setData(RandomDataGenerator.randomData(dataSize));
//		sort = new BubbleSort(dataManager.getData());
//		sort = new InsertionSort(dataManager.getData());
		sort = new QuickSort(dataManager.getData());
		appView = new ApplicationView(dataManager, this);
		stage.setTitle("Sorting Assignment");
		stage.setScene(setupScene());
		stage.show();
	}
	
	public AbstractSort getSorter() {
		return sort;
	}
	
	public Scene setupScene() {
		Scene scene = new Scene(appView.makeUI(), 1024, 768);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		return scene;
	}

	public void render(ArrayList<Integer> data) {
		appView.render(data);
	}
	
	public int getDataSize() {
		return dataSize;
	}
}
