package jp.co.ezswing.common;

import javax.swing.JComponent;

import jp.co.ezswing.common.anotation.Event;
import jp.co.ezswing.common.anotation.Layout;

public class Parts<T extends JComponent> {

	public Layout layout;

	public Event event;

	public JComponent baseComp;

	public Parts() {
	}

}
