<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preference" type="com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel" -->
<#-- @ftlvariable name="value" type="com.netcracker.laboratory.portlets.utils.preferences.renderers.custom.HiddenColumnsRenderer.HiddenColumns" -->
<label class="control-label"><@spring.message "portal.preferences.${preference.key}.key"/></label>

<#--<#assign reportColumns=preference.values['reportColumns']>-->

<div class="controls hidden-column">
    <div class="line etalon">
        <label>Column Name:
            <input type="text" placeholder="Column Name" data-key="columnName"/>
            <#--<select data-key="columnName">-->
            <#--<#list reportColumns as column>-->
                <#--<option value="${column.value}">${column.name}</option>-->
            <#--</#list>-->
            <#--</select>-->
        </label>
        <button class="btn btn-danger btn-mini" type="button">Remove</button>
        <input type="hidden"/>
    </div>
    <div class="button-holder">
        <button class="btn btn-success" type="button">Add</button>
    </div>
<#list preference.value as value>
    <div class="line common">
        <label>Column Name:
            <input type="text" placeholder="Column Name" data-key="columnName" value="${value.columnName!''}"/>
            <#--<select data-key="columnName">-->
                <#--<#list reportColumns as column>-->
                    <#--<option <#if column.value == value.columnName!''>selected="selected"</#if>-->
                            <#--value="${column.value}">${column.name}</option>-->
                <#--</#list>-->
            <#--</select>-->
        </label>
        <input type="hidden" name="${preference.key}" value=""/>
        <button class="btn btn-danger btn-mini" type="button">Remove</button>
    </div>
</#list>

</div>
<script>
    (function ($) {
        $(function () {
            var container = $('.controls.hidden-column');

            function addListeners(line) {
                line.find('input[type=text],select').change(function () {
                    serialize(line);
                });
                line.find('.btn-danger').click(function () {
                    $(this).closest('.line').remove();
                });
            }

            container.find('.line.common').each(function () {
                serialize($(this));
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
                line.find('input[type=text],select').each(function () {
                    var input = $(this);
                    var inputVal = input.val();
                    data[input.attr('data-key')] = inputVal;
                });
                line.find('input[type=hidden]').val(JSON.stringify(data));
            }
        });
    })(jQuery);
</script>
<style>
    .hidden-column .etalon {
        display: none;
    }

    .hidden-column .button-holder {
        margin-bottom: 12px;
    }

    .line.common label {
        display: inline-block;
    }
</style>