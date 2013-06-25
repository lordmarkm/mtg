<#import "/spring.ftl" as spring />

<h3>All the cards</h3>

<select id="select-expansion">
  <option value="all">All expansions</option>
  <#list expansions as expansion>
  <option value="${expansion.code }">${expansion.name }</option>
  </#list>
</select>

<table id="all-the-cards-table">
  <thead>
    <tr>
      <th>Id</th>
      <th>Name</th>
      <th>Expansion</th>
      <th>Cost</th>
      <th>Rarity</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
  </tbody>
</table>

<style>
select[name="all-the-cards-table_length"] {
  margin-top: 10px;
  height: auto;
  width: auto;
}
</style>

<script type="text/javascript" charset="utf8" src="<@spring.url '/libs/datatables/datatables-fnreloadajax.js' />" ></script>
<script>
var cardBrowseUrls = {
    card     : '<@spring.url "/cards/" />',
    cardsDt  : '<@spring.url "/cards/datatables/" />',
    find     : '<@spring.url "/cards/find/" />'
}
</script>
<script type="text/javascript" charset="utf8" src="<@spring.url '/javascript/browse-cards.js' />" ></script>