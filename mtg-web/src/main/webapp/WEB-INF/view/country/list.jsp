<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />

<span id="active-navbar-class" class="hide">communities</span>

<h3>Country list (shows only occupied)</h3>
<table class="table table-striped">
<thead>
  <tr>
    <th>Name</th>
    <th></th>
    <th>Players</th>
    <th>Moderators</th>
  </tr>
</thead>
<#list countries as country>
  <tr>
    <td><a href="<@spring.url '/cy/${country.urlFragment }' />">${country.name }</a></td>
    <td><@tools.nl2br string=country.description?html /></td>
    <td>${country.players?size }</td>
    <td></td>
  </tr>
</#list>
</table>