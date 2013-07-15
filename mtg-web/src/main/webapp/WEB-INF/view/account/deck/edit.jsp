<#import "/spring.ftl" as spring />

<h3>Edit ${deck.name }</h3>

<div>
  <ul data-bind="with: deck">
    <li data-bind="text: name"></li>
    <li data-bind="text: description"></li>
  </ul>
</div>

<script>
var editUrls = {
		deck : '<@spring.url "/deck/${deck.id}/json" />'
}

$(function(){
	
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