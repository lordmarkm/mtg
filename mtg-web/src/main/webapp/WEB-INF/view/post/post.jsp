<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<script src="<@spring.url '/javascript/fromnow.js' />"></script>

<div id="commentable">

  <div class="post-container">
    <h4 class="post-title">
      <a href="${post.link! }" target="_blank">${post.title }</a>
      <#if post.linkDomain??><small class="tiniest">(${post.linkDomain! })</small></#if>
    </h4>
    Posted by <@tools.postAuthorLink post=post /> to <@tools.postparentLink post=post /> <span class="fromNow">${post.postdate }</span>
    
    <div class="well mt20 mb5">${post.text?html }</div>
    
    <small class="post-controls bold" data-post-id="${post.id }">
      <@sec.authorize access="isAuthenticated()">
        <#if user.player.saved?seq_contains(post)>
        <a class="link-post-unsave" href="javascript:;">unsave</a>
        <#else>
        <a class="link-post-save" href="javascript:;">save</a>
        </#if>
      </@sec.authorize>
      <#if post.deleted>
      <span class="muted">deleted</span>
      <#elseif post.author.name == username || moderator || admin>
      <a class="link-post-delete" href="javascript:;">delete</a>
      </#if>
    </small>
    
    <@sec.authorize access="isAuthenticated()">
    <div class="usertext-edit">
      <form id="form-on-post" class="form-comment" action="<@spring.url '/comment/post/post/${post.id }' />" method="post">
        <textarea name="text" placeholder="Reply to this post"></textarea>
        <div>
          <small class="pull-right muted">
            <a href="<@spring.url '/support/etiquette' />" target="_blank">etiquette</a>
            <a href="<@spring.url '/support/formatting' />" target="_blank">formatting help</a>
          </small>
          <input type="submit" value="save" class="btn btn-primary btn-small" />
        </div>
      </form>
    </div>
    </@sec.authorize>
  </div>
  
  <div id="post-replies" class="replies">
    <#list post.replies as comment>
    <@tools.showcomment comment=comment username=username highlight=false delete=admin||moderator />
    </#list>
  </div>

</div>

<script>
var postUrls = {
		reply : '<@spring.url "/comment/post/comment/" />',
		comment : '<@spring.url "/comment/" />',
		deleteComment : '<@spring.url "/comment/delete/" />',
		deletePost: '<@spring.url "/post/delete/" />',
		savePost : '<@spring.url "/account/post/save/" />',
		unsavePost : '<@spring.url "/account/post/unsave/" />'
}
</script>
<script src="<@spring.url '/javascript/commentable.js' />"></script>
