<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preference" type="com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel" -->
<#-- @ftlvariable name="images" type="java.util.List<java.lang.String>" -->
<#-- @ftlvariable name="offers" type="java.util.List<com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValue>" -->
<#-- @ftlvariable name="value" type="com.netcracker.laboratory.portlets.utils.preferences.renderers.custom.ActionPagesRenderer.ActionPage" -->
<div class="images-container">
<#list images as image>
    <div class="image-container">
        <img class="image-item" src="${image}">

        <div class="image-title">${image?substring(image?last_index_of('/')+1)}</div>
        <div class="select-icon-box">
            <i class="icon icon-ok"></i>
        </div>
    </div>
</#list>
<#if images?size == 0>
    <div class="no-images">
        <i class="icon-exclamation-sign"></i>  No images available in current directory. You can upload it from Administrator Control Panel.

    </div>
</#if>
</div>
<style>
    .images-container {
        min-height: 300px;
    }
    .image-container {
        display: inline-block;
        float: left;
        margin: 12px;
        padding: 10px 0;
        position: relative;
        vertical-align: top;
        width: 160px;
        height: 160px;
        text-align: center;
        border-radius: 5px;
        border: 1px solid #ECECEC;
        position: relative;

    }

    .image-item {
        max-width: 126px;
        max-height: 126px;
    }

    .image-title {
        position: absolute;
        bottom: 0;
        text-align: center;
        width: 100%;
    }

    .select-icon-box {
        position: absolute;
        bottom: 7px;
        right: 4px;
        font-size: 20px;
        padding: 5px;
        border-radius: 50%;
        width: 30px;
        height: 30px;
        background-color: rgb(20, 139, 199);
        color: white;
        display: none;
    }

    .image-container.selected, .image-container:hover {
        border: 1px solid #3D90E2;
        background: #D3E8F1;
        cursor: pointer;
    }

    .image-container.selected .select-icon-box,
    .image-container:hover .select-icon-box {
        display: block;
    }

</style>
