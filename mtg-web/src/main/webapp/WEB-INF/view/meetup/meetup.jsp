<#import "/spring.ftl" as spring />

<span id="active-navbar-class" class="hide">communities</span>

<#if meetup??>

<h3>${meetup.name }</h3>

<ol>
<#list meetup.players as player>
  <li><a href="<@spring.url '/u/${player.name }' />">${player.name }</a></li>
</#list>
</ol>



<#else>
<div class="alert alert-info">Meetup not found</div>
</#if>