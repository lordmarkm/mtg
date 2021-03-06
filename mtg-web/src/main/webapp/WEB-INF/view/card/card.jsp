<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "../templates/magic.ftl" as magic />

<span id="active-navbar-class" class="hide">cards</span>

<#if card??>

<h2>${card.name }</h2>

<div class="row">
  <div class="span3">
    <div class="pull-right">
      <#if card.image??>
      <img id="card-img" src="<@spring.url '/image/${card.image.id?c }' />" />
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
      <dd class="pd2"><@magic.castingcost cost=card.costArray() /></dd>
      
      <dt>Types:</dt>
      <dd>${card.supertype } <#if card.subtype??>- ${card.subtype }</#if></dd>
      
      <dt>Expansion:</dt>
      <dd>${card.expansion.name }</dd>
      
      <dt>Rarity:</dt>
      <dd>${card.rarity }</dd>
    </dl>
    
    <div class="pull-left">
      <button id="btn-refresh-thumb" title="Refresh thumbnail" class="btn"><i class="icon-refresh"></i></button>
      <a class="btn" href="<@spring.url '/cards/find/${card.id?c }' />"><i class="icon-search"></i> Find in binders</a>
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
		wantlistAdd : '<@spring.url "/account/wantlist/add/${card.id?c}" />',
		refresh : '<@spring.url "/image/refresh/" />',
		image : '<@spring.url "/image/" />'
}
var cardConstants = {
		name : '${card.name!?js_string}',
		imageId : '${card.image.id?c}'
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
	
	$('#btn-refresh-thumb').click(function(){
		var $btn = $(this).attr('disabled', 'disabled').addClass('disabled');
		$.post(cardUrls.refresh + cardConstants.imageId, function(response) {
			switch(response.status) {
			case '200':
				$('#card-img').attr('src', cardUrls.image + response.image.id + '?time=' + Date.now());
				break;
			default:
				footer.error('Error refreshing thumbnail');
			}
		}).complete(function(){
			$btn.removeAttr('disabled').removeClass('disabled');
		});
	});
});
</script>