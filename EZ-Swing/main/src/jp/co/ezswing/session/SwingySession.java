package jp.co.ezswing.session;

import java.util.HashMap;
import java.util.Map;

import jp.co.ezswing.common.interfaces.IComponent;
import jp.co.ezswing.view.BaseFrame;

public class SwingySession {

	private static final Map<Object, Object> map = new HashMap<Object, Object>();

	private SwingySession() {
	}

	public static void put(String key, Object value) {
		map.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Object> T get(String key) {
		return (T)map.get(key);
	}

	public static void remove(String key) {
		map.remove(key);
	}

	/**
	 * セッションからフレーム情報を取得する.
	 * 
	 * @return
	 */
	public static <T extends BaseFrame> T getFrame() {
		return get("frame");
	}

	/**
	 * セッションからコンポーネント情報を取得する
	 * 
	 * @return
	 */
	public static <T extends IComponent> T getComponent() {
		return get("component");
	}

}
