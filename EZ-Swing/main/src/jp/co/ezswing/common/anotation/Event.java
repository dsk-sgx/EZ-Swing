package jp.co.ezswing.common.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jp.co.ezswing.common.enm.Listener;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Event {

	/** リスナー */
	Listener listener();

	/** アクションクラス */
	Class<?> event();

}
