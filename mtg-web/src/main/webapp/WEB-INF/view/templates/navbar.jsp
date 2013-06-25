<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <a class="brand" href="<@spring.url '/support/welcome' />">Magic Online Binder</a>
	    <form class="navbar-form pull-left">
	      <div class="input-append">
			    <input id="navbar-search" type="text" autocomplete="off" class="span2" placeholder="Search for cards">
			    <button class="btn btn-link" style="border-color: rgb(204,204,204);"><i class="icon-search"></i></button>
          <div id="clickery" class="hide"></div>
		    </div>
	    </form>
      <ul class="nav pull-right">
         <li><a href="<@spring.url '/cards/browse' />">Cards</a></li>
         <li><a href="<@spring.url '/binders/browse' />">Binders</a></li>
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
            <#if navimage??>
            <img class="navbar-thumb" src="<@spring.url '/image/${navimage.id }' />" />
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
            <li><a href="<@spring.url '/logout' />">Logout</a></li>
          </ul>
          </@sec.authorize>
         </li>
      </ul>
    </div>
  </div>
</div>
<div class="clearfix"></div>

<script>
var navbarUrls = {
  card   : '<@spring.url "/cards/" />',
  multicard   : '<@spring.url "/cards/multi/" />',
	search : '<@spring.url "/search/navbar?ajax" />'	
}
	
$(function(){

	//search
	var 
	 $input = $('#navbar-search'),
	 $clickery = $('#clickery');
	
	$input.typeahead({
		source: function(query, process) {
			return $.get(navbarUrls.search, {query : query}, function(response) {
				switch(response.status) {
				case '200':
					var items = [];
					$.each(response.data, function(i, dto) {
						switch(dto.type) {
						case 'card':
							
							if(dto.id.indexOf(',') != -1) {
							 items.push('<a href="' + navbarUrls.multicard + dto.id + '">' + dto.name + '</a>');
							} else {
					     items.push('<a href="' + navbarUrls.card + dto.id + '">' + dto.name + '</a>');
							}
							break;
						default:
							items.push(dto.name);
						}
					});
					return process(items);
					break;
				default:
					return process([]);
				}
				return process(response.data);
			});
		},
		updater: function(item) {
			$clickery.html('');
			$(item).appendTo($clickery).click();
		},
		highlighter: function(item) {
			return item;
		}
	});
	
});
</script>