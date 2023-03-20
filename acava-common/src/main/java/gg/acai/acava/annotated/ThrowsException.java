package gg.acai.acava.annotated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Clouke
 * @since 24.02.2023 04:41
 * © Acava - All Rights Reserved
 */
@Use("Use this annotation to mark methods that throw an exception")
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface ThrowsException {
    Class<? extends Throwable>[] value();
}
