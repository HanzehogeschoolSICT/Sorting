package jlsa.sorting.view;

import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import jlsa.sorting.controller.App;
import jlsa.sorting.model.DataManager;
import jlsa.sorting.util.RandomDataGenerator;
import javafx.scene.layout.BorderPane;
import jlsa.sorting.model.AbstractSort;
import jlsa.sorting.model.AutoSortThread;
import jlsa.scene.control.NumberTextField;

public class ApplicationView extends BaseView {	
	private AbstractSort sort;
	private ChartView chart;
	private DataManager dataManager;
	private AutoSortThread autoSortThread;
	private App app;
	
	public ApplicationView(Observable obs, App app) {
		super(obs);
		if (obs instanceof DataManager) {
			this.dataManager = (DataManager)obs;
		}
		this.app = app;
		init();
	}
	
	public void init() {
		sort = app.getSorter();
		chart = new ChartView(dataManager);
		render(dataManager.getData());
		autoSortThread = new AutoSortThread(sort, app);
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
	
	private Button addPlayButton() {
		Button btn = new Button("Play");
		btn.setPrefSize(100, 20);
		
		btn.setOnMouseClicked(e -> {
			if (autoSortThread == null) {
				autoSortThread = new AutoSortThread(sort, app);
				autoSortThread.start();
			} else {
				if (!autoSortThread.isAlive()) {
					autoSortThread.start();
				}
				autoSortThread.setRunning(true);
			}
		});
		
		return btn;
	}
	
	private Button addStepButton() {
		Button btn = new Button("Step");
		btn.setPrefSize(100, 20);
		
		btn.setOnMouseClicked(e -> {
			render(sort.step());
			if (autoSortThread != null) {
				autoSortThread.setRunning(false);
			}
		});
		
		return btn;
	}
	
	private Button addPauseButton() {
		Button btn = new Button("Pause");
		btn.setPrefSize(100, 20);
		btn.setOnMouseClicked(e -> {
			if (autoSortThread != null) {
				autoSortThread.setRunning(false);
			}
		});
		
		return btn;
	}
	
	private Button addResetButton() {
		Button btn = new Button("Reset");
		btn.setPrefSize(100, 20);
		btn.setOnMouseClicked(e -> {
			ArrayList<Integer> newData = RandomDataGenerator.randomData(app.getDataSize());
			render(newData);
			sort.reset(newData);
			if (autoSortThread != null) {
				autoSortThread.setRunning(false);
				autoSortThread.interrupt();
				autoSortThread = null;
			}
		});
		
		return btn;
	}
	
	private Button addInstantButton() {
		Button btn = new Button("Instant");
		btn.setPrefSize(100, 20);
		btn.setOnMouseClicked(e -> {
			ArrayList<Integer> newData = sort.getSorted();
			dataManager.setData(newData);
		});
		
		return btn;
	}
	
	public HBox addHBox() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		
		hbox.getChildren().addAll(
				addStepButton(), 
				addPlayButton(), 
				addPauseButton(), 
				addResetButton(), 
				addInstantButton());
		
		return hbox;
	}
	
	public HBox addTopHBox() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.getChildren().add(new Text(app.getSorter().getName()));
		
		return hbox;
	}
	
	public void render(ArrayList<Integer> data) {
		ArrayList<Integer> highlights = new ArrayList<>();
		highlights.add(sort.getCurrentIndex());
		highlights.add(sort.getSecondIndex());
		
		chart.highlightBars(highlights);
		dataManager.setData(data);
	}
}
