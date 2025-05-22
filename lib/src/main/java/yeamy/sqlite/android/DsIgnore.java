package yeamy.sqlite.android;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation this field is not a column and it will be skip while reading.
 * @see DsColumn
 */
@Target({ FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DsIgnore {
}
