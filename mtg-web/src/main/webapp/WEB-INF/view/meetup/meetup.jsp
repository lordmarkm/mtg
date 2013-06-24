<#import "/spring.ftl" as spring />

<h3>${meetup.name }</h3>

<ol>
<#list meetup.players as player>
  <li><a href="<@spring.url '/u/${player.name }' />">${player.name }</a></li>
</#list>
</ol>