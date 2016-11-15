package jp.co.ezswing.common.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Layout {

	/** 名前 */
	String name() default "";

	/** 幅 */
	int width();

	/** 高さ */
	int height();

	/** 横座標 */
	int posX();

	/** 縦座標 */
	int posY();

	/** スクロール */
	boolean scrollable() default false;

	/**
	 * 自動リサイズ<br>
	 * [0] ⇒ 横の座標変動<br>
	 * [1] ⇒ 横のリサイズ<br>
	 * [2] ⇒ 縦の座標変動<br>
	 * [3] ⇒ 縦のリサイズ
	 */
	boolean[] autoResize() default {false, false, false, false};
}
