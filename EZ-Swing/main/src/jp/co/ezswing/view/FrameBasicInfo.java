package jp.co.ezswing.view;

import java.awt.Font;

import javax.swing.JFrame;

import jp.co.ezswing.common.enm.LookAndFeel;

/**
 * Swingyフレームの基本情報を保持するクラス.
 * 
 * @author t_sugiyamads
 */
public class FrameBasicInfo {

	/** タイトル */
	public String title;

	/** 幅 */
	public int width;

	/** 高さ */
	public int height;

	/** クローズオペレーション */
	public int closeOperation = JFrame.EXIT_ON_CLOSE;

	/** ルックアンドフィール */
	public LookAndFeel lookAndFeel = LookAndFeel.Windows;

	/** リサイズ */
	public boolean resizable = true;

	/** 最上面表示 */
	public boolean alwaysOnTop = true;

	/** フォント */
	public Font font = new JFrame().getFont();
}
