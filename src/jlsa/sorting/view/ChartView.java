package jlsa.sorting.view;

import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jlsa.sorting.model.DataManager;

public class ChartView extends BaseView {
	
	private ArrayList<Integer> data;
	private DataManager sortingData;
	private ArrayList<Integer> highlightedBars;
	
	public ChartView(Observable obs) {
		super(obs);
		data = new ArrayList<>();
		highlightedBars = new ArrayList<>();
		update(obs, null);
	}
	
	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof DataManager) {
			sortingData = (DataManager)obs;
			this.data = sortingData.getData();
			render();
		}
	}
	
	@Override
	public void render() {
		clearChart();
		renderChart();
	}
	
	private void renderChart() {
		int height = heightProperty().intValue() - 1;
		int width = widthProperty().intValue() - 1;
		
		int xLeft = 2;
		for (int i = 0; i < data.size(); i++) {
			int xRight = width * (i + 1) / data.size() + 2;
			int barWidth = width / data.size() - 2;
			int barHeight = (int) Math.round(height * data.get(i) / getMax()) - 0;
			
			Text numberText = new Text(xLeft + barWidth/2, height - (barHeight + 5), data.get(i).toString());
			getChildren().add(numberText);
			if (highlightedBars.contains(i)) {
				drawOneBar(xLeft, height - barHeight + 10, barWidth, barHeight - 10, true);
			} else {
				drawOneBar(xLeft, height - barHeight + 10, barWidth, barHeight - 10);
			}
			xLeft = xRight;
		}
	}
	
	private void clearChart() {
		getChildren().removeAll(getChildren());	// clear the chartview from all object
	}
	
	private int getMax() {
		int max = 0;
		for (Integer wrapper : data) {
			if (max < wrapper) {
				max = wrapper;
			}
		}
		return max;
	}
	
	private void drawOneBar(int x, int y, int w, int h) {
		drawOneBar(x, y, w, h, false);
	}
	
	private void drawOneBar(int x, int y, int w, int h, boolean currentIndex) {
		Rectangle bar = new Rectangle(x, y, w, h);
		if (!currentIndex)
			bar.setFill(Color.DIMGRAY);
		else
			bar.setFill(Color.DARKSALMON);
		
		bar.setStroke(Color.BLACK);
		bar.getStyleClass().add("bar-default");
		getChildren().add(bar);
	}
	
	public void clearHighlights() {
		highlightedBars.clear();
	}
	
	public void highlightBar(int index) {
		clearHighlights();
		highlightedBars.add(index);
	}
	
	public void highlightBars(ArrayList<Integer> highlightedBars) {
		clearHighlights();
		this.highlightedBars = highlightedBars;
	}
	
}
