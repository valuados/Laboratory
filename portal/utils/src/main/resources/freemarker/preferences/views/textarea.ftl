<#-- @ftlvariable name="ns" type="java.lang.String" -->
<#-- @ftlvariable name="preference" type="com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel" -->
<label class="control-label"
       for="input-${preference.key}-${ns}"><@spring.message "portal.preferences.${preference.key}.key"/></label>

<div class="controls">
    <textarea name="${preference.key}" id="input-${preference.key}-${ns}">${preference.value}</textarea>
</div>