<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preference" type="com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel" -->
<label class="control-label"
       for="input-${preference.key}-${ns}"><@spring.message "portal.preferences.${preference.key}.key"/></label>

<div class="controls">
    <input type="text" class="text-renderer-input" name="${preference.key}" value="${preference.value}" id="input-${preference.key}-${ns}">
</div>
<script>
    (function ($) {
        $(function () {
            var input = $('#input-${preference.key}-${ns}');
            colorize(input);
            input.on('change', function() {
                colorize($(this));
            });

            function colorize(input) {
                var value = input.val();
                if(/^#/.test(value) && value.length == 7) {
                    input.css('background-color', value);
                } else {
                    input.css('background-color', 'transparent');
                }
            }
        });
    })(jQuery)
</script>
<style>
    .text-renderer-input {
        width: 250px!important;
        height: 31px!important;
    }
</style>