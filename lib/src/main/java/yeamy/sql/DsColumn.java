package yeamy.sql;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Annotation the column name of the field.
 * Normally, you don't need to annotation this
 * unless the column name is not the same with the field.
 * @see DsIgnore
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DsColumn {
    /**
     * @return name of column
     */
    String value();
}