package jp.co.ezswing.common;

import java.awt.Component;

/**
 * 部品のグルーピングクラス. 同一座標で複数部品を出しわける場合に使用する
 * 
 * @author t_sugiyamads
 */
public class BaseCompGroup {

	private Component target;

	/**
	 * 表示対象部品を設定する
	 * 
	 * @param comp
	 */
	public void setTarget(Component comp) {
		this.target = comp;
	}

	/**
	 * 表示対象部品を取得する
	 * 
	 * @return
	 */
	public Component getTarget() {
		return target;
	}
}
