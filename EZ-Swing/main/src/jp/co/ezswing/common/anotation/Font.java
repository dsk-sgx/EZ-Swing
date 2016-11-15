package jp.co.ezswing.common.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jp.co.ezswing.common.enm.FontName;
import jp.co.ezswing.common.enm.FontStyle;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Font {

	/** フォント名 */
	public FontName name() default FontName.NONE;

	/** スタイル */
	public FontStyle style() default FontStyle.NONE;

	/** サイズ */
	public int size();

}
