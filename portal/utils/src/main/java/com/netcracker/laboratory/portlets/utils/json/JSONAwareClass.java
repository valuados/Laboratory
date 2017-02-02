package com.netcracker.laboratory.portlets.utils.json;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface JSONAwareClass {
    Class<?> value();
}
