package jp.co.ezswing.test;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class FontTest {

	public static void main(String[] args) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		for (Font font : ge.getAllFonts()) {
			System.out.println(font.getName());
		}
	}
}
