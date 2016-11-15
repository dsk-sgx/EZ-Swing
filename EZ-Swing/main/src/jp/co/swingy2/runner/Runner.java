package jp.co.swingy2.runner;

import javax.swing.SwingUtilities;

import jp.co.swingy2.base.BaseController;

/**
 * Swingyを起動するクラス.
 */
public class Runner {

	private static String controllerName = null;

	public static void main(String[] args) {

		// コントローラ指定を判定
		if (args == null || args.length != 1) {
			throw new RuntimeException("引数が不正です。");
		}
		controllerName = args[0];

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				try {
					@SuppressWarnings("unchecked")
					Class<? extends BaseController> clazz = (Class<? extends BaseController>)Class.forName(controllerName);
					clazz.newInstance().execute();
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("BaseControllerの実行に失敗しました。(" + e.getMessage() + ")。");
				} catch (IllegalAccessException e) {
					throw new RuntimeException("BaseControllerの実行に失敗しました。(" + e.getMessage() + ")。");
				} catch (InstantiationException e) {
					throw new RuntimeException("BaseControllerの実行に失敗しました。(" + e.getMessage() + ")。");
				}
			}
		});
	}
}
