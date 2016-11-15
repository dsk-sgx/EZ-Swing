package jp.co.ezswing.data;

import java.util.ArrayList;
import java.util.List;

public class MenuData {

	/** メニューアイテム */
	private List<MenuItemData> items;

	/** 設定対象アイテム */
	private MenuItemData current;

	/**
	 * デフォルトコンストラクタ
	 */
	public MenuData() {
		this.items = new ArrayList<MenuItemData>();
	}

	/**
	 * 最上位メニューを追加する
	 * 
	 * @param name
	 * @return
	 */
	public MenuData addParent(String name) {
		MenuItemData data = new MenuItemData(name);
		items.add(data);
		current = data;
		return this;
	}

	/**
	 * 最上位メニューを追加する
	 * 
	 * @param name
	 * @return
	 */
	public MenuData addParent(String name, String method) {
		MenuItemData data = new MenuItemData(name, method);
		items.add(data);
		current = data;
		return this;
	}

	/**
	 * メニューを追加する
	 * 
	 * @param name
	 *        表示名
	 * @return
	 */
	public MenuData add(String name) {
		MenuItemData data = new MenuItemData(name);
		data.parent = current;
		current.child.add(data);
		current = data;
		return this;
	}

	/**
	 * メニューを追加する
	 * 
	 * @param name
	 *        表示名
	 * @param method
	 *        メソッド名
	 * @return
	 */
	public MenuData add(String name, String method) {
		MenuItemData data = new MenuItemData(name, method);
		data.name = name;
		data.method = method;
		current.child.add(data);
		return this;
	}

	/**
	 * カーソルを上位へ移動する
	 * 
	 * @return
	 */
	public MenuData upper() {
		current = current.parent;
		return this;
	}

	/**
	 * メニュー一件の保持クラス
	 */
	public class MenuItemData {

		public String name;

		public String method;

		public MenuItemData parent;

		public List<MenuItemData> child = new ArrayList<MenuItemData>();

		public MenuItemData(String name) {
			this.name = name;
		}

		public MenuItemData(String name, String method) {
			this.name = name;
			this.method = method;
		}
	}

	/**
	 * メニューアイテムを取得する
	 * 
	 * @return
	 */
	public List<MenuItemData> getItemList() {
		return items;
	}
}
