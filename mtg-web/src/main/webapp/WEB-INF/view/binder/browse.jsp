<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "../templates/tools.ftl" as tools />

<span id="active-navbar-class" class="hide">binders</span>

<div class="tabbable">
  <ul class="nav nav-tabs">
    <@sec.authorize access="isAuthenticated()">
    <li class="active"><a href="#tab-mylocations-filter" data-toggle="tab">My locations</a></li>
    </@sec.authorize>
    
    <@sec.authorize access="isAnonymous()">
    <li id="li-anon-everything" class="li-loc-filter"><a href="<@spring.url '/binders/browse/all/0' />">Everything</a></li>
    </@sec.authorize>
    
    <li <@sec.authorize access="isAnonymous()">class="active"</@sec.authorize>><a href="#tab-meetups-filter" data-toggle="tab">By meetup</a></li>  
    <li><a href="#tab-cities-filter" data-toggle="tab">By city</a></li>
    <li><a href="#tab-countries-filter" data-toggle="tab">By country</a></li>
  </ul>
  <div class="tab-content">
    <@sec.authorize access="isAuthenticated()">
    <div class="tab-pane active" id="tab-mylocations-filter">
      <ul class="nav nav-pills">
        <li class="li-loc-filter">
          <a href="<@spring.url '/binders/browse/all/0' />">Everything</a>
        </li>
        <#if player.country??>
        <li class="li-loc-filter">
          <a href="<@spring.url '/binders/browse/country/${player.country.id?c }' />">${player.country.name }</a>
        </li>  
        </#if>
        <#if player.cities?has_content>
        <#list player.cities as city>
        <li class="li-loc-filter">
          <a href="<@spring.url '/binders/browse/city/${city.id?c }' />">${city.name }</a>
        </li>
        </#list>
        </#if>       
        <#if player.meetups?has_content>
        <#list player.meetups as meetup>
        <li class="li-loc-filter">
          <a href="<@spring.url '/binders/browse/meetup/${meetup.id?c }' />">${meetup.name }</a>
        </li>
        </#list>
        </#if>    
      </ul>
    </div>
    </@sec.authorize>
    <div class="tab-pane <@sec.authorize access="isAnonymous()">active</@sec.authorize>" id="tab-meetups-filter">
      <ul class="nav nav-pills">
        <#list meetups as meetup>
        <li class="li-loc-filter">
          <a href="<@spring.url '/binders/browse/meetup/${meetup.id?c }' />">${meetup.name }</a>
        </li>
        </#list>
      </ul>
    </div>    
    <div class="tab-pane" id="tab-cities-filter">
      <ul class="nav nav-pills">
        <#list cities as city>
        <li class="li-loc-filter">
          <a href="<@spring.url '/binders/browse/city/${city.id?c }' />">${city.name }</a>
        </li>
        </#list>
      </ul>
    </div>
    <div class="tab-pane" id="tab-countries-filter">
      <ul class="nav nav-pills">
        <#list countries as country>
        <li class="li-loc-filter">
          <a href="<@spring.url '/binders/browse/country/${country.id?c }' />">${country.name }</a>
        </li>  
        </#list>
      </ul>    
    </div>
  </div>
</div>

<div id="binders-browse-loadhere"></div>

<script>
$(function(){
	var 
	 $filters = $('.li-loc-filter'),
	 $loadhere = $('#binders-browse-loadhere');
	
	$('#li-anon-everything').click(function(){
		if($(this).hasClass('active')) return false;
		$('.nav.nav-tabs li.active').removeClass('active');
		$('.tab-pane.active').removeClass('active');
	});
	
	$filters.click(function(){
		var $li = $(this);
		if($li.hasClass('active')) return false;		
		
		$filters.removeClass('active');
		$(this).addClass('active');
		
		loadhere.loading();
		$loadhere.load($li.find('a').attr('href') + '?ajax', function(){
			loadhere.notloading();
		});
		
		
		return false;
	});
});
</script>
