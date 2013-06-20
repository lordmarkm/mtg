<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<#include "editcontact-modal.jsp">

<h3>Account Details</h3>
<dl>
  <dt>Username</dt>
  <dd><@sec.authentication property="principal.username" /></dd>
  <dt>Authorities</dt>
  <dd><@sec.authentication property="principal.authorities" /></dd>
</dl>

<h3>Magic Profile</h3>
<div class="row-fluid">
  <div class="span4">
    <ul class="thumbnails">
      <li>
        <div class="thumbnail">
          <div class="replacable-image-container" upload-url="<@spring.url '/account/upload/profilepic' />"
           upload-title="Change profile picture">
            <#if account.player.image??>
            <img alt="300x200" src="<@spring.url '/image/${account.player.image.id }' />" />
            <#else>
            <img alt="300x200" src="<@spring.url '/images/no-img.jpg' />" />
            </#if>
          </div>
          <div class="caption">
            <span class="muted">Click to change</span>
          </div>
        </div>
      </li>
    </ul>
  </div>
  <div class="span6 pull-right">
    <table class="table">
      <tr>
        <th>Name</th>
        <td>${account.player.name }</td>
        <td></td>
      </tr>
      <tr>
        <th>Date joined</th>
        <td id="account-info-joined">${account.info.joined }</td>
        <td></td>
      </tr>
      <tr>
        <th>Last login</th>
        <td id="account-info-lastlogin">${account.info.lastLogin }</td>
        <td></td>
      </tr>
      <tr>
        <th>Flag</th>
        <td id="flag-container">
          <#if account.player.country?? && account.player.country.flag??>
          <img id="dashboard-flag" src="<@spring.url '/image/${account.player.country.flag.id?c }' />" />
          <#else>
          <img id="dashboard-flag">
          </#if>
        </td>
        <td>
          <select id="select-flag" style="width: 100px; font-size: 11px;">
            <option value="" disabled selected>Choose flag</option>
            <option value="0">None</option>
            <#list countries as country>
            <option value="${country.id }">${country.name }</option>
            </#list>
          </select>
        </td>
      </tr>
      <tr>
        <th>Cities</th>
        <td>
          <table class="table-condensed table-unstyled">
            <#list account.player.cities as city>
            <tr>
              <td>${city.name }</td>
              <td>
                <i city-id="${city.id }" class="remove-city icon-remove pointer"></i>
              </td>
            </tr>
            </#list>
          </table>
        </td>
        <td nowrap="nowrap"><a href="<@spring.url '/account/addcity' />" class="btn btn-primary btn-mini"><i class="icon-plus icon-white"></i> Add</a></td>
      </tr>
      <tr>
        <th>Meetups</th>
        <td>
          <table class="table-condensed table-unstyled">
            <#list account.player.meetups as meetup>
            <tr>
              <td>${meetup.name }</td>
              <td>
                <i meetup-id="${meetup.id }" class="remove-meetup icon-remove pointer"></i>
              </td>
            </tr>
            </#list>
          </table>
        </td>
        <td nowrap="nowrap"><a href="<@spring.url '/account/addmeetup' />" class="btn btn-primary btn-mini"><i class="icon-plus icon-white"></i> Add</a></td>
      </tr>
      <tr>
        <th>Contact</th>
        <td id="player-contact">
          <div title="${account.player.contact! }" style="max-width: 150px; overflow: hidden; text-overflow: ellipsis;">
            <@tools.nl2br string=account.player.contact! />
          </div>
        </td>
        <td><button id="edit-contact" class="btn btn-success btn-mini"><i class="icon-edit icon-white"></i> Edit</button></td>
      </tr>
    </table>
  </div>
</div>

<h3>Binders</h3>
<a class="btn btn-primary pull-right" href="/account/newbinder"><i class="icon-plus icon-white"></i> Create a binder</a>
<div class="clearfix mb10"></div>

