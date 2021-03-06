package jlsa.scene.control;

import javafx.scene.control.TextField;

/* 
 * Based on the answer given to a SO question
 * http://stackoverflow.com/a/18959399
 */
public class NumberTextField extends TextField {
	
	public NumberTextField(String text) {
		super(text);
	}
	
	@Override
	public void replaceText(int start, int end, String text) {
		if (validate(text)) {
			super.replaceText(start, end, text);
		}
	}
	
	@Override
	public void replaceSelection(String text) {
		if (validate(text)) {
			super.replaceSelection(text);
		}
	}
	
	private boolean validate(String text) {
		return text.matches("[0-9]*");
	}
}
