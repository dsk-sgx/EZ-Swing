package jp.co.ezswing.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * アクションイベントの共通実装クラス
 * 
 * @author t_sugiyamads
 */
public class SwingyActionEvent implements ActionListener {

	private Map<String, Object> actionMap = new HashMap<String, Object>();

	public void addActionMap(String command, Object actionInstance) {
		actionMap.put(command, actionInstance);
	}

	public boolean isRegisted(String command) {
		return actionMap.get(command) != null;
	}

	public void clear() {
		actionMap.clear();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			String command = event.getActionCommand();
			Object action = actionMap.get(command);
			Method method = action.getClass().getMethod(command + "Action", ActionEvent.class);
			method.invoke(action, event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}