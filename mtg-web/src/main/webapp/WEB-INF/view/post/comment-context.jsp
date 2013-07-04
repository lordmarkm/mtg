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
      <a href="javascript:;">save</a>
      </@sec.authorize>
      <#if post.author.name == username>
      <a class="link-post-delete" href="javascript:;">delete</a>
      </#if>
    </small>
  </div>
  
  <div class="alert alert-warning mt10">
    You are viewing a single comment's thread <br/>
    <a href="<@spring.url '/post/${post.id }/${post.urlFragment }' />">Click here to see all the comments</a>
  </div>
  
  <div id="post-replies">
    <@tools.showcomment comment=comment username=username />
  </div>
</div>


<script>
var postUrls = {
    reply : '<@spring.url "/comment/post/comment/" />',
    comment : '<@spring.url "/comment/" />',
    deleteComment : '<@spring.url "/comment/delete/" />'
}
</script>
<script src="<@spring.url '/javascript/commentable.js' />"></script>