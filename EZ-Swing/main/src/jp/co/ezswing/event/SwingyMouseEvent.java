package jp.co.ezswing.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * マウスイベントの共通実装クラス
 * 
 * @author t_sugiyamads
 */
public class SwingyMouseEvent implements MouseListener {

	private Map<Object, MouseEventData> mouseEventMap = new HashMap<Object, MouseEventData>();

	/**
	 * @param component
	 * @param mouseEvent
	 */
	public void addMouseEvent(Object component, MouseEventData mouseEvent) {
		mouseEventMap.put(component, mouseEvent);
	}

	/**
	 * @param component
	 * @return
	 */
	public boolean isRegisted(Object component) {
		return mouseEventMap.get(component) != null;
	}

	/**
	 *
	 */
	public void clear() {
		mouseEventMap.clear();
	}

	/** イベントモード */
	private static enum EventMode {
		Clicked, Pressed, Released, Entered, Exited
	}

	/**
	 * @param mode
	 * @param event
	 */
	private void execute(EventMode mode, MouseEvent event) {
		try {
			MouseEventData eventData = mouseEventMap.get(event.getComponent());
			Method method = eventData.instance.getClass().getMethod(eventData.field.getName() + mode.name(), MouseEvent.class);
			method.invoke(eventData.instance, event);
		} catch (NoSuchMethodException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		execute(EventMode.Clicked, event);
	}

	@Override
	public void mousePressed(MouseEvent event) {
		execute(EventMode.Pressed, event);
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		execute(EventMode.Released, event);
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		execute(EventMode.Entered, event);
	}

	@Override
	public void mouseExited(MouseEvent event) {
		execute(EventMode.Exited, event);
	}

	public static class MouseEventData {

		public Field field;

		public Object instance;

		public MouseEventData(Field field, Object instance) {
			this.field = field;
			this.instance = instance;
		}
	}
}
