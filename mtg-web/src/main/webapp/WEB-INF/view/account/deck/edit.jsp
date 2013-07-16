<#import "/spring.ftl" as spring />
<script src="<@spring.url '/javascript/cardtable.js' />"></script>

<h3>Edit ${deck.name }</h3>

<div>
  <ul data-bind="with: deck">
    <li data-bind="text: name"></li>
    <li data-bind="text: description"></li>
  </ul>
</div>

<div class="span3">
    <div id="cards-pickmessage" class="alert alert-info">Select a collection to show cards</div>
    <table class="hide" id="cards-table">
      <thead>
        <tr>
          <th>Id</th>
          <th>Name</th>
          <th></th>
          <th>Cost</th>
          <th>Rarity</th>
        </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
    
    <div class="clearfix"></div>
    <div class="pt20">
      <form class="form-horizontal">
        <select id="cards-expansion-select">
          <option value="" disabled selected>Please choose collection</option>
          <option value="mycollection" disabled>My Collection</option>
          <#list exps as exp>  
          <option value="${exp.code }">${exp.name }</option>
          </#list>
        </select>
      </form>
    </div>
</div>

<script>
var editUrls = {
		deck : '<@spring.url "/deck/${deck.id}/json" />'
}

$(function(){

	
	//handle the deck
	var model = {
		deck : ko.observable(),
		getDeck : function() {
			 loadhere.loading();
		   $.get(editUrls.deck, function(response) {
			   switch(response.status) {
			   case '200':
				   var deck = response.deck;
				   model.deck(new Deck(deck.id, deck.name, deck.description, deck.cards));
				   loadhere.notloading();
				   break;
			   default:
				   footer.error('Error fetching deck');
			   }
	     });
		},
		
		lands : ko.computed(function(){
			
			//TODO get lands here
			return [];
			
		})
	}
	
	function Deck(id, name, description, cards) {
		var self = this;
		this.id = id;
		this.name = ko.observable(name);
		this.description = ko.observable(description);
		this.cards = ko.observableArray(cards);
	}
	
	ko.applyBindings(model);
	model.getDeck();
});
</script>