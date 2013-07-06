<#import "../templates/tools.ftl" as tools />

<#macro nav player active self>
<div class="tabbable">
  <ul class="nav nav-tabs">
    <li <#if active=0>class="active"</#if>><a href="<@spring.url '/u/${player.name }' />"><i class="fam-user"></i> About</a></li>
    <li <#if active=1>class="active"</#if>><a href="<@spring.url '/u/${player.name }/comments/0/10' />"><i class="fam-user-comment"></i> Comments</a>
    <li <#if active=2>class="active"</#if>><a href="<@spring.url '/u/${player.name }/posts/0/10' />"><i class="fam-page-green"></i> Posts</a>
    <#if self>
    <li <#if active=3>class="active"</#if>><a href="<@spring.url '/u/${player.name }/saved/0/10' />"><i class="fam-disk"></i> Saved</a>
    </#if>
  </ul>
</div>
</#macro>