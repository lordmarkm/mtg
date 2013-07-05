<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<#import "posts.ftl" as posttools />
<script src="<@spring.url '/javascript/fromnow.js' />"></script>

<#list posts as post>
  <@posttools.postlistitem post=post />
</#list>