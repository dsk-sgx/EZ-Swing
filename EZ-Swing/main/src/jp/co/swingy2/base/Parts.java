package jp.co.swingy2.base;

import java.awt.Component;

import jp.co.ezswing.common.anotation.Event;
import jp.co.ezswing.common.anotation.Font;
import jp.co.ezswing.common.anotation.Label;
import jp.co.ezswing.common.anotation.Layout;
import jp.co.ezswing.view.FrameBasicInfo;

public class Parts {

	public Component component;

	public Layout layout;

	public Font font;

	public Label label;

	public Event event;

	public float ratioPosX;
	public float ratioPosY;
	public float ratioWidth;
	public float ratioHeight;

	public void setRatioSize(int width, int height) {
		if (layout == null) {
			return;
		}
		ratioPosX = ((float)layout.posX()) / width;
		ratioPosY = ((float)layout.posY()) / height;
		ratioWidth = ((float)layout.width()) / width;
		ratioHeight = ((float)layout.height()) / height;
	}

	public void setBounds(int frameWidth, int frameHeight) {
		int posX = layout.autoResize()[0] ? (int)(frameWidth * ratioPosX) : layout.posX();
		int width = layout.autoResize()[1] ? (int)(frameWidth * ratioWidth) : layout.width();
		int posY = layout.autoResize()[2] ? (int)(frameHeight * ratioPosY) : layout.posY();
		int height = layout.autoResize()[3] ? (int)(frameHeight * ratioHeight) : layout.height();
		component.setBounds(posX, posY, width, height);
	}

}
