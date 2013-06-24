<#macro symbol image rarity expcode>
  <div class="replacable-image-container inline-block" upload-url="<@spring.url '/admin/upload/expsymbol/${expcode}/${rarity}' />">
  <#if image.path??>
    <img src="/image/${image.id}" />
  <#else>
    <div class="expansion-symbol-placeholder"></div>
  </#if>
  </div>
</#macro>

<#macro castingcost cost>
  <#list cost as symbol>
    <#switch symbol>
      <#case 'U'>
        <img src="<@spring.url '/images/mana/u.png' />" alt="U" />
        <#break>
      <#case 'W'>
        <img src="<@spring.url '/images/mana/w.png' />" alt="W" />
        <#break>
      <#case 'G'>
        <img src="<@spring.url '/images/mana/g.png' />" alt="G" />
        <#break>
      <#case 'R'>
        <img src="<@spring.url '/images/mana/r.png' />" alt="R" />
        <#break>
      <#case 'B'>
        <img src="<@spring.url '/images/mana/b.png' />" alt="B" />
        <#break>
      <#case 'X'>
        <img src="<@spring.url '/images/mana/x.png' />" alt="X" />
        <#break>
      <#case '1'>
        <img src="<@spring.url '/images/mana/1.png' />" alt="1" />
        <#break>
      <#case '2'>
        <img src="<@spring.url '/images/mana/2.png' />" alt="2" />
        <#break>
      <#case '3'>
        <img src="<@spring.url '/images/mana/3.png' />" alt="3" />
        <#break>
      <#case '4'>
        <img src="<@spring.url '/images/mana/4.png' />" alt="4" />
        <#break>
      <#case '5'>
        <img src="<@spring.url '/images/mana/5.png' />" alt="5" />
        <#break>
      <#case '6'>
        <img src="<@spring.url '/images/mana/6.png' />" alt="6" />
        <#break>
      <#case '7'>
        <img src="<@spring.url '/images/mana/7.png' />" alt="7" />
        <#break>
      <#case '8'>
        <img src="<@spring.url '/images/mana/8.png' />" alt="8" />
        <#break>
      <#case '9'>
        <img src="<@spring.url '/images/mana/9.png' />" alt="9" />
        <#break>
      <#case '10'>
        <img src="<@spring.url '/images/mana/10.png' />" alt="10" />
        <#break>
      <#case '11'>
        <img src="<@spring.url '/images/mana/11.png' />" alt="11" />
        <#break>
      <#case '12'>
        <img src="<@spring.url '/images/mana/12.png' />" alt="12" />
        <#break>
      <#case '15'>
        <img src="<@spring.url '/images/mana/15.png' />" alt="15" />
        <#break>
      <#case '16'>
        <img src="<@spring.url '/images/mana/16.png' />" alt="16" />
        <#break>
      <#-- TODO split costs -->
      <#default>
        ${symbol}
    </#switch>
  </#list>
</#macro>