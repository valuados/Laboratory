<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preference" type="com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel" -->
<#-- @ftlvariable name="value" type="com.netcracker.laboratory.portlets.utils.preferences.renderers.custom.ActionButtonRenderer.ActionButton" -->
<label class="control-label"><@spring.message "portal.preferences.${preference.key}.key"/></label>

<div class="controls action-buttons">
    <div class="line etalon">
        <input type="text" placeholder="Action Id" data-key="actionId"/>
        <input type="text" placeholder="CSS" data-key="css"/>
        <input type="text" placeholder="Icon" data-key="icon"/>
        <input type="text" placeholder="Name Key" data-key="nameKey"/>
        <button class="btn btn-danger btn-mini" type="button">Remove</button>
        <input type="hidden"/>
    </div>
    <div class="button-holder">
        <button class="btn btn-success" type="button">Add</button>
    </div>
<#list preference.value as value>
    <div class="line common">
        <input type="text" placeholder="Action Id" data-key="actionId" value="${value.actionId!''}"/>
        <input type="text" placeholder="CSS" data-key="css" value="${value.css!''}"/>
        <input type="text" placeholder="Icon" data-key="icon" value="${value.icon!''}"/>
        <input type="text" placeholder="Name Key" data-key="nameKey" value="${value.nameKey!''}"/>
        <input name="${preference.key}" value="${value.json!''}" type="hidden"/>
        <button class="btn btn-danger btn-mini" type="button">Remove</button>
    </div>
</#list>

</div>
<script>
    (function ($) {
        $(function () {
            var container = $('.controls.action-buttons');

            function addListeners(line) {
                line.find('input[type=text]').change(function () {
                    serialize(line);
                });
                line.find('.btn-danger').click(function () {
                    $(this).closest('.line').remove();
                });
            }

            container.find('.line.common').each(function () {
                addListeners($(this));
            });

            container.find('.btn-success').click(function () {
                var newLine = container.find('.etalon.line').clone();
                newLine.show()
                        .toggleClass('etalon common')
                        .find('input[type=hidden]')
                        .attr('name', '${preference.key}');
                container.append(newLine);
                addListeners(newLine);
            });

            function serialize(line) {
                var data = {};
                line.find('input[type=text]').each(function () {
                    var input = $(this);
                    data[input.attr('data-key')] = input.val();
                });
                line.find('input[type=hidden]').val(JSON.stringify(data));
            }
        });
    })(jQuery);
</script>
<style>
    .action-buttons .etalon {
        display: none;
    }

    .action-buttons .button-holder {
        margin-bottom: 12px;
    }

    .line.common label {
        display: inline-block;
    }
</style>