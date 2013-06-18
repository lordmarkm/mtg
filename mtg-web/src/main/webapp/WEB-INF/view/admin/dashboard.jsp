<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "../templates/tools.ftl" as tools />
<#import "../templates/magic.ftl" as magic />

<script type="text/javascript" charset="utf8" src="<@spring.url '/libs/datatables/datatables-fnreloadajax.js' />" ></script>

<link rel="stylesheet" href="<@spring.url '/css/admin-dashboard.css' />" />
<script src="<@spring.url '/javascript/admin.js' />"></script>

<#include "expansion-modal.jsp">
<#include "parsecards-modal.jsp">

<h3>Admin Details</h3>
<dl class="dl-horizontal">
  <dt>Username:
  <dd><@sec.authentication property="principal.username" />
  <dt>Authorities:
  <dd><@sec.authentication property="principal.authorities" />
</dl>

<h3>Application Details</h3>
<dl class="dl-horizontal">
  <dt>Image path:
  <dd>${env.getProperty('images.path') }
  
  <dt>Registered users:
  <dd>${usercount }
  
  <dt>Cards in database:
  <dd>${cardcount }
  
  <dt>Binders in database:
  <dd>${bindercount }
</dl>

<hr>

<h3>Expansions</h3>

<div class="pull-right mb10">
  <a href="#modal-newexpansion" class="btn btn-primary" data-toggle="modal">Add Expansion</a>
</div>
<div class="clearfix"></div>

<#if !expansions?has_content>
<div class="alert alert-info">There are no expansions on record</div>
<#else>
<table id="expansions-table" class="table">
  <thead>
    <tr>
      <th>Symbols <small class="muted">Click to change</small></th>
      <th>Code</th>
      <th>Name</th>
      <th>Description</th>
      <th>&nbsp;</th>
    </tr>
  </thead>
  <tbody>
    <#list expansions as expansion>
    <tr>
      <td>
        <@magic.symbol image=expansion.rarities.common   rarity='common'   expcode=expansion.code />
        <@magic.symbol image=expansion.rarities.uncommon rarity='uncommon' expcode=expansion.code />
        <@magic.symbol image=expansion.rarities.rare     rarity='rare'     expcode=expansion.code />
        <@magic.symbol image=expansion.rarities.mythic   rarity='mythic'   expcode=expansion.code />
      </td>
      <td>${expansion.code}</td>
      <td>${expansion.name }</td>
      <td><@tools.nl2br string=expansion.description /></td>
      <td>
        <div class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button">
            <i class="icon-large icon-cog"></i>
          </a>
          <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
            <li><a class="a-parse-cards" expname="${expansion.name }" expcode="${expansion.code }" href="javascript:;">Parse cards</a></li>
            <li><a href="javascript:;">Delete</a></li>
          </ul>
        </div>
      </td>
    </tr>
    </#list>
  </tbody>
</table>
</#if>

<h3>Cards</h3>

<#if !expansions?has_content>
<div class="alert alert-info">There are no expansions on record, and thus no cards</div>
<#else>
<script>
var urls = {
	card : '<@spring.url "/cards/" />',
  cardsDt : '<@spring.url "/cards/datatables/" />'
}
</script>

<form class="form-horizontal">
  <select id="cards-expansion-select">
    <option value="" disabled selected>Please choose expansion</option>
    <#list expansions as expansion>  
    <option value="${expansion.code }">${expansion.name }</option>
    </#list>
  </select>
</form>

<div style="min-height: 400px;">
  <div id="cards-pickmessage" class="alert alert-info">Select and expansion to show cards</div>
  <table class="hide" id="cards-table">
    <thead>
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Expansion</th>
        <th>Cost</th>
        <th>Rarity</th>
      </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
</div>
</#if>