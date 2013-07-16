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
  <h3>Creatures</h3>
  <ul data-bind="foreach: creatures">
    <li>
      <span data-bind="text: name"></span>
    </li>
  </ul>
  
  <h3>Lands</h3>
  <ul data-bind="foreach: lands">
    <li>
      <span data-bind="text: name"></span>
    </li>
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

<style>
input[aria-controls="cards-table"] {
    width: 160px;
}
</style>

<script>
var editUrls = {
		deck : '<@spring.url "/deck/${deck.id}/json" />',
		addCard : '<@spring.url "/deck/${deck.id}/add/" />'
}

$(function(){
	//deck model
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
		
		creatures : ko.computed(function() {
			 console.debug('Finding creatures');
	     if(!model || !model.deck().cards()) {
         return [];
       }
       var lands = [];
       var card;
       for(var i = 0, len = model.deck().cards().length; i < len; ++i) {
         card = model.deck().cards()[i];
         console.debug('Checking ' + card.name);
         if(isCreature(card)) {
           lands.push(card);
         }
       }
       return lands;
		}),
		
		lands : ko.computed(function() {
			
			if(!model || !model.deck().cards) {
				return [];
			}
			
			var lands = [];
			var card;
			for(var i = 0, len = model.deck().cards.length; i < len; ++i) {
				card = model.deck().cards[i];
				if(isLand(card)) {
					lands.push(card);
				}
			}
			return lands;
		}),
		
		isCreature : function(card) {
			if(card.supertype.indexOf('Creature') != -1) return true;
		},
		isEnchantment : function(card) {
			if(isCreature(card)) return false;
			if(card.supertype.indexOf('Enchantment') != -1) return true;
		},
		isLand : function(card) {
			if(isCreature(card)) return false;
			if(isEnchantment(card)) return false;
			if(card.supertype.indexOf('Land') != -1) return true;
		},
		isSorcery : function(card) {
			if(card.supertype.indexOf('Sorcery') != -1) return true;
		},
		isInstant : function(card) {
			if(card.supertype.indexOf('Instant') != -1) return true;
		}
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
	
	
	//add card to deck
  $('#cards-table').on({
    click : function(){
      var id = $(this).attr('card-id');
      
      $.post(editUrls.addCard + id, function(response) {
        switch(response.status) {
        case '200':
        	model.deck().cards().push(response.card);
        	console.debug(model.deck().cards());
          footer.success(response.message);
          break;
        case '500':
          footer.error(response.message);
          break;
        default:
          footer.error('Error!');
        }
      });
    }
  }, '.btn.card-add');
});
</script>