<#import "/spring.ftl" as spring />

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