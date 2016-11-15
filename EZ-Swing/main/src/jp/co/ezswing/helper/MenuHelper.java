package jp.co.ezswing.helper;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import jp.co.ezswing.data.MenuData;
import jp.co.ezswing.data.MenuData.MenuItemData;
import jp.co.ezswing.session.SwingySession;

public class MenuHelper {

	/** アクションクラスのインスタンス */
	private Object actionInstance;

	/**
	 * コンストラクタ. アクションクラスのインスタンス
	 * 
	 * @param actionInstance
	 */
	public MenuHelper(Object actionInstance) {
		this.actionInstance = actionInstance;
	}

	/**
	 * メニューを生成する.
	 * 
	 * @param title
	 *        メニュータイトル
	 * @param data
	 *        メニューデータ
	 * @return
	 */
	public JMenu createMenu(String title, MenuData data) {
		JMenu menu = new JMenu(title);
		for (MenuItemData item : data.getItemList()) {
			menu.add(getMenu(item));
		}
		return menu;
	}

	/**
	 * ポップアップメニューを生成する.
	 * 
	 * @param data
	 *        メニューデータ
	 * @return
	 */
	public JPopupMenu createPopupMenu(MenuData data) {
		JPopupMenu popup = new JPopupMenu();
		for (final MenuItemData item : data.getItemList()) {
			popup.add(getMenu(item));
		}
		return popup;
	}

	private Component getMenu(final MenuItemData item) {

		// 最下位要素の場合
		if (item.child.isEmpty()) {
			JMenuItem menuItem = new JMenuItem(item.name);
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Method method = actionInstance.getClass().getMethod(item.method);
						method.invoke(actionInstance);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

			// フレーム共通のフォントを設定
			if (SwingySession.getFrame() != null) {
				menuItem.setFont(SwingySession.getFrame().getFont());
			}
			return menuItem;

			// 子要素が設定されている場合
		} else {
			JMenu menu = new JMenu(item.name);
			for (MenuItemData child : item.child) {
				Component menuItem = getMenu(child);
				menu.add(menuItem);
			}
			// フレーム共通のフォントを設定
			if (SwingySession.getFrame() != null) {
				menu.setFont(SwingySession.getFrame().getFont());
			}
			return menu;
		}

	}

}
