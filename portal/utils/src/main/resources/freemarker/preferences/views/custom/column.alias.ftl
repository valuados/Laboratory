<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preference" type="com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel" -->
<#-- @ftlvariable name="value" type="com.netcracker.laboratory.portlets.utils.preferences.renderers.custom.ColumnAliasRenderer.ColumnAlias" -->
<label class="control-label"><@spring.message "portal.preferences.${preference.key}.key"/></label>

<div class="controls column-aliases">
    <div class="line etalon">
        <input type="text" placeholder="Column Name" data-key="columnName"/>
        <input type="text" placeholder="Alias" data-key="columnAlias"/>
        <button class="btn btn-danger btn-mini" type="button">Remove</button>
        <input type="hidden"/>
    </div>
    <div class="button-holder">
        <button class="btn btn-success" type="button">Add</button>
    </div>
<#list preference.value as value>
    <div class="line common">
        <input type="text" placeholder="Column Name" data-key="columnName" value="${value.columnName!''}"/>
        <input type="text" placeholder="Alias" data-key="columnAlias" value="${value.columnAlias!''}"/>
        <input type="hidden" name="${preference.key}" value=""/>
        <button class="btn btn-danger btn-mini" type="button">Remove</button>
    </div>
</#list>

</div>
<script>
    (function ($) {
        $(function () {
            var container = $('.controls.column-aliases');

            function addListeners(line) {
                line.find('input[type=text]').change(function () {
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
                line.find('input[type=text]').each(function () {
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
    .column-aliases .etalon {
        display: none;
    }

    .column-aliases .button-holder {
        margin-bottom: 12px;
    }

    .line.common label {
        display: inline-block;
    }
</style>