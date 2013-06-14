<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />

<#if !binder??>
<div class="alert alert-error">
  Binder not found
</div>
<#else>

<h3>${binder.name }</h3>
<p><@tools.nl2br string=binder.description />

<#if binder.bundles?has_content>
<ol>
  <#list binder.bundles as bundle>
  ${bundle.card.name }
  </#list>
</ol>
<#else>
<div class="alert alert-info">
  There is nothing in this binder
</div>
</#if>
  
</#if>

