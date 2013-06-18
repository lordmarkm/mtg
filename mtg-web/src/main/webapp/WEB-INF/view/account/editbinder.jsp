<#import "/spring.ftl" as spring />

<link rel="stylesheet" href="<@spring.url '/css/editbinder.css' />" />
<script type="text/javascript" charset="utf8" src="<@spring.url '/libs/datatables/datatables-fnreloadajax.js' />" ></script>

<#if binder??>

<h1>${binder.name }</h1>

<div class="row">
  <div class="span3">
    <div class="pagination">
      <select id="select-binder-page">
        <#list 1..20 as page>
        <option value="${page }">Page ${page }</option>
        </#list>
      </select>
      <ul>
        <li><a id="btn-binder-prevpage" href="javascript:;">Prev</a></li>
        <li><a id="btn-binder-nextpage" href="javascript:;">Next</a></li>
      </ul>
    </div>
    <div id="page-table-container"></div>
  </div>
  
  <div class="span3" style="height: 600px; width: 0px; border-left: 4px solid #E6E6FA;"></div>
  
  <div class="span3">
  
    <div id="cards-pickmessage" class="alert alert-info">Select a collection to show cards</div>
    <table class="hide" id="cards-table">
      <thead>
        <tr>
          <th>Id</th>
          <th>Name</th>
          <th></th>
          <th>Cost</th>
          <th>Rarity</th>
        </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
    
    <div class="clearfix"></div>
    <div class="pt20">
      <form class="form-horizontal">
        <select id="cards-expansion-select">
          <option value="" disabled selected>Please choose collection</option>
          <option value="mycollection" disabled>My Collection</option>
          <#list exps as exp>  
          <option value="${exp.code }">${exp.name }</option>
          </#list>
        </select>
      </form>
    </div>
    
  </div> 

</div>

<script>
var editbinder = {
    binder : '${binder.urlFragment}'
}
var editbinderUrls = {
      cardsDt : '<@spring.url "/cards/datatables/" />',
      getPage : '<@spring.url "/account/editbinder/page/" />',
      addCard : '<@spring.url "/account/editbinder/add/" />'
}
</script>
<script src="<@spring.url '/javascript/editbinder.js' />"></script>

<#else>
  <div class="alert alert-error">Binder not found</div>
</#if>