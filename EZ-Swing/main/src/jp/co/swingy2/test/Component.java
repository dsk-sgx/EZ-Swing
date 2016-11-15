package jp.co.swingy2.test;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;

import jp.co.ezswing.common.anotation.Font;
import jp.co.ezswing.common.anotation.Label;
import jp.co.ezswing.common.anotation.Layout;
import jp.co.ezswing.common.interfaces.IComponent;

public class Component implements IComponent {

	/** メニューバー */
	@Layout(posX = 0, posY = 0, width = ViewConst.FRAME_WIDTH, height = 20, autoResize = {false, true, false, false})
	public JMenuBar menuBar = new JMenuBar();

	/** カテゴリツリー */
	@Layout(posX = 5, posY = 25, width = 180, height = 450, scrollable = true, autoResize = {false, false, false, true})
	public JTree categoryTree = new JTree();

	/** カテゴリ1ラベル */
	@Label(name = "カテゴリⅠ")
	@Font(size = 9)
	@Layout(posX = 200, posY = 25, width = 45, height = 20)
	public JLabel category1Label = new JLabel();

	/** カテゴリ2ラベル */
	@Label(name = "カテゴリⅡ")
	@Font(size = 9)
	@Layout(posX = 440, posY = 25, width = 45, height = 20)
	public JLabel category2Label = new JLabel();

	/** タイトルラベル */
	@Label(name = "タイトル")
	@Layout(posX = 200, posY = 50, width = 60, height = 20)
	public JLabel titleLabel = new JLabel();

	/** タイトル */
	@Layout(posX = 250, posY = 50, width = 420, height = 20, autoResize = {false, true, false, false})
	public JTextField title = new JTextField();

	/** URLラベル */
	@Label(name = "URL")
	@Layout(posX = 200, posY = 75, width = 60, height = 20)
	public JLabel urlLabel = new JLabel();

	/** URL */
	@Layout(posX = 250, posY = 75, width = 420, height = 20, autoResize = {false, true, false, false})
	public JTextField url = new JTextField();

	/** 添付ファイルラベル */
	@Label(name = "ファイル")
	@Layout(posX = 200, posY = 100, width = 60, height = 20)
	public JLabel fileLabel = new JLabel();

	/** 本文 */
	@Layout(posX = 250, posY = 125, width = 420, height = 350, scrollable = true, autoResize = {false, true, false, true})
	public JTextArea text = new JTextArea();

}
