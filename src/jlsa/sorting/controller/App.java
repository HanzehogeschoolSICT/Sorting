package jlsa.sorting.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.application.Platform;
import jlsa.sorting.view.ChartView;
import javafx.scene.control.Button;
import jlsa.sorting.model.BubbleSort;
import jlsa.sorting.model.DataManager;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import jlsa.scene.control.NumberTextField;
import jlsa.sorting.model.RandomDataGenerator;

public class App extends Application {
	
	private int dataSize = 20;
	
	private BubbleSort sort;
	private ChartView chart;
	private DataManager dataManager;
	private AutoSortThread autoSortThread;
	
	@Override
	public void start(Stage stage) throws Exception {
		init();
		stage.setTitle("Sorting Assignment");
		stage.setScene(setupScene());
		stage.show();
	}
	
	public void init() {
		dataManager = new DataManager();
		dataManager.setData(RandomDataGenerator.randomData(dataSize));
		sort = new BubbleSort(dataManager.getData());
		chart = new ChartView(dataManager);
		render(dataManager.getData());
	}
	
	public Scene setupScene() {
		Scene scene = new Scene(makeUI(), 1024, 768);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		return scene;
	}
	
	public BorderPane makeUI() {
		BorderPane bp = new BorderPane();
		HBox hbox = addHBox();
		HBox topBox = addTopHBox();
		
		Pane left = new Pane();
		left.setPrefSize(10.0, 10.0);
		Pane right = new Pane();
		right.setPrefSize(10.0, 10.0);
		
		bp.setTop(topBox);
		addInputField(hbox);
		bp.setCenter(chart);
		bp.setBottom(hbox);
		bp.setLeft(left);
		bp.setRight(right);	
		
		return bp;
	}
	
	public void addInputField(HBox hbox) {
		Label lbl = new Label("Delay (in ms)");
		NumberTextField ntfDelay = new NumberTextField("100");
		ntfDelay.textProperty().addListener((obs, oldValue, newValue) -> {
			int newDelay = 100;
			if (obs.getValue().length() > 0 && Integer.parseInt(obs.getValue()) > 100) {
				newDelay = Integer.parseInt(obs.getValue());
			} else {
				newDelay = 100;
			}
			if (autoSortThread != null) {
				autoSortThread.setDelay(newDelay);
			}
		});
		hbox.getChildren().addAll(lbl, ntfDelay);
	}
	
	public HBox addHBox() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		
		Button btnStep = new Button("Step");
		btnStep.setPrefSize(100, 20);
		
		btnStep.setOnMouseClicked(e -> {
			render(sort.step());
			if (autoSortThread != null) {
				autoSortThread.running = false;
			}
		});
		
		Button btnPlay = new Button("Play");
		btnPlay.setPrefSize(100, 20);
		
		btnPlay.setOnMouseClicked(e -> {
			if (autoSortThread == null) {
				autoSortThread = new AutoSortThread();
				autoSortThread.start();
			} else {
				autoSortThread.running = true;
			}
		});
		
		Button btnPause = new Button("Pause");
		btnPause.setPrefSize(100, 20);
		btnPause.setOnMouseClicked(e -> {
			if (autoSortThread != null) {
				autoSortThread.running = false;
			}
		});
		
		Button btnReset = new Button("Reset");
		btnReset.setPrefSize(100, 20);
		btnReset.setOnMouseClicked(e -> {
			ArrayList<Integer> newData = RandomDataGenerator.randomData(dataSize);
			render(newData);
			sort = new BubbleSort(newData);
			if (autoSortThread != null) {
				autoSortThread.running = false;
				autoSortThread.interrupt();
				autoSortThread = null;
			}
		});
		
		Button btnInstant = new Button("Instant");
		btnInstant.setPrefSize(100, 20);
		btnInstant.setOnMouseClicked(e -> {
			ArrayList<Integer> newData = sort.getSorted();
			dataManager.setData(newData);
		});
		
		hbox.getChildren().addAll(btnStep, btnPlay, btnPause, btnReset, btnInstant);
		
		return hbox;
	}
	
	public HBox addTopHBox() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.getChildren().add(new Text("Bubble Sort Algorithm"));
		
		return hbox;
	}
	
	private void render(ArrayList<Integer> data) {
		ArrayList<Integer> highlights = new ArrayList<>();
		highlights.add(sort.getCurrentIndex());
		highlights.add(sort.getCurrentIndex() + 1);
		
		chart.highlightBars(highlights);
		dataManager.setData(data);
	}
	
	private class AutoSortThread extends Thread {
		private volatile boolean running = true;
		private int delay = 100;
		
		public AutoSortThread() {
			this.setDaemon(true);
		}
		
		@Override
		public void run() {
			while (!sort.isDone()) {
				while(!running) {
					yield();
				}
				ArrayList<Integer> newData = sort.step();
				
				// update the data manager
				Platform.runLater(() -> {
					render(newData);
				});
				
				try {
					Thread.sleep(getDelay());
				} catch (InterruptedException e) {
					// I know it is bad behaviour to not handle thrown exceptions
				}
			}
 		}
		
		private int getDelay() {
			return delay;
		}
		private void setDelay(int delay) {
			this.delay = delay;
		}
	}
}
