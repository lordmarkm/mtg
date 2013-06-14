<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />

<#if !binder??>
<div class="alert alert-error">
  Binder not found
</div>
<#else>

<h3>${binder.name }</h3>
<p><@tools.nl2br string=binder.description />
  
</#if>

