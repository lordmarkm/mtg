<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<#import "profile.ftl" as profile />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<script src="<@spring.url '/javascript/fromnow.js' />"></script>

<#include "profile-header.jsp">
<@profile.nav player=user.player active=1/>

<div id="commentable">
  <#list comments.content as comment>
  <#assign progenitor = comment.progenitor>
  <div>
    <a href="<@spring.url '/post/${progenitor.id}/${progenitor.urlFragment }' />">${progenitor.title }</a> by ${progenitor.author.name }
    <#if progenitor.parent.locationParent??> in ${progenitor.parent.locationParent.name }</#if>
  </div>
  <div class="comment-container mt5 mb10">
    <div class="comment-header">
      <a href="javascript:;" class="comment-expand">[-]</a> <a href="<@spring.url '/u/${comment.author.name}' />">${comment.author.name}</a> 
      <span class="fromNow muted tiny">${comment.postdate}</span>
    </div>
    <div class="comment-body">
      <div>
      <@tools.nl2br string=comment.text />
      </div>
      <small class="comment-controls" data-comment-id="${comment.id}">
        <#if username == comment.author.name>
        <a class="link-comment-delete" href="javascript:;">delete</a>
        </#if>
        <a href="<@spring.url '/comment/context/${comment.id }' />">context</a>
      </small>
    </div>
  </div>
  </#list>
</div>

<div class="navigation mt20">
  Page ${comments.number + 1 } of ${comments.totalPages }<br/>

  <#if comments.hasPreviousPage() || comments.hasNextPage()>
  <span class="muted">view more: </span>
  </#if>
  
  <#if comments.hasPreviousPage()>
  <a class="btn btn-small" href="<@spring.url '/u/${user.username }/comments/${comments.number - 1 }/10' />"><i class="fam-resultset-previous"></i> prev</a>
  </#if>
  
  <#if comments.hasNextPage()>
  <a class="btn btn-small" href="<@spring.url '/u/${user.username }/comments/${comments.number + 1 }/10' />">next <i class="fam-resultset-next"></i></a>
  </#if>
</div>

<script>
var postUrls = {
	    deleteComment : '<@spring.url "/comment/delete/" />',
}
</script>
<script src="<@spring.url '/javascript/commentable.js' />"></script>
