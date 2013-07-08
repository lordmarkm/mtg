<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<span id="active-navbar-class" class="hide">communities</span>

<h3>${country.name }</h3>
<@tools.countrynav country=country active=2/>

<table class="table table-striped">
  <thead>
    <tr>
      <th>Name</th>
      <th>Binders</th>
    </tr>
  </thead>
  <tbody>
    <#list country.players as player>
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

<#if country.moderators?has_content>
<p>Moderators: 
  <#list country.moderators as moderator>
  <a href="/u/${moderator.name }">${moderator.name }</a>
  </#list>
</#if>
