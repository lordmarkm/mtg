<#import "/spring.ftl" as spring /> 

<h3>Choose a city</h3>

<#if cities?has_content>
<table id="add-cities-table" class="table">
  <thead>
    <tr>
      <th>Name</th>
      <th></th><!-- desc -->
      <th>Country</th>
      <th></th><!-- actions -->
    </tr>
  </thead>
  <tbody>
    <#list cities as city>
    <tr>
      <td>${city.name }</td>
      <td>${city.description }</td>
      <td><#if city.country??>${city.country.name }</#if></td>
      <td>
        <button class="btn-add-city btn btn-primary btn-mini" city-id="${city.id?c }">
          <i class="icon-plus icon-white"></i> Add city
        </button>
      </td>
    </tr>
    </#list>
  </tbody>
</table>
<#else>
<div class="alert alert-info">There are no cities on record. Feel free to create a new one.</div>
</#if>

<div class="mb50"></div>

<h3>Or create a new one</h3>
<#assign action><@spring.url '/account/addcity?ajax' /></#assign>
<form id="form-newcity" action="${action }" method="post">
  <input type="hidden" name="cityId" />
  <#if error??>
  <div class="alert alert-error">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    ${error }
  </div>
  </#if>
  
  <div class="control-group">
    <label class="control-label" for="countryId">Country</label>
    <div class="controls">
      <select id="countryId" name="countryId">
        <option value="0">None</option>
        <#list countries as country>
        <option value="${country.id?c }">${country.name }</option>
        </#list>
      </select>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="name">Name</label>
    <div class="controls">
      <input type="text" name="name" id="name" />
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="description">Description</label>
    <div class="controls">
      <textarea name="description" id="description"></textarea>
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <button class="btn btn-primary">Create new city</button>
    </div>
  </div>    
</form>

<div class="pull-right">
  <a href="<@spring.url '/account/dashboard' />" class="btn">Cancel</a>
</div>

<script>
var addCityUrls = {
		dashboard : '<@spring.url "/account/dashboard" />'
}
$(function(){
	var $table = $('#add-cities-table'),
	    $form = $('#form-newcity');
	
	$table.dataTable({
    'sDom': "ftpr",
		'aoColumnDefs' : [
		                  {bSortable: false, aTargets: [1,3]},
		                  {bSearchable: false, aTargets: [3]}
		                  ]
	});
	
	//create new city from form
	$form.submit(function(){
    $.post($form.attr('action'), $form.serialize(), function(response) {
        switch(response.status) {
        case '200':
          window.location.href = addCityUrls.dashboard;
          break;
        case '500':
          footer.error(response.message);
          break;
        default:
          footer.debug('Error submitting form :(');
        }
    });
		return false;
	});
    
  //add existing city
  $(document).on({
	  click: function() {
		    var cityId = $(this).attr('city-id');
		    $form.find('input[name="cityId"]').val(cityId);
		    $form.submit();
	  }
  }, '.btn-add-city');
});
</script>
