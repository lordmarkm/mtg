<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "../templates/tools.ftl" as tools />
<span id="active-navbar-class" class="hide">communities</span>

<#if city.banned>
<div class="alert alert-error">This city has been banned</div>
<#else>

<h3>${city.name }</h3>
<@tools.citynav city=city active=0/>

<div id="commentable">
  <div id="posts-loadhere"></div>
</div>

<div class="text-center mt20">
  <button id="btn-loadmore" class="btn">Load more</button>
</div>

<script>
var postableUrls = {
  load : '<@spring.url "/post/city/${city.id?c}/${city.urlFragment}?ajax" />'
}
var postUrls = {
        deletePost: '<@spring.url "/post/delete/" />',
        savePost : '<@spring.url "/account/post/save/" />',
        unsavePost : '<@spring.url "/account/post/unsave/" />'
}
</script>
<script src="<@spring.url '/javascript/postable.js' />"></script>
<script src="<@spring.url '/javascript/commentable.js' />"></script>

</#if>