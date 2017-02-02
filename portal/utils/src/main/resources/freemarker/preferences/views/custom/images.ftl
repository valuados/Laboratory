<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preference" type="com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel" -->
<#-- @ftlvariable name="folders" type="java.util.List<com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValue>" -->
<#-- @ftlvariable name="offers" type="java.util.List<com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValue>" -->
<#-- @ftlvariable name="value" type="com.netcracker.laboratory.portlets.utils.preferences.renderers.custom.ImagesRenderer.ImageItem" -->
<label class="control-label"><@spring.message "portal.preferences.${preference.key}.key"/></label>
<#assign noImgSrc = "data:image/png;base64,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">
<#assign offers=preference.values['items']>
<#assign folders=preference.values['folders']>

<div class="controls image-selector-config" id="images-selector-config-${preference.key}-${ns}">
    <div class="line etalon">
        <label>
            <input data-key="imageUrl" class="hidden-img-url" value="">
            <img class="item-image" src="${noImgSrc}"/>
        </label>
        <label>
            <select data-key="itemId">
            <#list offers as offer>
                <option value="${offer.value}">${offer.name}</option>
            </#list>
            </select>
        </label>
        <button class="btn btn-danger" type="button">Remove</button>
        <input type="hidden" value=''/>
    </div>
    <div class="button-holder">
        <button class="btn btn-success btn-add" type="button">Add</button>
    </div>
<#list preference.value as value>
    <div class="line common">
        <label>
            <input data-key="imageUrl" class="hidden-img-url" value="${value.imageUrl!''}">
            <img class="item-image" src="${value.imageUrl!noImgSrc}"/>
        </label>
        <label>
            <select data-key="itemId">
                <option></option>
                <#list offers as offer>
                    <option <#if offer.value == value.itemId!''>selected="selected"</#if>
                            value="${offer.value}">${offer.name}</option>
                </#list>
            </select>
        </label>
        <input name="${preference.key}" value='${value.json!''}' type="hidden"/>
        <button class="btn btn-danger" type="button">Remove</button>
    </div>
</#list>
    <div class="modal fade">
        <div class="modal-content">
            <div class="modal-header">Select Image</div>
            <div class="modal-body">
                <div class="folder-selector-block">
                    <i class="icon icon-folder-open"></i>
                    <select id="folder-selector">
                    <#list folders as folder>
                        <option value="${folder.value}">${folder.name}</option>
                    </#list>
                    </select>
                </div>
                <div class="images-list-block">

                </div>
            </div>
            <div class="modal-footer">
                <div class="bottom-block">
                    <button type="button" class="btn btn-dismiss-modal btn-default"><i class="icon icon-remove"></i> Cancel</button>
                    <button type="button" class="save-image btn btn-success"><i class="icon icon-ok"></i> Save</button>
                </div>
            </div>
        </div>
    </div>
