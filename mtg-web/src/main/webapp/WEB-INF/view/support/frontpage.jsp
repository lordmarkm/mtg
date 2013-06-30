<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />



<@sec.authorize access="hasRole('ROLE_ADMIN')">
<div class="well">
  <p>Admin actions
  <div>
    <a href="/admin/frontpage/newpost" class="btn">New frontpage post</a>
  </div>
</div>
</@sec.authorize>

<div id="posts-loadhere"></div>
<button id="btn-loadmore" class="btn">Load more</button>

<script>
var frontpageUrls = {
  load : '<@spring.url "/post/frontpage/1?ajax" />'
}

$(function() {
  
  var page = 0, size = 5;
  var 
   $loadhere = $('#posts-loadhere'),
   $btnLoad = $('#btn-loadmore');
  
  function loadmore() {
    loadhere.loading();
    $.get(frontpageUrls.load, {page: page, size: size}, function(response) {
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