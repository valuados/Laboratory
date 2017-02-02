<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preference" type="com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel" -->
<#-- @ftlvariable name="pages" type="java.util.List<com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValue>" -->
<#-- @ftlvariable name="value" type="com.netcracker.laboratory.portlets.utils.preferences.renderers.custom.UrlParametersRenderer.UrlParametersItem" -->
<label class="control-label"><@spring.message "portal.preferences.${preference.key}.key"/></label>

<#assign pages=preference.values['pages']>

<div class="controls urlParameters-buttons">
    <div class="line etalon">
        <label>Parameter:
            <input type="text" placeholder="URL Parameter" data-key="parameter"/>
        </label>
        <label>Page:
            <select data-key="pageId">
            <#list pages as page>
                <option value="${page.value}">${page.name}</option>
            </#list>
            </select>
        </label>
        <label>
            <input type="checkbox" data-key="isActive" value="true"/>
            Is Active
        </label>
        <label>
            <input type="checkbox" data-key="isHidden" value="true"/>
            Is Hidden
        </label>
        <button class="btn btn-danger btn-mini" type="button">Remove</button>
        <input type="hidden"/>
    </div>
    <div class="button">
        <button class="btn btn-success" type="button">Add</button>
    </div>
<#list preference.value as value>
    <div class="line common">
        <label>Parameter:
            <input type="text" placeholder="URL Parameter" data-key="parameter" value="${value.parameter!''}"/>
        </label>
        <label>Page:
            <select data-key="pageId">
                <option></option>
                <#list pages as page>
                    <option <#if page.value == value.pageId!''>selected="selected"</#if>
                            value="${page.value}">${page.name}</option>
                </#list>
            </select>
        </label>
        <label>
            <input type="checkbox" data-key="isActive" value="true" <#if value.active>checked="checked"</#if>/>
            Is Active
        </label>
        <label>
            <input type="checkbox" data-key="isHidden" value="true"  <#if value.hidden>checked="checked"</#if>/>
            Is Hidden
        </label>
        <button class="btn btn-danger btn-mini" type="button">Remove</button>
        <input name="${preference.key}" value="${value.json?html!''}" type="hidden"/>
    </div>
</#list>

</div>
<script>
    (function ($) {
        $(function () {
            var container = $('.controls.urlParameters-buttons');

            function addListeners(line) {
                line.find('input[type=text],input[type=checkbox]').change(function () {
                    serialize(line);
                });
                line.find('select').change(function () {
                    serialize(line);
                }).select2();
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
                line.find('input[type=text],input[type=checkbox],select').each(function () {
                    var input = $(this);
                    data[input.attr('data-key')] = input.is(':checkbox') ? input.is(':checked') : input.val();
                });
                line.find('input[type=hidden]').val(JSON.stringify(data));
            }
        });
    })(jQuery);
</script>
<style>
    .urlParameters-buttons {
        margin-left: 150px!important;
    }

    .urlParameters-buttons .etalon {
        display: none;
    }

    .urlParameters-buttons .button {
        margin-bottom: 12px;
    }

    .urlParameters-buttons .line label {
        display: inline-block;
        font-size: 11px;
        margin: auto 10px;
        vertical-align: top;
    }

    input[type="text"] {
        color: black!important;
        height: 28px!important;
        width: 180px!important;
    }
</style>