<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preference" type="com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel" -->
<#-- @ftlvariable name="pages" type="java.util.List<com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValue>" -->
<#-- @ftlvariable name="offers" type="java.util.List<com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValue>" -->
<#-- @ftlvariable name="value" type="com.netcracker.laboratory.portlets.utils.preferences.renderers.custom.ActionPagesRenderer.ActionPage" -->
<label class="control-label"><@spring.message "portal.preferences.${preference.key}.key"/></label>

<#assign offers=preference.values['items']>
<#assign pages=preference.values['pages']>

<div class="controls action-buttons">
    <div class="line etalon">
        <label>Offer:
            <select data-key="offerId">
            <#list offers as offer>
                <option value="${offer.value}">${offer.name}</option>
            </#list>
            </select>
        </label>
        <label>Page:
            <select data-key="pageId">
            <#list pages as page>
                <option value="${page.value}">${page.name}</option>
            </#list>
            </select>
        </label>
        <button class="btn btn-danger btn-mini" type="button">Remove</button>
        <input type="hidden" value=''/>
    </div>
    <div class="button-holder">
        <button class="btn btn-success" type="button">Add</button>
    </div>
<#list preference.value as value>
    <div class="line common">
        <label>Offer:
            <select data-key="offerId">
                <option></option>
                <#list offers as offer>
                    <option <#if offer.value == value.offerId!''>selected="selected"</#if>
                            value="${offer.value}">${offer.name}</option>
                </#list>
            </select>
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
        <input name="${preference.key}" value='${value.json!''}' type="hidden"/>
        <button class="btn btn-danger btn-mini" type="button">Remove</button>
    </div>
</#list>

</div>
<link rel="stylesheet" href="/catalog-display-portlets/vendor/select2-3.4.6/select2.css"/>
<script type="text/javascript" src="/catalog-display-portlets/vendor/select2-3.4.6/select2.min.js"></script>
<script>
    (function ($) {
        $(function () {
            var container = $('.controls.action-buttons');

            function addListeners(line) {
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
                line.find('select').each(function () {
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

    .line.common label .select2-container {
        min-width: 270px;
    }

    .line.common label {
        display: inline-block;
    }
</style>