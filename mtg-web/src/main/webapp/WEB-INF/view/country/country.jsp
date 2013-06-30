<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />

<span id="active-navbar-class" class="hide">communities</span>

<#if (country.players?size == 0)>
<div class="alert alert-info">There is nobody in this country</div>
<#else>


<h3>People in ${country.name }</h3>
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


</#if>