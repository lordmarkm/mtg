<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<h3>
  Magic Profile - ${user.player.name }
  <@sec.authorize access="hasRole('ROLE_ADMIN')">
  <button class="btn btn-danger"><i class="fam-user-delete"></i> Ban user</button>
  </@sec.authorize>
</h3>

<div class="row-fluid">
  <div class="span4">
    <ul class="thumbnails">
      <li>
        <div class="thumbnail">
            <#if user.player.image??>
            <img alt="300x200" src="<@spring.url '/image/${user.player.image.id }' />" />
            <#else>
            <img alt="300x200" src="<@spring.url '/images/no-img.jpg' />" />
            </#if>
          <div class="caption">
            <span class="muted">${user.player.name }</span>
          </div>
        </div>
      </li>
    </ul>
  </div>
  <div class="span7 pull-right">
    <table class="table">
      <tr>
        <th>Name</th>
        <td>${user.player.name }</td>
      </tr>
      <tr>
        <th>Date joined</th>
        <td class="fromnow">${user.info.joined }</td>
      </tr>
      <tr>
        <th>Last login</th>
        <td class="fromnow">${user.info.lastLogin }</td>
      </tr>
      <tr>
        <th>Flag</th>
        <td id="flag-container">
          <#if user.player.country?? && user.player.country.flag??>
          <img id="dashboard-flag" src="<@spring.url '/image/${user.player.country.flag.id?c }' />" title="${user.player.country.name }"/>
          <#else>
          <img id="dashboard-flag">
          </#if>
        </td>
      </tr>
      <tr>
        <th>Cities</th>
        <td>
          <table class="table-condensed table-unstyled">
            <#list user.player.cities as city>
            <tr>
              <td class="city-name"><a href="<@spring.url '/c/${city.urlFragment }' />">${city.name }</a></td>
            </tr>
            </#list>
          </table>
        </td>
      </tr>
      <tr>
        <th>Meetups</th>
        <td>
          <table class="table-condensed table-unstyled">
            <#list user.player.meetups as meetup>
            <tr>
              <td class="meetup-name"><a href="<@spring.url '/m/${meetup.urlFragment }' />">${meetup.name }</a></td>
            </tr>
            </#list>
          </table>
        </td>
      </tr>
      <tr>
        <th>Contact</th>
        <td id="player-contact">
          <div title="${user.player.contact! }" style="max-width: 250px; overflow: hidden; text-overflow: ellipsis;">
            <@tools.nl2br string=user.player.contact! />
          </div>
        </td>
      </tr>
    </table>
  </div>
</div>

<h3>Binders</h3>
<#if user.player.binders?has_content>
<table class="table">
  <thead>
    <tr>
      <th>Name</th>
      <th></th>
      <th>Cards</th>
      <th>Last modified</th>
    </tr>
  </thead>
  <tbody>
    <#list user.player.binders as binder>
    <tr class="tr-binder" binder-id="${binder.urlFragment }">
      <td>
        <strong><a href="<@spring.url '/u/${user.player.name }/${binder.urlFragment }' />">${binder.name }</a></strong>
      </td>
      <td>
        <@tools.nl2br string=binder.description! />
      </td>
      <td>
        ${binder.cardCount() }
      </td>
      <td>
        <div class="fromnow">${binder.lastModified }</div>
      </td>
    </tr>
    </#list>
  </tbody>
</table>
<#else>
<div class="alert alert-info">${user.player.name } has no binders right now</div>
</#if>

<h3>Want list</h3>
<#if user.player.wanted?has_content>
<table class="table table-striped">
  <thead>
    <tr>
      <th>Name</th>
      <th>Expansion</th>
      <th>Count</th>
      <th>Note</th>
    </tr>
  </thead>
  <tbody>
    <#list user.player.wanted as wanted>
    <tr wanted-id="${wanted.id }">
      <td>${wanted.card.name }</td>
      <td>${wanted.card.expansion.name }</td>
      <td>${wanted.count }</td>
      <td>${wanted.note!?html }</td>
    </tr>
    </#list>
  </tbody>
</table>
<#else>
<div class="alert alert-info">${user.player.name } is not looking for any cards</div>
</#if>

<script>
$(function(){
	$('.fromnow').each(function(i, that){
		var $this = $(that);
		$this.text(moment($this.text()).fromNow());
	});
});
</script>