<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<script src="<@spring.url '/javascript/fromnow.js' />"></script>

<#list posts as post>
  <div class="post-container">
    <h4 class="post-title">${post.title }</h4>
    Posted by ${post.author.name } to <@tools.postparentLink post=post /> <span class="fromNow">${post.postdate }</span>
    <div>
      <span><a href="<@spring.url '/post/${post.id?c }/${post.urlFragment?url }' />">${post.replyCount } comments</a></span>
    </div>
    <div class="clearfix"></div>
  </div>
</#list>