<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<#include "editcontact-modal.jsp">
<#include "resendverification-modal.jsp">
<#include "deletebinder-modal.jsp">

<h3>Account Details</h3>
<dl class="dl-horizontal">
  <dt>Username</dt>
  <dd><@sec.authentication property="principal.username" /></dd>
  <dt>Authorities</dt>
  <dd><@sec.authentication property="principal.authorities" /></dd>
  <dt>Email
  <dd>${account.info.email! } &nbsp;&nbsp;  
    <#if account.info.authenticated>
      <i class="fam-accept"></i><strong><small>Verified!</small></strong>
    <#else>
      <button id="btn-resend-verification" class="btn btn-mini btn-success"><i class="icon-envelope icon-white"></i> Resend verification</button>
    </#if>
  </dd>
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
  <div class="span7 pull-right">
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
              <td class="city-name">${city.name }</td>
              <td>
                <i city-id="${city.id }" class="remove-city icon-remove pointer"></i>
              </td>
            </tr>
            </#list>
          </table>
        </td>
        <td nowrap="nowrap"><a id="btn-addcity" href="<@spring.url '/account/addcity' />" class="btn btn-primary btn-mini"><i class="icon-plus icon-white"></i> Add</a></td>
      </tr>
      <tr>
        <th>Meetups</th>
        <td>
          <table class="table-condensed table-unstyled">
            <#list account.player.meetups as meetup>
            <tr>
              <td class="meetup-name">${meetup.name }</td>
              <td>
                <i meetup-id="${meetup.id }" class="remove-meetup icon-remove pointer"></i>
              </td>
            </tr>
            </#list>
          </table>
        </td>
        <td nowrap="nowrap"><a id="btn-addmeetup" href="<@spring.url '/account/addmeetup' />" class="btn btn-primary btn-mini"><i class="icon-plus icon-white"></i> Add</a></td>
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
<a  id="btn-addbinder" class="btn btn-primary pull-right" href="/account/newbinder"><i class="icon-plus icon-white"></i> Create a binder</a>
<div class="clearfix mb10"></div>

<#if account.player.binders?has_content>
<table class="table">
<#list account.player.binders as binder>
  <tr class="tr-binder" binder-id="${binder.urlFragment }">
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

<div id="wantlist-container"></div>

<div class="mb50"></div>

<script>
var dashboardUrls = {
    image : '<@spring.url "/image/" />',
    selectFlag : '<@spring.url "/account/selectflag/" />',
    removeCity : '<@spring.url "/account/removecity/" />',
    removeMeetup : '<@spring.url "/account/removemeetup/" />',
    deleteBinder : '<@spring.url "/account/deletebinder/" />',
    editContact : '<@spring.url "/account/editcontact" />',
    resendVerification : '<@spring.url "/account/verify/resend" />',
    wantlist: '<@spring.url "/account/wantlist?ajax" />',
    wantedOp: '<@spring.url "/account/wantlist/" />',
    editWantedNote : '<@spring.url "/account/wantlist/editnote/" />'
}

var dashboardConstants = {
    maxCities : 3,
    maxMeetups: 5,
    maxBinders: 3
}

$(function(){
  //resend verification
  var $btnReverify = $('#btn-resend-verification');
  
  $btnReverify.click(function(){
    $.post(dashboardUrls.resendVerification, function(response){
      switch(response.status) {
      case '200':
        $('#resendverification-modal').modal('show');
        $btnReverify.hide();
        break;
      default:
        footer.error('Error resending verification email.');
      }
    });
  });
  
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
  
  //locations
  var 
    $btnAddCity = $('#btn-addcity'),
    $btnAddMeetup = $('#btn-addmeetup');
  
  //limit locations per user
  if($('td .city-name').length >= dashboardConstants.maxCities) {
    $btnAddCity.hide();
  }
  if($('td .meetup-name').length >= dashboardConstants.maxMeetups) {
    $btnAddMeetup.hide();
  }
  
  //remove city
  $('.remove-city').click(function(){
    var $i = $(this);
    var cityId = $i.attr('city-id');
    
    $.post(dashboardUrls.removeCity + cityId + '?ajax', function(response) {
      switch(response.status) {
      case '200':
        $i.closest('tr').fadeOut();
        $btnAddCity.show();
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
        $btnAddMeetup.show();
        break;
      default:
        footer.error('Could not remove meetup');
      }
    });
  });
  
  //binder management
  var $btnAddbinder = $('#btn-addbinder');
  if($('.tr-binder').length >= dashboardConstants.maxBinders) {
    $btnAddbinder.hide();
  }
  
  //init wantlist
  $('#wantlist-container').load(dashboardUrls.wantlist);
});
</script>