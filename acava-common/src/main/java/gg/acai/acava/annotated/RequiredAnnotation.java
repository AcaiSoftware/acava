package gg.acai.acava.annotated;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Clouke
 * @since 12.12.2022 14:47
 * © Acai Software - All Rights Reserved
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredAnnotation {
    Class<? extends Annotation> value();
}
