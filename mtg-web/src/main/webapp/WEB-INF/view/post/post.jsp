<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<script src="<@spring.url '/javascript/fromnow.js' />"></script>

<h4 class="post-title">${post.title}</h4>
Posted by ${post.author.name } to <@tools.postparentLink post=post /> <span class="fromNow">${post.postdate }</span>

<div class="well mt20">${post.text?html }</div>