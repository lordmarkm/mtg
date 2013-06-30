<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<@sec.authorize access="hasRole('ROLE_ADMIN')">
<a href="/admin/frontpage/newpost" class="btn">New frontpage post</a>
</@sec.authorize>

<div id="posts-loadhere"></div>
<button id="btn-loadmore" class="btn">Load more</button>

<script>
var frontpageUrls = {
		load : '<@spring.url "/post/frontpage/1" />'
}

$(function() {
	
	var page = 0, size = 10;
	var 
	 $loadhere = $('#posts-loadhere'),
	 $btnLoad = $('#btn-loadmore');
	
	function loadmore() {
		loadhere.loading();
		$.get(frontpageUrls.load, {page: page, size: size}, function(response) {
			if(response.length === 0) {
				$btnLoad.hide();
			}
			$loadhere.append(response);
			loadhere.notloading();
		});
	}
	
	$btnLoad.click(function(){
		page += 1;
		loadmore();
	});
	
	loadmore(); //first time
	
});
</script>