package jp.co.ezswing.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

import jp.co.ezswing.common.BaseCompGroup;
import jp.co.ezswing.common.anotation.Event;
import jp.co.ezswing.common.anotation.Label;
import jp.co.ezswing.common.anotation.Layout;
import jp.co.ezswing.common.enm.FontName;
import jp.co.ezswing.common.enm.FontStyle;
import jp.co.ezswing.common.enm.Listener;
import jp.co.ezswing.common.enm.LookAndFeel;
import jp.co.ezswing.common.interfaces.IComponent;
import jp.co.ezswing.component.JLink;
import jp.co.ezswing.event.ResizeEvent;
import jp.co.ezswing.event.SwingyActionEvent;
import jp.co.ezswing.event.SwingyMouseEvent;
import jp.co.ezswing.event.SwingyMouseEvent.MouseEventData;
import jp.co.ezswing.event.TextEvent;
import jp.co.ezswing.session.SwingySession;

@SuppressWarnings("serial")
public abstract class BaseFrame extends JFrame {

	private static FrameBasicInfo frameInfo;
	private static IComponent component;

	private static SwingyActionEvent actionEvent;
	private static SwingyMouseEvent mouseEvent;
	private static TextEvent textEvent;
	private static ResizeEvent resizeEvent;

	/** フレーム初期化 */
	abstract protected FrameBasicInfo initFrame();

	/** コンポーネント初期化 */
	abstract protected IComponent initComponent();

	/**
	 * メイン処理
	 */
	protected final void execute() {

		// フレーム情報を設定
		frameInfo = initFrame();
		if (frameInfo == null) {
			throw new IllegalArgumentException("フレーム情報が設定されていません。");
		}

		setTitle(frameInfo.title);
		setMinimumSize(new Dimension(frameInfo.width, frameInfo.height));
		setDefaultCloseOperation(frameInfo.closeOperation);
		setLookAndFeel(frameInfo.lookAndFeel);
		setResizable(frameInfo.resizable);
		setAlwaysOnTop(frameInfo.alwaysOnTop);
		setFont(frameInfo.font);
		setLayout(null);
		SwingySession.put("frame", this);

		// メイン処理
		component = initComponent();
		SwingySession.put("component", component);

		// イベント初期化
		actionEvent = new SwingyActionEvent();
		mouseEvent = new SwingyMouseEvent();
		textEvent = new TextEvent();
		resizeEvent = new ResizeEvent();
		addComponentListener(resizeEvent);
		setComponent(component);

		beforeRender();

		setVisible(true);
	}

	/**
	 * 部品をフレームに登録する
	 *
	 * @param component
	 */
	public void setComponent(IComponent component) {

		// TODO マッピングの整理
		// サポートしてるリスナーを全削除
		for (Component comp : getContentPane().getComponents()) {
			if (comp instanceof JButton) {
				((JButton)comp).removeActionListener(actionEvent);
			} else if (comp instanceof JComboBox) {
				((JComboBox)comp).removeActionListener(actionEvent);
			} else if (comp instanceof JTextComponent) {
				((JTextComponent)comp).removeKeyListener(textEvent);
			} else if (comp instanceof JTree) {
				((JTree)comp).removeMouseListener(mouseEvent);
			} else if (comp instanceof JLink) {
				for (MouseListener listener : comp.getMouseListeners()) {
					((JLink)comp).removeMouseListener(listener);
				}
			}
		}

		// 初期化
		getContentPane().removeAll();

		// フィールドを取得
		Field[] components = component.getClass().getFields();
		for (Field field : components) {

			// ---------------------------------------------
			// レイアウト
			// ---------------------------------------------
			Layout layout = field.getAnnotation(Layout.class);
			if (layout == null) {
				continue;
			}

			Component comp = getComponent(field);
			if (comp == null) {
				System.err.println("WORN コンポーネントフィールド以外にレイアウトアノテーションが設定されています。[" + field.getName() + "]");
				continue;
			}

			// グループのフィールドを取得
			Field targetField = getTargetField(field);

			// ---------------------------------------------
			// イベント
			// ---------------------------------------------
			// テキスト共通設定
			if (comp instanceof JTextComponent) {

				// タブサイズ指定
				if (comp instanceof JTextArea) {
					((JTextArea)comp).setTabSize(2);
				}

				// イベント
				comp.addKeyListener(textEvent);
				textEvent.addComponent(comp);
			}

			// 個別イベント
			Annotation[] annotations = targetField.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Event) {
					addEventListner(comp, (Event)annotation, targetField);
				}
			}

