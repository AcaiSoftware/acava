package gg.acai.acava.annotated;

import java.lang.annotation.*;

/**
 * Marked elements are required to be present.
 *
 * @author Clouke
 * @since 05.12.2022 20:42
 * © Acava - All Rights Reserved
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PACKAGE, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
}
