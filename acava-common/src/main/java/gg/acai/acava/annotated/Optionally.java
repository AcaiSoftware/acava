package gg.acai.acava.annotated;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Clouke
 * @since 24.02.2023 17:58
 * © Acava - All Rights Reserved
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Optionally {
    String value() default "";
}
