package jp.co.ezswing.common.enm;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.EventListener;

public enum Listener {

	ActionListener(ActionListener.class), MouseListener(MouseListener.class);

	public Class<? extends EventListener> value;

	private Listener(Class<? extends EventListener> value) {
		this.value = value;
	}
}
