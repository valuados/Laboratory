<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preferences" type="java.util.List<com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel>" -->

<input type="hidden" id="type${ns}" value="edit"/>
<form id="preferences-container-${ns}" class="preferences-container form-horizontal"
      action="<@portlet.resourceURL id="preferences.save"/>" data-form-events-profile="preferences">
<#list preferences as preference>
    <div class="control-group">
        <#include "${preference.view}">
    </div>
</#list>
    <div class="control-group">
        <div class="controls">
            <button type="submit" class="btn"><@spring.message "portal.preferences.save"/></button>
        </div>
    </div>
</form>