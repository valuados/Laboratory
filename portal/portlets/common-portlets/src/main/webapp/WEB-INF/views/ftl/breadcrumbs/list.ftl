<#-- @ftlvariable name="breadcrumbList" type="java.util.List<com.netcracker.laboratory.portlets.utils.preferences.renderers.custom.BreadcrumbsRenderer.BreadCrumbItem>" -->

<div>
    <ul class="breadcrumb">
        <#if breadcrumbList??>
            <#list breadcrumbList as breadcrumbItem>

                <#--<li class="breadcrumb-item"><a href="#">Home</a></li>-->
                    <li class="breadcrumb-item <#if ! breadcrumbItem_has_next> active </#if>" style="display: table-cell;"><a href="${breadcrumbItem.url}"> ${breadcrumbItem.name} </a></li>
            </#list>

        </#if>
    </ul>
</div>