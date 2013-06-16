<#macro symbol image rarity expcode>
  <div class="replacable-image-container inline-block" upload-url="<@spring.url '/admin/upload/expsymbol/${expcode}/${rarity}' />">
  <#if image.path??>
    <img src="/image/${image.id}" />
  <#else>
    <div class="expansion-symbol-placeholder"></div>
  </#if>
  </div>
</#macro>