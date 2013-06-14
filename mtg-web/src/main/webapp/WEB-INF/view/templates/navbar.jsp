<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <a class="brand" href="<@spring.url '/' />">Magic Online Binder</a>
	    <form class="navbar-form pull-left">
	      <div class="input-append">
			    <input type="text" class="span2">
			    <button class="btn btn-link" style="border-color: rgb(204,204,204);"><i class="icon-search"></i></button>
		    </div>
	    </form>
      <ul class="nav pull-right">
         <li><a href="<@spring.url '/support/faq' />">FAQ</a></li>
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
            <@sec.authentication property="principal.username" />
            <b class="caret"></b>
          </a>
          <ul class="dropdown-menu">
            <li><a href="<@spring.url '/account/dashboard' />">Account Dashboard</a></li>
            <@sec.authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="<@spring.url '/admin/dashboard' />">Admin Dashboard</a>
            </@sec.authorize>
            <li class="divider"></li>
            <li><a href="<@spring.url '/logout' />">Logout</a></li>
          </ul>
          </@sec.authorize>
         </li>
      </ul>
    </div>
  </div>
</div>
<div class="clearfix"></div>