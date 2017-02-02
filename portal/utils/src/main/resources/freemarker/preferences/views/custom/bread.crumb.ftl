<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preference" type="com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel" -->
<#-- @ftlvariable name="value" type="com.netcracker.laboratory.portlets.utils.preferences.renderers.custom.BreadcrumbsRenderer.BreadCrumbItem" -->
<label class="control-label"><@spring.message "portal.preferences.${preference.key}.key"/></label>

<div class="controls breadcrumbs-buttons">
    <div class="line etalon">
        <input type="text" placeholder="Name" data-key="name"/>
        <input type="text" placeholder="URL" data-key="url"/>
        <label>
            <input type="checkbox" data-key="isActive" value="true"/>
            Is Active
        </label>
        <button class="btn btn-danger btn-mini" type="button">Remove</button>
        <input type="hidden"/>
    </div>
    <div class="button">
        <button class="btn btn-success" type="button">Add</button>
    </div>
<#list preference.value as value>
    <div class="line common">
        <input type="text" placeholder="Name" data-key="name" value="${value.name!''}"/>
        <input type="text" placeholder="URL" data-key="url" value="${value.url!''}"/>
        <label>
            <input type="checkbox" data-key="isActive" value="true" <#if value.active>checked="checked"</#if>/>
            Is Active
        </label>
        <input name="${preference.key}" value="${value.json?html!''}" type="hidden"/>
        <button class="btn btn-danger btn-mini" type="button">Remove</button>
    </div>
</#list>

</div>
<script>
    (function ($) {
        $(function () {
            var container = $('.controls.breadcrumbs-buttons');

            function addListeners(line) {
                line.find('input[type=text],input[type=checkbox]').change(function () {
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
                line.find('input[type=text],input[type=checkbox]').each(function () {
                    var input = $(this);

                    data[input.attr('data-key')] = input.is(':checkbox') ? input.is(':checked') : input.val();
                });
                line.find('input[type=hidden]').val(JSON.stringify(data));
            }
        });
    })(jQuery);
</script>
<style>
    .breadcrumbs-buttons .etalon {
        display: none;
    }

    .breadcrumbs-buttons .button {
        margin-bottom: 12px;
    }

    .breadcrumbs-buttons .line label {
        display: inline-block;
        font-size: 11px;
        margin: auto 10px;
        vertical-align: top;
    }
</style>