<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />

<span id="active-navbar-class" class="hide">communities</span>

<h3>City list</h3>
<table class="table table-striped">
<thead>
  <tr>
    <th>Name</th>
    <th></th>
    <th>Players</th>
    <th>Moderators</th>
  </tr>
</thead>
<#list cities as city>
  <tr>
    <td><a href="<@spring.url '/c/${city.urlFragment }' />">${city.name }</a></td>
    <td><@tools.nl2br string=city.description?html /></td>
    <td>${city.players?size }</td>
    <td></td>
  </tr>
</#list>
</table>