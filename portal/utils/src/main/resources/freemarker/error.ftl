<#-- @ftlvariable name="message" type="java.lang.String" -->
<#-- @ftlvariable name="stackTrace" type="java.lang.String" -->
<div class="alert alert-error"><@spring.message "portal.error.general.portlet"/>
    <button class="btn btn-danger btn-mini" style="line-height: 8px;" onclick="jQuery(this).next().toggle()">...
    </button>
    <pre style="display: none;font-size: 11px;color: #000;">
    ${stackTrace}
    </pre>
</div>