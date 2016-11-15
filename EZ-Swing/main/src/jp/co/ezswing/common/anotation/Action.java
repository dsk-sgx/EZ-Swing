package jp.co.ezswing.common.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * アクションクラスに指定するアノテーション
 * 
 * @author t_sugiyamads
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Action {

	public boolean bool() default true;
}
