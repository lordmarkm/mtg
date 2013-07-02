<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "../templates/tools.ftl" as tools />
<span id="active-navbar-class" class="hide">communities</span>

<#if city.banned>
<div class="alert alert-error">This city has been banned</div>
<#else>

<h3>${city.name }</h3>
<@tools.citynav city=city active=0/>

<div id="posts-loadhere"></div>
<button id="btn-loadmore" class="btn">Load more</button>

<script>
var postableUrls = {
  load : '<@spring.url "/post/city/${city.id?c}?ajax" />'
}
</script>
<script src="<@spring.url '/javascript/postable.js' />"></script>

</#if>