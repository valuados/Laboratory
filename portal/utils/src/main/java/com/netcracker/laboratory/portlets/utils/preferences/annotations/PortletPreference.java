package com.netcracker.laboratory.portlets.utils.preferences.annotations;

import com.liferay.portal.kernel.util.StringPool;
import com.netcracker.laboratory.portlets.utils.preferences.PreferenceRenderer;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.TextRenderer;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValuesProvider;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.StaticListValuesProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
public @interface PortletPreference {

    String value();

    Class<? extends PreferenceRenderer> renderer() default TextRenderer.class;

    String[] defaultValue() default StringPool.BLANK;

    boolean multiple() default false;

    ListValue[] listValues() default {};

    Class<? extends ListValuesProvider>[] listValuesProviders() default StaticListValuesProvider.class;

    @Target(TYPE)
    @Retention(RUNTIME)
    @interface List {
        PortletPreference[] value();
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    @interface ListValue {
        String name();

        String value();
    }
}
