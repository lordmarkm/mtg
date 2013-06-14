<#macro nl2br string>
  ${string?html?replace('\n','<br/>')}
</#macro>