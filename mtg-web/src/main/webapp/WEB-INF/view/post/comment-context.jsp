<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<script src="<@spring.url '/javascript/fromnow.js' />"></script>

<div id="commentable">

  <div class="post-container">
    <h4 class="post-title">${post.title }</h4>
    Posted by ${post.author.name } to <@tools.postparentLink post=post /> <span class="fromNow">${post.postdate }</span>
    
    <div></div>
    
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
  </div>
  
  <div class="alert alert-warning mt10">
    You are viewing a single comment's thread <br/>
    <a href="<@spring.url '/post/${post.id }/${post.urlFragment }' />">Click here to see all the comments</a>
  </div>
  
  <div id="post-replies">
    <@tools.context contextcomment=comment ancestors=ancestors index=0 username=username/>
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