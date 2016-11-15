package jp.co.swingy2.base;

public abstract class BaseController {

	/**
	 * 
	 */
	public void execute() {

		// page単位のセッション保持

		//

	}

	/**
	 * ページの登録処理を実装する
	 */
	public abstract void init();

	protected void addPage(BasePage page) {
	}
}
