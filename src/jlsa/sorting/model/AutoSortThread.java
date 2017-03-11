package jlsa.sorting.model;

import java.util.ArrayList;
import jlsa.sorting.controller.App;
import javafx.application.Platform;

public class AutoSortThread extends Thread {
	private volatile boolean running = true;
	private int delay = 100;
	private AbstractSort sort;
	private App app;
	
	public AutoSortThread(AbstractSort sort, App app) {
		this.setDaemon(true);
		this.sort = sort;
		this.app = app;
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
				app.render(newData);
			});
			
			try {
				Thread.sleep(getDelay());
			} catch (InterruptedException e) {
				// I know it is bad behaviour to not handle thrown exceptions
			}
		}
	}
	
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public void setRunning(boolean toRun) {
		running = toRun;
	}
	
	public boolean getRunning() {
		return running;
	}
}