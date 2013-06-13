<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <a class="brand" href="<@spring.url '/' />">Magic Online Binder</a>
      <ul class="nav pull-right">
         <li class="dropdown">
          <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
            More
            <b class="caret"></b>
          </a>
          <ul class="dropdown-menu">
            <li><a href="<@spring.url '/support/faq' />">FAQ</a></li>
            <@sec.authorize access="isAuthenticated()">
            <li><a href="<@spring.url '/account/' />">Account Settings</a></li>
            </@sec.authorize>
            <@sec.authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="<@spring.url '/admin/dashboard' />">Admin Dashboard</a>
            </@sec.authorize>
            <li class="divider"></li>
            <@sec.authorize access="isAnonymous()">
            <li><a href="<@spring.url '/support/login' />">Login</a></li>
            </@sec.authorize>
            <@sec.authorize access="isAuthenticated()">
            <li><a href="<@spring.url '/logout' />">Logout</a></li>
            </@sec.authorize>
          </ul>
         </li>
      </ul>
    </div>
  </div>
</div>
<div class="clearfix"></div>