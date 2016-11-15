package jp.co.swingy2.base;

import java.awt.Component;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import jp.co.ezswing.common.anotation.Layout;
import jp.co.ezswing.common.interfaces.IComponent;
import jp.co.ezswing.view.FrameBasicInfo;

public class PartsManager {

	public static List<Parts> partsList = new ArrayList<Parts>();

	public static void parseComponent(IComponent component, int width, int height) {
		Field[] components = component.getClass().getFields();

		try {
			for (Field field : components) {

				Layout layout = field.getAnnotation(Layout.class);
				if (layout == null) {
					continue;
				}

				Parts parts = new Parts();
				Component comp = (Component)field.get(component);

				// グループのフィールドを取得
				parts.component = comp;
				parts.layout = layout;
				parts.setRatioSize(width, height);
				PartsManager.partsList.add(parts);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
