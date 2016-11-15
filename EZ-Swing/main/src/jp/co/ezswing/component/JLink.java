package jp.co.ezswing.component;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

/**
 * リンクを表示するためのクラス.
 * 
 * @author t_sugiyamads
 */
public class JLink extends JLabel {

	private static final long serialVersionUID = 7600914776273763271L;

	private static String orgText;

	public JLink() {
		super();
		init();
	}

	public JLink(String text) {
		super(text);
		setText(text, true);
		init();
	}

	@Override
	public void setText(String text) {
		setText(text, true);
	}

	/**
	 * 表示文字列を設定する
	 * 
	 * @param text
	 *        表示文字列
	 * @param overwrite
	 *        もとの文字列を上書きする場合はtrue
	 */
	public void setText(String text, boolean overwrite) {
		if (overwrite) {
			orgText = text;
		}
		super.setText(text);
	}

	private void init() {
		setForeground(Color.BLUE);
		addMouseListener();
	}

	/**
	 * 共通のイベントを定義する
	 */
	private void addMouseListener() {
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setText(orgText, false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setText("<html><u>" + orgText + "</u></html>", false);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

}
