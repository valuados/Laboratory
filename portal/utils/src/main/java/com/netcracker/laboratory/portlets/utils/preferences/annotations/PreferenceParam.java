package com.netcracker.laboratory.portlets.utils.preferences.annotations;

import com.liferay.portal.kernel.util.StringPool;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 */
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface PreferenceParam {
    String value() default StringPool.BLANK;
}