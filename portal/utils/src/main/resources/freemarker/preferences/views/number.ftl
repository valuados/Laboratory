<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preference" type="com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel" -->
<label class="control-label"
       for="input-${preference.key}-${ns}"><@spring.message "portal.preferences.${preference.key}.key"/></label>

<div class="controls">
    <input type="text" name="${preference.key}" value="${preference.value}" id="input-${preference.key}-${ns}">
    <span id="errmsg"></span>
</div>

<script type="text/javascript" src="/catalog-display-portlets/vendor/jquery.maskedinput.js"></script>
<script type="text/javascript">
    (function ($) {
        $(function () {
            var inputNumber = $('#input-${preference.key}-${ns}');

            inputNumber.attr('placeholder', 'number').keypress(function (e) {
                //if the letter is not digit then display error and don't type anything
                if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                    //display error message
                    $('#errmsg').html("Digits Only").show().fadeOut("slow");
                    return false;
                }
            });

        });
    })(jQuery)
</script>