			// ---------------------------------------------
			// ラベル
			// ---------------------------------------------
			Label label = targetField.getAnnotation(Label.class);
			if (label != null) {
				setText(comp, label);
			}

			// ---------------------------------------------
			// フォント
			// ---------------------------------------------
			jp.co.ezswing.common.anotation.Font font = targetField.getAnnotation(jp.co.ezswing.common.anotation.Font.class);
			if (font == null) {
				comp.setFont(frameInfo.font);
			} else {
				String name = font.name() == FontName.NONE ? frameInfo.font.getName() : font.name().value;
				int style = font.style() == FontStyle.NONE ? frameInfo.font.getStyle() : font.style().value;
				int size = font.size();
				comp.setFont(new Font(name, style, size));
			}

			// ---------------------------------------------
			// スクロールバー
			// ---------------------------------------------
			if (layout.scrollable()) {
				JScrollPane scroll = new JScrollPane(comp);
				scroll.setBounds(layout.posX(), layout.posY(), layout.width(), layout.height());
				comp = scroll;
			}

			// ---------------------------------------------
			// 配置設定
			// ---------------------------------------------
			comp.setBounds(layout.posX(), layout.posY(), layout.width(), layout.height());

			getContentPane().add(comp);
		}
	}

	/**
	 * 再描画を行う
	 */
	public void repaint(IComponent component) {
		clearAction();
		setComponent(component);
		getContentPane().invalidate();
		getContentPane().validate();
		getContentPane().repaint();
	}

	/**
	 * フィールドがグループ対象の場合、ターゲットのフィールドを取得する.
	 *
	 * @param field
	 * @return
	 */
	private Field getTargetField(Field field) {
		try {
			Object obj = field.get(component);
			if (obj instanceof BaseCompGroup) {
				Field[] fields = obj.getClass().getFields();
				for (Field subField : fields) {
					Object target = ((BaseCompGroup)obj).getTarget();
					if (subField.get(obj).equals(target)) {
						return subField;
					}
				}
				return null;
			} else {
				return field;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * フィールドのコンポーネントを取得する
	 *
	 * @param field
	 * @return
	 */
	private Component getComponent(Field field) {
		try {
			Object obj = field.get(component);
			if (obj instanceof BaseCompGroup) {
				return ((BaseCompGroup)obj).getTarget();
			} else {
				return (Component)obj;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * テキストを設定する
	 *
	 * @param comp
	 * @param label
	 */
	private void setText(Object comp, Label label) {
		try {
			Method method = comp.getClass().getMethod("setText", String.class);
			method.invoke(comp, label.name());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, Object> actionClassMap = new HashMap<String, Object>();

	private void addEventListner(Object comp, Event event, Field field) {
		try {

			Listener listener = event.listener();
			Class<?> actionClass = event.event();
			Class<?> listenerClass = listener.value;

			Object actionInstance = actionClassMap.get(actionClass.getName());
			if (actionInstance == null) {
				actionInstance = actionClass.getConstructor().newInstance();
				actionClassMap.put(actionClass.getName(), actionInstance);
			}

			String methodName = "add" + listener.name();
			Method method = comp.getClass().getMethod(methodName, listenerClass);

			switch (listener) {
				case ActionListener:
					if (!actionEvent.isRegisted(field.getName())) {
						Method addActionCommand = comp.getClass().getMethod("setActionCommand", String.class);
						addActionCommand.invoke(comp, field.getName());
						method.invoke(comp, actionEvent);
						actionEvent.addActionMap(field.getName(), actionInstance);
					}
					break;

				case MouseListener:
					if (!mouseEvent.isRegisted(comp)) {
						method.invoke(comp, mouseEvent);
						mouseEvent.addMouseEvent(comp, new MouseEventData(field, actionInstance));
					}
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 共通アクションクラスを初期化する.
	 */
	private void clearAction() {
		actionClassMap.clear();
		actionEvent.clear();
		mouseEvent.clear();
	}

	/**
	 * ルックアンドフィールを設定する
	 *
	 * @param lookAndFeel
	 * @return
	 */
	protected boolean setLookAndFeel(LookAndFeel lookAndFeel) {
		try {
			UIManager.setLookAndFeel(lookAndFeel.value);
			SwingUtilities.updateComponentTreeUI(this);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * フレーム表示直前にコールされるメソッド. 独自実装が必要な場合にオーバーライドする
	 */
	protected void beforeRender() {
	}

	/**
	 * メインフレームのフォントを設定する
	 *
	 * @param component
	 */
	public void setFont(Component component) {
		component.setFont(getFont());
	}
}
