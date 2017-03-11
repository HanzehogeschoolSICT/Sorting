package jlsa.sorting.view;

import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.text.Text;
import javafx.geometry.Point2D;
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
			
			Point2D dimensions = new Point2D(barWidth, barHeight - 10);
			Point2D position = new Point2D(xLeft, height - barHeight + 10);
			Text numberText = new Text(xLeft + barWidth/2, height - (barHeight + 5), data.get(i).toString());
			getChildren().add(numberText);
			if (highlightedBars.contains(i)) {
				drawOneBar(position, dimensions, true);
			} else {
				drawOneBar(position, dimensions);
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
	
	private void drawOneBar(Point2D position, Point2D dimensions) {
		drawOneBar(position, dimensions, false);
	}
	
	private void drawOneBar(Point2D position, Point2D dimensions, boolean currentIndex) {
		Rectangle bar = new Rectangle(position.getX(), position.getY(), dimensions.getX(), dimensions.getY());
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
