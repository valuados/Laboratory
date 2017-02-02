<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="portletPreferencesJson" type="java.lang.String" -->
<script type="text/javascript">
    (function ($) {
        window['portlet_preferences${ns}'] = ${portletPreferencesJson};
    })(jQuery);
</script>