package yeamy.sqlite.android;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation the column name of the field.
 * Normally, you don't need to annotation this
 * unless the column name is not the same with the field.
 * @see DsIgnore
 */
@Target({ FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DsColumn {
	String value();
}