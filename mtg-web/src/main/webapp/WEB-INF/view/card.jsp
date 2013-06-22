<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

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
      <a class="btn" href="<@spring.url '/cards/find/${card.id }' />"><i class="icon-search"></i> Find in binders</a>
      <@sec.authorize access="isAuthenticated()">
      <button id="btn-wantlist-add" class="btn btn-primary"><i class="icon-plus icon-white"></i> Add to want list</button>
      </@sec.authorize>
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

<script>
var cardUrls = {
		wantlistAdd : '<@spring.url "/account/wantlist/add/${card.id}" />'
}
var cardConstants = {
		name : '${card.name!?js_string}'
}

$(function(){
	$('#btn-wantlist-add').click(function(){
		$.post(cardUrls.wantlistAdd, function(response) {
			switch(response.status) {
			case '200':
				bootbox.alert(cardConstants.name + ' added to your want list!');
				break;
			case '500':
				bootbox.alert(cardConstants.name + ' was already in your want list');
				break;
			default:
				footer.error('Error adding to wantlist!');
			}
		});
	});
});
</script>