</div>
<link rel="stylesheet" href="/catalog-display-portlets/vendor/select2-3.4.6/select2.css"/>
<script type="text/javascript" src="/catalog-display-portlets/vendor/select2-3.4.6/select2.min.js"></script>
<script>
    (function ($) {
        $(function () {
            $(document).ready(function () {
                var container = $('#images-selector-config-${preference.key}-${ns}');
                var modal = container.find('.modal');
                container.find('.save-image').on('click', function () {
                    var url = container.find('.modal .images-container .image-container.selected img').attr('src');
                    var itemId = $(this).closest('.modal').attr('data-item-id');
                    if (itemId) {
                        container.find('.line select.select2-offscreen').each(function () {
                            if ($(this).val() == itemId) {
                                var line = $(this).closest('.line');
                                var input = line.find('input.hidden-img-url');
                                line.find('img.item-image').attr('src', url);
                                input.val(url);
                                input.change();
                                return;
                            }
                        });
                    }
                    modal.removeClass('in').hide();
                });

                container.find('.btn-dismiss-modal').on('click', function() {
                    $(this).closest('.modal').hide();
                });

                var folderSelector = container.find('#folder-selector');
                folderSelector.on('change', function () {
                    loadImagesByFolderId($(this).val());
                });

                function addListeners(line) {
                    line.find('select').change(function () {
                        serialize(line);
                    }).select2();
                    line.find('input').change(function () {
                        serialize(line);
                    });

                    line.find('.btn-danger').click(function () {
                        $(this).closest('.line').remove();
                    });
                    line.find('.item-image').on('click', function () {
                        var currentItem = $(this).closest('.line').find('select').val();
                        modal.attr('data-item-id', currentItem);
                        modal.addClass('in').show();
                        if (folderSelector.val()) {
                            folderSelector.change();
                        }
                    });
                }

                container.find('.line.common').each(function () {
                    addListeners($(this));
                });

                container.find('.btn-success.btn-add').click(function () {
                    var newLine = container.find('.etalon.line').clone();
                    newLine.show()
                            .toggleClass('etalon common')
                            .find('input[type=hidden]')
                            .attr('name', '${preference.key}');
                    exludeAlreadySelected(newLine);
                    container.append(newLine);
                    addListeners(newLine);
                });

                function exludeAlreadySelected(newLine) {
                    var values = [];
                    container.find('.line select.select2-offscreen').each(function () {
                        values.push($(this).val());
                    });
                    var newLineSelector = newLine.find('select');
                    newLine.find('select option').each(function () {
                        var value = $(this).attr('value');
                        if ($.inArray(value, values) >= 0) {
                            newLineSelector.find('option[value="' + value + '"]').remove();
                        }
                    })
                }

                function serialize(line) {
                    var data = {};
                    line.find('select[data-key="itemId"], input[data-key="imageUrl"]').each(function () {
                        var input = $(this);
                        data[input.attr('data-key')] = input.val();
                    });
                    line.find('input[type=hidden]').val(JSON.stringify(data));
                }

                function loadImagesByFolderId(folderId) {
                    if (folderId) {
                        NC.showLoader();
                        $.ajax({
                            url: "<@portlet.resourceURL id="get.images.by.folder"/>",
                            type: 'POST',
                            data: {folderId: folderId},
                            success: function (data) {
                                container.find('.images-list-block').html(data);
                                init(container);
                                NC.hideLoader();
                            }
                        })
                    }
                }

                function init(container) {
                    var images = container.find('.image-container');
                    images.on('click', function () {
                        images.removeClass('selected');
                        $(this).addClass('selected');
                    })
                }
            });
        });
    })(jQuery);
</script>
<style>
    .image-selector-config .line.etalon {
        display: none !important;
    }

    .image-selector-config .button-holder {
        margin-bottom: 12px;
    }

    .line {
        display: inline-block !important;
        width: 100%;
        border-bottom: 1px dashed #eee;
        margin-bottom: 15px;
    }

    .line .btn.btn-danger {
        float: right;
        margin-right: 14px;
    }
     .line.common label:first-child {
         width:60px;
     }
    .line.common label .select2-container {
        min-width: 270px;
    }

    .line.common label {
        display: inline-block;
        margin: 10px 15px;
    }

    .image-selector-config .modal-header {
        display: inline-block;
        width: 100%;

        padding: 20px 25px;
        font-size: 25px;
        height: auto !important;
        background-color: #F5F5F5;
    }
     .image-selector-config .modal .icon-folder-open {
         font-size: 23px;
         position: relative;
         top: 5px;
     }
    .image-selector-config .modal-content {
        width: 100%;
        margin: 0px auto;
    }
    .image-selector-config .modal-body {
        max-height: 460px!important;
    }
    .image-selector-config .no-images {
        text-align: center;
        color: #ccc;
        padding: 49px 0px;
        font-size: 15px;
    }

    .image-selector-config .modal {
        width: 70% !important;
        margin: 0 auto !important;
        margin-left: -35% !important;
    }

    .image-selector-config .fade {
        opacity: 0;
        -webkit-transition: opacity .15s linear;
        -o-transition: opacity .15s linear;
        transition: opacity .15s linear;
        top: -100%!important;
    }

    .image-selector-config .fade.in {
        opacity: 1;
        top: 10%!important;
    }

    .hidden-img-url {
        display: none !important;
    }

    .item-image {
        border-radius: 3px !important;
        max-width: 60px !important;
        max-height: 60px !important;
    }

    .image-selector-config .line label {
        display: inline-block !important;
        float: left !important;
    }

    .folder-selector-block {
        padding: 10px;
        border-bottom: 1px solid #F5F5F5;
    }
</style>