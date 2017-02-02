<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preference" type="com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel" -->
<#-- @ftlvariable name="value" type="com.netcracker.laboratory.portlets.utils.preferences.renderers.custom.ColumnLinkRenderer.ColumnLink" -->
<label class="control-label"><@spring.message "portal.preferences.${preference.key}.key"/></label>

<#--<#assign reportColumns=preference.values['reportColumns']>-->

<div class="controls column-link">
    <div class="line etalon">
        <label>Column name: <input type="text" placeholder="ColumnA" data-key="columnName"/></label>
        <label>Link URL: <input type="text" placeholder="page1?objectId=" data-key="linkUrl"/></label>
        <label>Link Parameter:
            <input type="text" placeholder="SALES_ORDER_ID" data-key="linkParam"/>
            <#--<select data-key="linkParam">-->
            <#--<#list reportColumns as column>-->
                <#--<option value="${column.value}">${column.name}</option>-->
            <#--</#list>-->
            <#--</select>-->
        </label>
        <label>Link Title:
            <input type="text" placeholder="SALES_ORDER_NAME" data-key="linkTitle"/>
            <#--<select data-key="linkTitle">-->
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
        <label>Column name: <input type="text" placeholder="Column Name" data-key="columnName"
                                   value="${value.columnName!''}"/></label>
        <label>Link URL: <input type="text" placeholder="URL" data-key="linkUrl" value="${value.linkUrl!''}"/></label>
        <label>Link Parameter:
            <input type="text" placeholder="Link Parameter" data-key="linkParam" value="${value.linkParam!''}"/>
            <#--<select data-key="linkParam">-->
                <#--<#list reportColumns as column>-->
                    <#--<option <#if column.value == value.linkParam!''>selected="selected"</#if>-->
                            <#--value="${column.value}">${column.name}</option>-->
                <#--</#list>-->
            <#--</select>-->
        </label>
        <label>Link Title:
            <input type="text" placeholder="Link Title" data-key="linkTitle" value="${value.linkTitle!''}"/>
            <#--<select data-key="linkTitle">-->
                <#--<#list reportColumns as column>-->
                    <#--<option <#if column.value == value.linkTitle!''>selected="selected"</#if>-->
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
            var container = $('.controls.column-link');

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
    .column-link .etalon {
        display: none;
    }

    .column-link .button-holder {
        margin-bottom: 12px;
    }

    .line.common label {
        display: inline-block;
    }
  </style>