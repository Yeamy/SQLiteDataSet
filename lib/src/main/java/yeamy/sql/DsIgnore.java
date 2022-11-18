package yeamy.sql;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Annotation this field is not a column and it will be skip while reading.
 * @see DsColumn
 */
@Target({ FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DsIgnore {
}
