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

<div class="text-center mt20">
  <button id="btn-loadmore" class="btn">Load more</button>
</div>

<script>
var postableUrls = {
  load : '<@spring.url "/post/frontpage/1/frontpage?ajax" />'
}
</script>
<script src="<@spring.url '/javascript/postable.js' />"></script>