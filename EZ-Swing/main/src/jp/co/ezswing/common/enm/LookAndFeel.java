package jp.co.ezswing.common.enm;

public enum LookAndFeel {

	Metal("javax.swing.plaf.metal.MetalLookAndFeel"), CDE_Motif("com.sun.java.swing.plaf.motif.MotifLookAndFeel"), Windows("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"), WindowsClassic("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

	public String value;

	private LookAndFeel(String value) {
		this.value = value;
	}
}
