package jp.co.ezswing.common.enm;

public enum FontStyle {
	NONE(0), PLAIN(java.awt.Font.PLAIN), BOLD(java.awt.Font.BOLD), ITALIC(java.awt.Font.ITALIC), BOLD_ITALIC(BOLD.value | ITALIC.value);

	public int value;

	private FontStyle(int value) {
		this.value = value;
	}
}
