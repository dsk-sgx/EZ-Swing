package jp.co.ezswing.event;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import jp.co.ezswing.session.SwingySession;
import jp.co.swingy2.base.BasePage2;
import jp.co.swingy2.base.PartsManager;

/**
 * TODO フレームのリサイズ対応
 * 
 * @author t_sugiyamads
 */
public class ResizeEvent implements ComponentListener {

	private BasePage2 page;

	public ResizeEvent(BasePage2 page) {
		this.page = page;
	}

	public ResizeEvent() {
	}

	boolean display;
	int orgHeight;
	int orgWidth;

	@Override
	public void componentResized(ComponentEvent event) {

		page.display();
	}

	@Override
	public void componentMoved(ComponentEvent event) {
	}

	@Override
	public void componentShown(ComponentEvent event) {
	}

	@Override
	public void componentHidden(ComponentEvent event) {
	}

}
