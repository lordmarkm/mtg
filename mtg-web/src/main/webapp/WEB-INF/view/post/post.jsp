<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<script src="<@spring.url '/javascript/fromnow.js' />"></script>

<h4 class="post-title">${post.title }</h4>
Posted by ${post.author.name } to <@tools.postparentLink post=post /> <span class="fromNow">${post.postdate }</span>

<div class="well mt20">${post.text?html }</div>

<small class="bold">
  <@sec.authorize access="isAuthenticated()">
  <a href="javascript:;">save</a>
  </@sec.authorize>
</small>

<@sec.authorize access="isAuthenticated()">
<div class="usertext-edit">
  <form id="form-on-post" action="<@spring.url '/comment/post/${post.id }' />" method="post">
    <textarea name="comment"></textarea>
    <div>
      <small class="pull-right muted">
        <a href="javascript:;">reddiquette</a>
        <a href="javascript:;">formatting help</a>
      </small>
      <input type="submit" value="save" class="btn btn-primary btn-small" />
    </div>
  </form>
</div>
</@sec.authorize>

<#list post.replies as comment>
  <@tools.showcomment comment=comment username=username/>
</#list>

<script>
var postUrls = {
		reply : '<@spring.url "/comment/comment/" />',
		comment : '<@spring.url "/comment/" />'
}
</script>
<script src="<@spring.url '/javascript/commentable.js' />"></script>
