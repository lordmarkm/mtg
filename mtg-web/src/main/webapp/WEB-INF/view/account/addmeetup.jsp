<#import "/spring.ftl" as spring /> 

<h3>Choose a meetup</h3>

<#if meetups?has_content>
<table id="add-meetups-table" class="table">
  <thead>
    <tr>
      <th>Name</th>
      <th></th><!-- desc -->
      <th>City</th>
      <th></th><!-- actions -->
    </tr>
  </thead>
  <tbody>
    <#list meetups as meetup>
    <tr>
      <td>${meetup.name }</td>
      <td>${meetup.description }</td>
      <td><#if meetup.city??>${meetup.city.name }</#if></td>
      <td>
        <button class="btn-add-meetup btn btn-primary btn-mini" meetup-id="${meetup.id }">
          <i class="icon-plus icon-white"></i> Add meetup
        </button>
      </td>
    </tr>
    </#list>
  </tbody>
</table>
<#else>
<div class="alert alert-info">There are no meetups on record. Feel free to create a new one.</div>
</#if>

<div class="mb50"></div>

<h3>Or create a new one</h3>
<#assign action><@spring.url '/account/addmeetup?ajax' /></#assign>
<form id="form-newmeetup" action="${action }" method="post">
  <input type="hidden" name="meetupId" />
  <#if error??>
  <div class="alert alert-error">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    ${error }
  </div>
  </#if>
  
  <div class="control-group">
    <label class="control-label" for="cityId">City</label>
    <div class="controls">
      <select id="cityId" name="cityId">
        <option value="0">None</option>
        <#list cities as city>
        <option value="${city.id }">${city.name }</option>
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
      <button class="btn btn-primary">Create new meetup</button>
    </div>
  </div>    
</form>

<div class="pull-right">
  <a href="<@spring.url '/account/dashboard' />" class="btn">Cancel</a>
</div>

<script>
var addMeetupUrls = {
		dashboard : '<@spring.url "/account/dashboard" />'
}
$(function(){
	var $table = $('#add-meetups-table'),
	    $form = $('#form-newmeetup');
	
	$table.dataTable({
    'sDom': "ftpr",
		'aoColumnDefs' : [
		                  {bSortable: false, aTargets: [1,3]},
		                  {bSearchable: false, aTargets: [3]}
		                  ]
	});
	
	//create new meetup from form
	$form.submit(function(){
    $.post($form.attr('action'), $form.serialize(), function(response) {
        switch(response.status) {
        case '200':
          window.location.href = addMeetupUrls.dashboard;
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
    
  //add existing meetup
  $(document).on({
	  click: function() {
		    var meetupId = $(this).attr('meetup-id');
		    $form.find('input[name="meetupId"]').val(meetupId);
		    $form.submit();
	  }
  }, '.btn-add-meetup');
});
</script>
