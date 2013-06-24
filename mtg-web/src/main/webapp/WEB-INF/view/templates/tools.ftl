<#macro nl2br string>
  ${string?replace('\n','<br/>')}
</#macro>

<#macro flag country>
  <#if country.flag??>
  <img class="tinyflag" title="${country.name}" src="/image/${country.flag.id}" />
  </#if>
</#macro>

<#macro mana manastr>

</#macro>