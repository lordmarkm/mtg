<#import "/spring.ftl" as spring />

<#if card??>

<h2>${card.name }</h2>

<div class="row">
  <div class="span3">
    <div class="pull-right">
      <#if card.image??>
      <img src="<@spring.url '/image/${card.image.id }' />" />
      <#else>
      <img src="<@spring.url '/images/no-img.jpg' />" />
      </#if>
    </div>
  </div>
  <div id="card-container" class="span4 pull-left">
    <dl class="dl-horizontal" style="margin-left: 0px;">
      <dt>Card Name:</dt>
      <dd>${card.name }
      
      <dt>Mana Cost:</dt>
      <dd>${card.cost }</dd>
      
      <dt>Types:</dt>
      <dd>${card.supertype } <#if card.subtype??>- ${card.subtype }</#if></dd>
      
      <dt>Expansion:</dt>
      <dd>${card.expansion.name }</dd>
      
      <dt>Rarity:</dt>
      <dd>${card.rarity }</dd>
    </dl>
    
    <div class="pull-left">
      <a class="btn" href="<@spring.url '/cards/find/${card.id }' />">Find in binders</a>
    </div>
  </div>
</div>

<#else>
<div class="alert alert-error">Card not found.</div>
</#if>

<style>
#card-container dt {
  width: 90px;
}
#card-container dd {
  margin-left: 110px;
}
</style>