<#-- @ftlvariable name="attachPreferences" type="boolean" -->
<#-- @ftlvariable name="attachJSON" type="boolean" -->
<#assign portlet=JspTaglibs["http://java.sun.com/portlet"]>
<#--<#assign liferay_ui = JspTaglibs["/WEB-INF/tld/liferay-ui.tld"]>-->
<#setting locale="fr_FR">
<@portlet.defineObjects/>

<#assign ns>
    <@portlet.namespace/>
</#assign>

<#import "/spring.ftl" as spring>

<#if attachPreferences?has_content && attachPreferences>
    <#include 'preferences/dump.references.ftl'>
</#if>

<#if attachJSON?has_content && attachJSON>
    <#include 'preferences/dump.JSON.ftl'>
</#if>