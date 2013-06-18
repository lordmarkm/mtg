<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />

<#if !binder??>
<div class="alert alert-error">
  Binder not found
</div>
<#else>

<h3>${binder.name }</h3>
<p><@tools.nl2br string=binder.description />

<#if binder.pages?has_content>
<ol>
  <#list binder.pages as page>
  <li>${page.bundles?size }</li>
  </#list>
</ol>
<#else>
<div class="alert alert-info">
  There is nothing in this binder
</div>
</#if>
  
</#if>

