package jlsa.sorting.view;

import java.util.Observer;
import java.util.Observable;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import jlsa.sorting.model.DataManager;

public abstract class BaseView extends Pane implements Observer, RenderElement  {
	
	protected DataManager sortingData;
	
	public BaseView(Observable obs) {
		obs.addObserver(this);
		this.getStyleClass().add("pane");
		this.setPadding(new Insets(10, 10, 10, 10));
		this.widthProperty().addListener(observable -> render());
		this.heightProperty().addListener(observable -> render());
	}
	
	@Override
	public void render() {}

	@Override
	public void update(Observable o, Object arg) {}

}
