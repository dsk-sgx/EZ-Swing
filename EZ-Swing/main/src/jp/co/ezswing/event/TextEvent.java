package jp.co.ezswing.event;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.text.JTextComponent;

public class TextEvent implements KeyListener {

	/** テキスト情報 */
	private static Map<Integer, TextManager> map = new HashMap<Integer, TextEvent.TextManager>();

	@Override
	public void keyPressed(KeyEvent event) {
		JTextComponent component = (JTextComponent)event.getComponent();
		TextManager manager = map.get(event.getComponent().hashCode());

		// Ctrl + Z
		if (isUndo(event)) {
			manager.undo();
			component.setText(manager.getText());

			// Ctrl + Y
		} else if (isRedo(event)) {
			manager.redo();
			component.setText(manager.getText());
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		JTextComponent component = (JTextComponent)event.getComponent();
		TextManager manager = map.get(event.getComponent().hashCode());

		// 更新があった場合記録
		manager.save(component.getText());
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	public void addComponent(Component component) {
		map.put(component.hashCode(), new TextManager());
	}

	private boolean isUndo(KeyEvent e) {
		return e.isControlDown() && KeyEvent.VK_Z == e.getKeyCode();
	}

	private boolean isRedo(KeyEvent e) {
		return e.isControlDown() && KeyEvent.VK_Y == e.getKeyCode();
	}

	/**
	 * 入力内容を保持するマネージャクラス
	 */
	private static class TextManager {
		private Vector<String> stack = new Vector<String>();
		private int index = 0;

		public TextManager() {
			stack.add("");
		}

		private String getText() {
			return stack.get(index);
		}

		private void undo() {
			if (index != 0) {
				index--;
			}
		}

		private void redo() {
			if (index != stack.size() - 1) {
				index++;
			}
		}

		private void save(String text) {
			if (!text.equals(stack.get(index))) {
				index++;
				stack.add(index, text);
				stack.setSize(index + 1);
			}
		}
	}

}
