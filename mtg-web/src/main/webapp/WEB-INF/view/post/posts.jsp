<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<#import "posts.ftl" as posttools />
<script src="<@spring.url '/javascript/fromnow.js' />"></script>

<#if user??>
  <#assign saved = user.player.saved>
<#else>
  <#assign saved = []>
</#if>

<#list posts as post>
  <@posttools.postlistitem 
    post=post 
    
    delete=(user?? 
      && (admin 
          || post.author.name==user.player.name 
          || (post.parent.locationParent?? 
              && post.parent.locationParent.moderators?seq_contains(user.player)))) 
    
    save=!saved?seq_contains(post) />
</#list>