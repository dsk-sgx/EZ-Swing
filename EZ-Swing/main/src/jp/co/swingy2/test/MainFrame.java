package jp.co.swingy2.test;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingUtilities;

import jp.co.ezswing.common.enm.FontName;
import jp.co.ezswing.common.enm.FontStyle;
import jp.co.ezswing.common.interfaces.IComponent;
import jp.co.ezswing.view.FrameBasicInfo;
import jp.co.swingy2.base.BasePage2;

@SuppressWarnings("serial")
public class MainFrame extends BasePage2 {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().execute();
			}
		});
	}

	@Override
	protected FrameBasicInfo initFrame() {
		FrameBasicInfo frameInfo = new FrameBasicInfo();
		frameInfo.title = ViewConst.TITLE;
		frameInfo.width = ViewConst.FRAME_WIDTH;
		frameInfo.height = ViewConst.FRAME_HEIGHT;
		frameInfo.resizable = true;
		frameInfo.font = new Font(FontName.ＭＳ_ゴシック.name(), FontStyle.PLAIN.value, 10);
		return frameInfo;
	}

	@Override
	protected IComponent initComponent() {
		Component component = new Component();

		// MenuData menuData = new MenuData().addParent("新規作成").add("カテゴリⅠ",
		// "createCategory")
		// .add("カテゴリⅡ", "createCategory2")
		// .add("ファイル", "createFile").upper()
		// .addParent("インポート", "import")
		// .addParent("エクスポート", "export");
		// JMenu menu = new MenuHelper(new TreeAction()).createMenu("ファイル",
		// menuData);
		// component.menuBar.add(menu);
		//
		// Logic logic = Logic.getInstance();
		// component.categoryTree = new JTree(logic.createTreeModel());
		// component.categoryTree.setRootVisible(false);
		// component.category1.select = new
		// JComboBox(logic.createFirstCategoryModel());
		//
		// int defaultParent =
		// ((CategoryData)component.category1.select.getItemAt(0)).id;
		// component.category2.select = new
		// JComboBox(logic.createSecondCategoryModel(defaultParent));
		//
		// component.file.setTarget(component.file.fileSelect);

		return component;
	}
}
