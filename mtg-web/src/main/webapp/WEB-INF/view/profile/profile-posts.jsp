<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<#import "profile.ftl" as profile />
<#import "../post/posts.ftl" as posttools />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<script src="<@spring.url '/javascript/fromnow.js' />"></script>

<#include "profile-header.jsp">
<@profile.nav player=user.player active=2/>

<#list posts.content as post>
  <@posttools.postlistitem post=post />
</#list>

<div class="navigation mt20">
  Page ${posts.number + 1 } of ${posts.totalPages }<br/>
  
  <#if posts.hasPreviousPage() || posts.hasNextPage()>
  <span class="muted">view more: </span>
  </#if>
  
  <#if posts.hasPreviousPage()>
  <a class="btn btn-small" href="<@spring.url '/u/${user.username }/posts/${posts.number - 1 }/10' />"><i class="fam-resultset-previous"></i> prev</a>
  </#if>
  
  <#if posts.hasNextPage()>
  <a class="btn btn-small" href="<@spring.url '/u/${user.username }/posts/${posts.number + 1 }/10' />">next <i class="fam-resultset-next"></i></a>
  </#if>
</div>