<#if account.player.binders?has_content>
<table class="table">
<#list account.player.binders as binder>
  <tr>
    <td>
      <strong><a href="<@spring.url '/u/${account.player.name }/${binder.urlFragment }' />">${binder.name }</a></strong>
    </td>
    <td>
      <a href="<@spring.url '/account/editbinder/${binder.urlFragment}' />" class="btn btn-success btn-mini"><i class="icon-edit icon-white"></i> Edit</a>
      <a href="javascript:;" class="delete-binder btn btn-danger btn-mini" binder-name="${binder.name }" binder-id="${binder.urlFragment }"><i class="icon-remove icon-white"></i> Delete</a>
    </td>
  </tr>
</#list>
</table>
<#else>
<div class="alert alert-info">You have no binders right now</div>
</#if>

<h3>Want list</h3>
<#if wantlist?has_content>
<#else>
<div class="alert alert-info">You are not looking for any cards</div>
</#if>

<h3>Bookmarks</h3>
<#if bookmarks?has_content>
<#else>
<div class="alert alert-info">You haven't bookmarked any cards</div>
</#if>

<div class="mb50"></div>

<script>
var dashboardUrls = {
		image : '<@spring.url "/image/" />',
		selectFlag : '<@spring.url "/account/selectflag/" />',
		removeCity : '<@spring.url "/account/removecity/" />',
		removeMeetup : '<@spring.url "/account/removemeetup/" />',
		deleteBinder : '<@spring.url "/account/deletebinder/" />',
		editContact : '<@spring.url "/account/editcontact" />'
}
$(function(){
  //fromNow on joined and lastlogin
  var $joined = $('#account-info-joined');
  var $lastLogin = $('#account-info-lastlogin');
  $joined.text(moment($joined.text()).fromNow());
  
  var lastlogin = moment($lastLogin.text()).fromNow();
  $lastLogin.text(lastlogin == 'in a few seconds' ? 'Just now' : lastlogin);

  //flag change
  var $selectFlag = $('#select-flag'),
      $flag = $('#dashboard-flag');
  
  $selectFlag.change(function(){
	  var countryId = $(this).val();
	  $.post(dashboardUrls.selectFlag + countryId + '?ajax', function(response){
		  switch(response.status) {
		  case '200':
			  if(response.flag) {
				  loadhere.loading();
			    $flag.attr('src', dashboardUrls.image + response.flag.id).load(function(){
			    	loadhere.notloading();
			    });
			  } else {
				  $flag.attr('src', '');
			  }
			  break;
			default:
				footer.error('Error changing flag');
		  }
	  });
  });
  
  //remove city
  $('.remove-city').click(function(){
	  var $i = $(this);
	  var cityId = $i.attr('city-id');
	  
	  $.post(dashboardUrls.removeCity + cityId + '?ajax', function(response) {
		  switch(response.status) {
		  case '200':
			  $i.closest('tr').fadeOut();
			  break;
			default:
				footer.error('Could not remove city');
		  }
	  });
  });
  
  //remove meetup
  $('.remove-meetup').click(function(){
    var $i = $(this);
    var meetupId = $i.attr('meetup-id');
    
    $.post(dashboardUrls.removeMeetup + meetupId + '?ajax', function(response) {
      switch(response.status) {
      case '200':
        $i.closest('tr').fadeOut();
        break;
      default:
        footer.error('Could not remove meetup');
      }
    });
  });
  
  //edit contact
  var $editContact = $('#edit-contact');
  
  $editContact.click(function(){
	  
  });
  
  //delete binder
  $(document).on({
		click: function(){
			var $btn = $(this);
			var name = $btn.attr('binder-name');
			var id = $btn.attr('binder-id');
			
			if(confirm('Are you sure you want to delete ' + name + '?')) {
				$.post(dashboardUrls.deleteBinder + id, function(response) {
					switch(response.status) {
					case '200':
						$btn.closest('tr').fadeOut();
						break;
					default:
						footer.error('Error deleting ' + name);
					}
				}).error(function(){
					footer.error('Error deleting ' + name);
				});
			}
		}
	}, '.delete-binder')
});
</script>