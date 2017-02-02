<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preference" type="com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel" -->
<label class="control-label"
       for="input-${preference.key}-${ns}"><@spring.message "portal.preferences.${preference.key}.key"/></label>
<div class="controls">
    <select <#if preference.isMultiple()>multiple</#if> id="input-${preference.key}-${ns}" name="${preference.key}">
    <#list preference.values?values?first as listValue>
        <option value="${listValue.value}"
                <#if (preference.getMultipleValues()?seq_contains(listValue.value))>selected</#if>>
        ${listValue.name}
        </option>
    </#list>
    </select>
</div>
<link rel="stylesheet" href="/common-portlets/vendor/select2-3.4.6/select2.css"/>
<script type="text/javascript" src="/common-portlets/vendor/select2-3.4.6/select2.min.js"></script>
<script type="text/javascript">
    (function ($) {
        $(function () {
            var select = $('#input-${preference.key}-${ns}');
            select.select2();

            if (select.prop('multiple')) {
                var container = select.closest('.controls');
                var name = select.attr('name');
                select.removeAttr('name');
                var val = select.val();
                if (val) {
                    $.each(val, function () {
                        var input = $('<input type="hidden">').attr('name', name).val(this);
                        container.append(input);
                    });
                }
                select.on("change", function (e) {
                    if (e.added) {
                        container.append($('<input type="hidden">').attr('name', name).val(e.added.id));
                    }
                    if (e.removed) {
                        container.find('input[value=' + e.removed.id + ']').remove();
                    }
                });
            }

        });
    })(jQuery)
</script>
<style>
    .select2-container {
        min-width: 300px;
    }
</style>