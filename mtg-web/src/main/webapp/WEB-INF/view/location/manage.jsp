<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<span id="active-navbar-class" class="hide">communities</span>

<h3>${location.name }</h3>

<#if type='city'>
<@tools.citynav city=location active=3/>
<#elseif type='meetup'>
<@tools.meetupnav meetup=location active=3/>
<#elseif type='country'>
<@tools.countrynav country=location active=3/>
</#if>

<table class="table table-striped">
  <thead>
    <tr>
      <th>Name</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <#assign mods = location.moderators>
    <#list location.players as player>
    <tr>
      <td><a href="/u/${player.name }">${player.name }</a></td>
      <td>
        <#if !mods?seq_contains(player)>
        <button class="btn-make-moderator" data-player-id="${player.id }" title="Make ${player.name } a moderator for ${location.name}">
          <i class="icon-plus"></i><i class="fam-group-gear"></i>
        </button>
        <#else>
        <button class="btn-make-moderator disabled noclick" title="${player.name } is a moderator for ${location.name}" disabled="disabled">
          <i class="icon-check"></i> <i class="fam-group-gear"></i>
        </button>
        </#if>
      </td>
    </tr>
    </#list>
  </tbody>
</table>

<script>
var manage = {
		type : '${type}',
		locationId : '${location.id}',
		makeMod : '<@spring.url "/community/makemod/" />'
}

$(function(){
	$('.btn-make-moderator').click(function(){
		var $that = $(this);
		var playerId = $that.data('player-id');
		$.post(manage.makeMod + manage.type + '/' + manage.locationId + '/' + playerId, function(response) {
			switch(response.status) {
			case '200':
				$that.addClass('disabled').attr('disabled', 'disabled')
				  .attr('title', 'You have made this player a moderator')
				  .find('.icon-plus').removeClass('icon-plus').addClass('icon-check');
				break;
			default:
				footer.error('Error granting mod status!');
			}
		});
	});
});
</script>