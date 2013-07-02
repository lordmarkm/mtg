<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<span id="active-navbar-class" class="hide">communities</span>

<#if city.banned>
<div class="alert alert-error">This city has been banned</div>
<#else>

<h3>${city.name }</h3>
<@tools.citynav city=city active=2/>

<table class="table table-striped">
  <thead>
    <tr>
      <th>Name</th>
      <th>Binders</th>
    </tr>
  </thead>
  <tbody>
    <#list city.players as player>
    <tr>
      <td><a href="/u/${player.name }">${player.name }</a></td>
      <td>
        <ul class="unstyled">
        <#list player.binders as binder>
          <li><a href="<@spring.url '/u/${player.name }/${binder.urlFragment }' />">${binder.name }</a> - ${binder.cardCount() } cards
        </#list>
        </ul>
      </td>
    </tr>
    </#list>
  </tbody>
</table>

<#if city.moderators?has_content>
<p>Moderators: 
  <#list city.moderators as moderator>
  <a href="/u/${moderator.name }">${moderator.name }</a>
  </#list>
</#if>

</#if>