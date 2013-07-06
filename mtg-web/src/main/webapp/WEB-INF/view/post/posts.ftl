<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<#macro postlistitem post delete save>
  <div class="post-container">
    <h4 class="post-title">
      <a href="${post.link!}" target="_blank">${post.title }</a> 
      <#if post.linkDomain??><small class="muted tiniest">(${post.linkDomain})</small></#if>
    </h4>
    Posted by <a href="<@spring.url '/u/${post.author.name}' />">${post.author.name }</a> 
    to <@tools.postparentLink post=post /> <span class="fromNow">${post.postdate }</span>
    <div>
      <span><a href="<@spring.url '/post/${post.id?c }/${post.urlFragment?url }' />">${post.replyCount } comments</a></span>
      
      <small class="post-controls bold" data-post-id="${post.id }">
        <@sec.authorize access="isAuthenticated()">
          <#if save>
          <a class="link-post-save" href="javascript:;">save</a>
          <#else>
          <a class="link-post-unsave" href="javascript:;">unsave</a>
          </#if>
        </@sec.authorize>
        <#if delete && !post.deleted>
        <a class="link-post-delete" href="javascript:;">delete</a>
        <#elseif post.deleted>
        <span class="muted">deleted</span>
        </#if>
      </small>
      
    </div>
    <div class="clearfix"></div>
  </div>
</#macro>