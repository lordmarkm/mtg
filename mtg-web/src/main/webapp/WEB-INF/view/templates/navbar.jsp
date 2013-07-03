<#import "/spring.ftl" as spring />
<link rel="stylesheet" href="<@spring.url '/css/navbar.css' />" />
<script>
var navbarUrls = {
  card   : '<@spring.url "/cards/" />',
  multicard   : '<@spring.url "/cards/multi/" />',
  search : '<@spring.url "/search/navbar?ajax" />'  
}
</script>
<script src="<@spring.url '/javascript/navbar.js' />"></script>

<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <a class="brand unoutlined" href="<@spring.url '/support/frontpage' />">Tomescour.org</a>
	    <form class="navbar-form pull-left" style="height:40px;">
	      <div class="input-append">
			    <input id="navbar-search" type="text" autocomplete="off" class="span2" placeholder="Search for cards">
			    <button class="btn btn-link" style="border-color: rgb(204,204,204);"><i class="icon-search"></i></button>
          <div id="clickery" class="hide"></div>
		    </div>
	    </form>
      <ul id="header-nav" class="nav pull-right">
         <li class="cards"><a href="<@spring.url '/cards/browse' />">Cards</a></li>
         <li class="binders"><a href="<@spring.url '/binders/browse' />">Binders</a></li>
         <li class="communities"><a href="<@spring.url '/community/browse' />">Communities</a></li>
         <li class="faq"><a href="<@spring.url '/support/faq' />">FAQ</a></li>
         <li class="dropdown">
          <@sec.authorize access="isAnonymous()">
          <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
            Anonymous
            <b class="caret"></b>
          </a>
          <ul class="dropdown-menu">
            <li><a href="<@spring.url '/auth/login' />">Login</a></li>
            <li><a href="<@spring.url '/auth/register' />">Register</a></li>
          </ul>
          </@sec.authorize>
          
          <@sec.authorize access="isAuthenticated()">
          <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
            <#if navimage??>
            <img class="navbar-thumb" src="<@spring.url '/image/${navimage.id?c }' />" />
            </#if>
            <@sec.authentication property="principal.username" />
            <b class="caret"></b>
          </a>
          <ul class="dropdown-menu">
            <li><a href="<@spring.url '/account/dashboard' />">Account Dashboard</a></li>
            <li><a href="<@spring.url '/u' />">User profile</a></li>
            <@sec.authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="<@spring.url '/admin/dashboard' />">Admin Dashboard</a>
            </@sec.authorize>
            <li class="divider"></li>
            <li><a class="no-intercept" href="<@spring.url '/logout' />">Logout</a></li>
          </ul>
          </@sec.authorize>
         </li>
      </ul>
    </div>
  </div>
</div>
<div class="clearfix"></div>
