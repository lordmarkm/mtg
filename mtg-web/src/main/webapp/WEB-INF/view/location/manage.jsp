<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<span id="active-navbar-class" class="hide">communities</span>

<h3>${location.name }</h3>

<#if type='city'>
<@tools.citynav city=location active=3/>
</#if>

<table class="table table-striped">
  <thead>
    <tr>
      <th>Name</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <#list location.players as player>
    <tr>
      <td><a href="/u/${player.name }">${player.name }</a></td>
      <td>
      </td>
    </tr>
    </#list>
  </tbody>
</table>